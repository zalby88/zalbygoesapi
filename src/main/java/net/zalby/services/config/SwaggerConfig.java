package net.zalby.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * The Swagger Config class which sets up the API basic documentation home page 
 * (which by default is /{appname}/swagger-ui.html)
 * 
 * @author Alberto Lazzarin
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("net.zalby.services"))
                .paths(PathSelectors.any())
                .build().apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return new ApiInfo(
                "Zalby Goes API - Documentation",
                null,
                "v1.0",
                null,
                null,
                null,
                null,
                Collections.emptyList()
        );
    }
}//end class