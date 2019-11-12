package com.feng.backstage.utils;

/**
 * Create by east on 2019/10/24 8:29
 */

import com.feng.backstage.base.BaseController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger2接口文档配置
 * @author Administrator
 */
@EnableSwagger2
@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                // 访问单个controller
                .apis(RequestHandlerSelectors.basePackage("com.feng.backstage.admin.controller"))
                // 制定所有controller的都实现的一个接口，访问多个controller
                // .apis(RequestHandlerSelectors.withClassAnnotation(BaseController.class))
                // 使用多个controller的共同拥有的父类
                // .apis(RequestHandlerSelectors.basePackage("com.xx"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger2构建RESTful APIs")
                .description("测试文档")
                .version("1.0")
                .build();
    }

}
