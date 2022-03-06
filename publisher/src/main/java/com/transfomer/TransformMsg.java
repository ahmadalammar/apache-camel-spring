package com.transfomer;


import org.apache.camel.Exchange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class TransformMsg {


    private static final Logger LOG = LoggerFactory.getLogger(TransformMsg.class);
    public static String addAuditMsg(Exchange msg) {
        LOG.info("[Publisher] Adding audit msg....");
        return new StringBuilder().append(msg.getIn().getBody().toString()).append(" , This is audit msg").toString();
    }

}
