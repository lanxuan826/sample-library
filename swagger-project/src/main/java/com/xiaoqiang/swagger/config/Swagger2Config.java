package com.xiaoqiang.swagger.config;

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
 * Swagger2Config <br>
 * 〈Swagger2配置类〉
 *
 * @author XiaoQiang
 * @create 2019-1-4
 * @since 1.0.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {


    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.**.web"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("Swagger2 构建RESTful API")
                .contact(new Contact("Xiaoqiang" , "http://www.baidu.com" , ""))
                .version("1.0")
                .description("API描述")
                .build();
    }
}
