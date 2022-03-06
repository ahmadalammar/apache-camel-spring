package com.router;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.processors.ProcessRequest;
import com.transfomer.TransformMsg;

import java.util.Objects;

@Component
public class PublisherRouter extends RouteBuilder {

    @Value("${kafka.publisher.listener}")
    String kafkaPublisherListener;

    @Value("${kafka.consumer.listener}")
    String kafkaConsumerListener;


    @Override
    public void configure() throws Exception {

        from(kafkaPublisherListener)
                .id("DirectToPublisher")
                .transform()
                .exchange(TransformMsg::addAuditMsg)
                .process(new ProcessRequest())
                .log("[Publisher] received a msg : ${body}");
    }
}
