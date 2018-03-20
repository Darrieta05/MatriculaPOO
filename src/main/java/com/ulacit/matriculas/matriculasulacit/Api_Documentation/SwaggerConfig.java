package com.ulacit.matriculas.matriculasulacit.Api_Documentation;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket MatriculasApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ulacit.matriculas"))
                .paths(regex("/api.*"))
                .build().apiInfo(MetaInfo());
    }

    private ApiInfo MetaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "MATRICULAS+ API",
                "Documentación del API de MATRICULAS-ULACIT+, utilizado para el manejo de toda la información y administración del CRM",
                "1.0",
                "API privado, ULACIT tiene derecho a su uso exclusivo",
                new Contact("ULACIT", "http://ulacit.ac.cr/", "ulacit@ac.cr"),
                "Apache License Version 2.0",
                "https://www.apache.org/license.html"
        );

        return apiInfo;
    }


}
