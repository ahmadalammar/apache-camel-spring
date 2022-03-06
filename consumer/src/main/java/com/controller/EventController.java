package com.controller;

import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EventController {

    @Autowired
    ProducerTemplate producerTemplate;

    @Value("${kafka.publisher.listener}")
    String kafkaListener;

    @GetMapping(value = "/consumer/event/send" )
    public String sendEvent(@RequestParam("msg") String msg) {
        producerTemplate.sendBodyAndHeaders(kafkaListener, "Send Msg from consumer, msg="+msg,null);
        return "Consumer has send a msg successfully to publisher";
    }

}
