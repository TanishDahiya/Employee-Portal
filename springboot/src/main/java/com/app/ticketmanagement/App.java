package com.app.ticketmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableCaching
public class App {
	

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);

	}
	@Bean
	public Docket swaggerApiConfig() {
	return new Docket(DocumentationType.SWAGGER_2)
	.select()
	.paths(PathSelectors.any())
	.apis(RequestHandlerSelectors.basePackage("com.blog.website"))
	.build();
	}

}
