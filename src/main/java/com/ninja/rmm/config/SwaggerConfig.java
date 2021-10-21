package com.ninja.rmm.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${swagger.service.title}")
    private String serviceTitle;

    @Value("${swagger.service.description-rmm-service-server}")
    private String serviceDqaDescription;

    @Bean
    public Docket rdsOneApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("ninja")
                .apiInfo(apiInfo(serviceTitle, serviceDqaDescription)).select()
                .apis(RequestHandlerSelectors.basePackage("com.ninja.rmm")).build();
    }

    private ApiInfo apiInfo(String title, String description) {
        return new ApiInfoBuilder().title(title).description(description).build();
    }

}
