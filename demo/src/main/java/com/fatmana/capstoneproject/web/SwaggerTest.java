package com.fatmana.capstoneproject.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerTest {
    //DocketBean is defined and using its select method we get an instance of ApiSelectorBuider
    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().apis(RequestHandlerSelectors.basePackage("com.fatmana.capstoneproject.web")).paths(PathSelectors.any()).build();
    }


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("API References for PPM ")
                .description("Your API reference for developers")
                .termsOfServiceUrl("http://yourapi.com")
                .contact("helen@gmail.com").license("Capstone API  License")
                .licenseUrl("helen@gmail.com").version("2.0").build();
    }

}