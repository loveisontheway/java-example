package com.muxi.java.example.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.muxi.java.example.enums.DBTypeEnum;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.*;

@Configuration
public class MybatisPlusConfig {
    /**
     * 分页
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 乐观锁
     *
     * @return
     */
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }

    @Bean(name = "dataSourceDefault")
    @ConfigurationProperties(prefix = "datasource.default")
    public DataSource defaultDB() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dataSourceOther")
    @ConfigurationProperties(prefix = "datasource.other")
    public DataSource otherDB() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源配置
     *
     * @return
     */
    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier("dataSourceDefault") DataSource dataSourceDefault,
                                         @Qualifier("dataSourceOther") DataSource dataSourceOther) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        Map<Object, Object> targetDataSources = new HashMap<>(4);
        targetDataSources.put(DBTypeEnum.DEFAULTDB.getType(), dataSourceDefault);
        targetDataSources.put(DBTypeEnum.OTHERDB.getType(), dataSourceOther);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        dynamicDataSource.setDefaultTargetDataSource(dataSourceDefault);
        return dynamicDataSource;
    }

    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(defaultDB(), otherDB()));

        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);

        sqlSessionFactory.setConfiguration(configuration);

        List<Resource> resourceList = new ArrayList<>();
        // classpath*: 遍历所有的classpath，加载速度慢，用处就是规避mapper空文件夹（抛异常），尽量避免使用classpath*
//        resourceList.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources("classpath*:/mapper/*.xml")));
        resourceList.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml")));
        resourceList.addAll(Arrays.asList(new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/other/*.xml")));
        Resource[] resources = resourceList.toArray(new Resource[0]);
        sqlSessionFactory.setMapperLocations(resources);
        sqlSessionFactory.setTypeAliasesPackage("com.muxi.java.example.mapper.*,com.muxi.java.example.mapper.other.*");

        //PerformanceInterceptor(),OptimisticLockerInterceptor()
        //添加分页功能
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor()
        });
//        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }
}
