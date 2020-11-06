package com.muxi.java.example.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.*;

/**
 * <p>
 * 代码生成器父类
 * </p>
 *
 * @author whh
 */
public class SuperGenerator {

    /**
     * 获取TemplateConfig
     *
     * @return
     */
    protected TemplateConfig getTemplateConfig() {
        return new TemplateConfig().setXml(null);
    }

    /**
     * 获取InjectionConfig
     *
     * @return
     */
    protected InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.<FileOutConfig>singletonList(new FileOutConfig(
                "/templates/mapper.xml.vm") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return getResourcePath() + "/mapper/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));
    }

    /**
     * 获取PackageConfig
     *
     * @return
     */
    protected PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent("com.muxi.java.example")
                .setController("web")
                .setEntity("model")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl");
    }

    /**
     * 获取StrategyConfig
     *
     * @param tableName
     * @return
     */
    protected StrategyConfig getStrategyConfig(String tableName) {
        List<TableFill> tableFillList = getTableFills();
        return new StrategyConfig()
                .setCapitalMode(true)// 全局大写命名
//                .setTablePrefix("sys_")// 去除前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                //.setInclude(new String[] { "user" }) // 需要生成的表
                //自定义实体父类
//                .setSuperEntityClass("com.kordeta.framework.model.BaseModel")
                // 自定义实体，公共字段
//                .setSuperEntityColumns("id")
                .setTableFillList(tableFillList)
                // 自定义 mapper 父类
//                .setSuperMapperClass("com.kordeta.framework.mapper.BaseMapper")
//                // 自定义 controller 父类
//                .setSuperControllerClass("com.kordeta.framework.controller.SuperController")
//                // 自定义 service 实现类父类
//                .setSuperServiceImplClass("com.kordeta.framework.service.impl.BaseServiceImpl")
//                // 自定义 service 接口父类
//                .setSuperServiceClass("com.kordeta.framework.service.BaseService")
                // 【实体】是否生成字段常量（默认 false）
                .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                .setEntityBuilderModel(false)
                // 【实体】是否为lombok模型（默认 false）
                .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setRestControllerStyle(false)
                .setRestControllerStyle(true)
                .setInclude(tableName);
    }

    /**
     * 获取TableFill策略
     *
     * @return
     */
    protected List<TableFill> getTableFills() {
        // 自定义需要填充的字段
        List<TableFill> tableFillList = new ArrayList<>();
//        tableFillList.add(new TableFill("createTime", FieldFill.INSERT));
//        tableFillList.add(new TableFill("updateTime", FieldFill.INSERT_UPDATE));
//        tableFillList.add(new TableFill("createUid", FieldFill.INSERT));
//        tableFillList.add(new TableFill("updateUid", FieldFill.INSERT_UPDATE));
        return tableFillList;
    }

    /**
     * 获取DataSourceConfig
     *
     * @return
     */
    protected DataSourceConfig getDataSourceConfig() {
        DBSetting dbSetting = readSetting();
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)// 数据库类型
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                        if (fieldType.toLowerCase().equals("bit")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().equals("tinyint")) {
                            return DbColumnType.BOOLEAN;
                        }
                        if (fieldType.toLowerCase().equals("date")) {
                            return DbColumnType.LOCAL_DATE;
                        }
                        if (fieldType.toLowerCase().equals("time")) {
                            return DbColumnType.LOCAL_TIME;
                        }
                        if (fieldType.toLowerCase().equals("datetime")) {
                            return DbColumnType.LOCAL_DATE_TIME;
                        }
                        return super.processTypeConvert(globalConfig, fieldType);
                    }
                })
//                .setDriverName("com.mysql.jdbc.Driver")
//                .setUsername("test")
//                .setPassword("aiuY67%9*66")
//                .setUrl("jdbc:mysql://10.5.151.180:3306/test?useUnicode=true&characterEncoding=UTF-8&&useSSL=false");
                .setDriverName(dbSetting.getDriverName())
                .setUsername(dbSetting.getUserName())
                .setPassword(dbSetting.getPassword())
                .setUrl(dbSetting.getUrl());
    }

    /**
     * 获取GlobalConfig
     *
     * @return
     */
    protected GlobalConfig getGlobalConfig() {
        return new GlobalConfig()
                .setOutputDir(getJavaPath())//输出目录
                .setFileOverride(false)// 是否覆盖文件
                .setActiveRecord(false)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(false)// XML ResultMap
                .setBaseColumnList(false)// XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                .setOpen(false)
                .setAuthor("muxi") //作者
                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%s")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("I%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController");
    }


    /**
     * 获取根目录
     *
     * @return
     */
    private String getRootPath() {
        String file = Objects.requireNonNull(SuperGenerator.class.getClassLoader().getResource("")).getFile();
        return new File(file).getParentFile().getParentFile().getPath();
    }

    /**
     * 获取JAVA目录
     *
     * @return
     */
    protected String getJavaPath() {
        return getRootPath() + "/src/main/java";
    }

    /**
     * 获取Resource目录
     *
     * @return
     */
    protected String getResourcePath() {
        return getRootPath() + "/src/main/resources";
    }

    /**
     * 读取数据库配置
     */
    private DBSetting readSetting() {
        DBSetting setting = new DBSetting();
        try {
            Yaml yaml = new Yaml();
            URL url = Test.class.getClassLoader().getResource("application.yml");
            if (url != null) {
                //获取test.yaml文件中的配置数据，然后转换为map
                Map map = (Map) yaml.load(new FileInputStream(url.getFile()));
                Map rstMap = (Map) map.get("spring");
                if (rstMap != null) {
                    rstMap = (Map) rstMap.get("datasource");
                }
                if (rstMap != null) {
                    setting.setDriverName(String.valueOf(rstMap.get("driver-class-name")));
                    setting.setUserName(String.valueOf(rstMap.get("username")));
                    setting.setPassword(String.valueOf(rstMap.get("password")));
                    setting.setUrl(String.valueOf(rstMap.get("url")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return setting;
    }

    /**
     * 获取AutoGenerator
     *
     * @param tableName
     * @return
     */
    protected AutoGenerator getAutoGenerator(String tableName) {
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 数据源配置
                .setDataSource(getDataSourceConfig())
                // 策略配置
                .setStrategy(getStrategyConfig(tableName))
                // 包配置
                .setPackageInfo(getPackageConfig())
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                .setCfg(getInjectionConfig())
                .setTemplate(getTemplateConfig());
    }
}
