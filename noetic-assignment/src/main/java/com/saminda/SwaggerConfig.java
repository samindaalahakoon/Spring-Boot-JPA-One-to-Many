package com.saminda;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.saminda.rest")).paths(regex("/service.*")).build()
				.apiInfo(metaData());

	}

	private ApiInfo metaData() {
		// @SuppressWarnings("deprecation")
		 ApiInfo apiInfo = new ApiInfo(
	                "Neotic Assignment API",
	                "Document Management API reference for Developers",
	                "1.0",
	                "Terms of service",
	                new Contact("Saminda Alahakoon", "https://www.linkedin.com/in/samindaalahakoon", "samindaalahakoon7@gmail.com"),
	               "Apache License Version 2.0",
	                "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}

