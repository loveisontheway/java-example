package com.muxi.java.example.knife;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * knife api docment
 *
 * @author jl.jiang 2021/3/5
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {

        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("台接口")
                .description("这里描述内容")
                .contact(new Contact("muxi", "", ""))
                .termsOfServiceUrl("http://localhost:60000/")
                .version("1.0")
                .build();

        return new Docket(DocumentationType.SWAGGER_2)
                .host("http://localhost:60000/")
                .groupName("后台接口")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.muxi.java.web"))
                .paths(PathSelectors.any())
                .build();
    }
}

