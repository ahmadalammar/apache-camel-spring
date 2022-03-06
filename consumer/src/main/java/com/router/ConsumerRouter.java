package com.router;

import com.processors.ProcessRequest;
import com.transfomer.TransformMsg;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ConsumerRouter extends RouteBuilder {

    @Value("${kafka.publisher.listener}")
    String kafkaPublisherListener;

    @Value("${kafka.consumer.listener}")
    String kafkaConsumerListener;


    @Override
    public void configure() throws Exception {

        from(kafkaConsumerListener)
                .id("DirectToKafka")
                .transform()
                .exchange(TransformMsg::addAuditMsg)
                .process(new ProcessRequest())
                .log("[Consumer] received a msg: ${body}");
    }
}
