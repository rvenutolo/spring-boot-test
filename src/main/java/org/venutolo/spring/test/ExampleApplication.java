package org.venutolo.spring.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.stream.Collectors;

@SpringBootApplication
public class ExampleApplication {

    private static final Log logger = LogFactory.getLog(ExampleApplication.class);

    public static void main(final String... args) {
        SpringApplication.run(ExampleApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(final ApplicationContext applicationContext) {
        return args -> {
            final String beans = Arrays.stream(applicationContext.getBeanDefinitionNames())
                                       .sorted()
                                       .collect(Collectors.joining("\n\t\t", "[\n\t\t", "\n]"));
            logger.info("Spring Boot beans: " + beans);
        };
    }

}
