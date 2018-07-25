package com.bridgeit.todoapplication.configuration;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author : Vishal Dhar Dubey
 * @version: 1.0
 * @since : 13-07-2018
 * <p><b>Class is for configuring the Swagger configuration</b></p>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	/**
	 * <p>A builder which is intended to be the primary interface into the swagger-springmvc framework.
	 * Provides sensible defaults and convenience methods for configuration.</p>
	 * @return docket 
	 */
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("public-api").apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}

	/**
	 * <p>Function is to Returns a predicate that evaluates to true if either of its components evaluates to true. 
	 * The components are evaluated in order, and evaluation will be "short-circuited" as soon as a true predicate is found.</p>
	 * @return predicate
	 */
	private Predicate<String> postPaths() {
		return or(regex("/.*"), regex("/.*"));
	}

	/**
	 * <p> Function is to return ApiInfoBuilder</p>
	 * @return ApiInfoBuilder
	 */
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Todo application").description("todo API reference for developers")
				.contact("vishaldhardubey1807@gmail.com").licenseUrl("javainuse@gmail.com").version("1.0").build();
	}
	
	/**
	 * <p> Function is to return the bean of SecurityConfiguration</p>
	 * @return object of SecurityConfig
	 */
	@Bean
	public SecurityConfiguration securityConfig() {
		return new SecurityConfiguration(null, null, null, null, "Token", ApiKeyVehicle.HEADER, "token", ",");
	}

}