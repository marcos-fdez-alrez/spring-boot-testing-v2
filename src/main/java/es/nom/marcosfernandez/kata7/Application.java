package es.nom.marcosfernandez.kata7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Main Application.
 */
@SpringBootApplication
@EnableSwagger2
public class Application {

    /**
     * Main entry point.
     *
     * @param args - main arguments
     */
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /**
     * api() Method.
     *
     * @return - Docket
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.regex("/convert/eur/usd.*"))
                .build();
    }
}
