package com.dics.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;

import springfox.documentation.spring.web.plugins.Docket;


@Configuration
public class ApplicationSwaggerConfig {
	
	@Bean
    public Docket employeeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //.apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }
	
	 //http://localhost:port/swagger-ui.html
	 private ApiInfo getApiInfo() {
	        return new ApiInfoBuilder()
	                .title("CheckIn API")
	                .version("1.0")
	                .description("API for Product Order")
	                .contact(new Contact("Diego Castaneda", "https://app.pluralsight.com/profile/idiegocs", "idiegocs@gmail.com"))
	                .license("Apache License Version 2.0")
	                .build();
	    }


}
