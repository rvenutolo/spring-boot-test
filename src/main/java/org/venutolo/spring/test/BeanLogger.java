package org.venutolo.spring.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class BeanLogger implements CommandLineRunner {

    private static final Log logger = LogFactory.getLog(BeanLogger.class);

    private final ApplicationContext context;

    @Autowired
    public BeanLogger(final ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(final String... args) throws Exception {
        final String beans = Arrays.stream(context.getBeanDefinitionNames())
                                   .sorted()
                                   .collect(Collectors.joining("\n\t\t", "[\n\t\t", "\n]"));
        logger.info("Spring beans: " + beans);
    }

}
