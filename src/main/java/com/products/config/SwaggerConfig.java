package com.products.config;

import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


@Profile(value = "default")
@Configuration

public class SwaggerConfig {
  @Bean
  public Docket api() {
      return new Docket(DocumentationType.SWAGGER_2)
              .select()
              .apis(RequestHandlerSelectors.basePackage("com.products.api"))
              .paths(PathSelectors.any())
              .build()
              .apiInfo(apiInfo());
  }
  
  private ApiInfo apiInfo() {
    return new ApiInfo(
      "CRUD API", 
      "This is an CRUD app for smooties.", 
      "", 
      "Terms of service", 
      new Contact("n/a", "", "na@dummy.com"), 
      "License of API", "n/a", Collections.emptyList());
}

}
