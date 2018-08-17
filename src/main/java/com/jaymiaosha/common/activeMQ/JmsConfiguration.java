package com.jaymiaosha.common.activeMQ;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * activeMQ参数配置类
 */
@Configuration
public class JmsConfiguration {
    @Bean
    public Queue queue() {
        return new ActiveMQQueue("roncoo.queue");
    }
}

