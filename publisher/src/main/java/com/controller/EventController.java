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

    @Value("${kafka.consumer.listener}")
    String kafkaListener;

    @GetMapping(value = "/publisher/event/send" )
    public String sendEvent(@RequestParam("msg") String msg) {
        // Send a msg to consumer from publisher...
        producerTemplate.sendBodyAndHeaders(kafkaListener, "Send Msg from publisher, msg="+msg,null);
        return "Publisher has sent the msg successfully";
    }

}
