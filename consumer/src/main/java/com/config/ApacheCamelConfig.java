package com.config;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.component.ComponentsBuilderFactory;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ApacheCamelConfig {

    @Value("${kafka.brokers}")
    String kafkaBrokers;

    @Bean
    @Autowired
    public CamelContext initCamelContextWithKafka(List<RouteBuilder> routeBuilderList){
        CamelContext camelContext = new DefaultCamelContext();
        camelContext.getPropertiesComponent().setLocation("classpath:application.properties");
        ComponentsBuilderFactory.kafka()
                .brokers(kafkaBrokers)
                .register(camelContext, "kafka");
        routeBuilderList.stream().forEach(routeBuilder -> {
            try {
                camelContext.addRoutes(routeBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        camelContext.start();
        return camelContext;
    }

    @Bean
    public ProducerTemplate producerTemplate(CamelContext camelContext){
        return camelContext.createProducerTemplate();
    }

}
