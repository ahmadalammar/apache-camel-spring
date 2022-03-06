package com.processors;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ProcessRequest implements Processor {


    private static final Logger LOG = LoggerFactory.getLogger(ProcessRequest.class);
    @Override
    public void process(Exchange exchange) {
        LOG.info("[Consumer] Processing msg: "+exchange.getIn().getBody());
    }
}
