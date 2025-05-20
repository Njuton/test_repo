package com.example.myapp.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author rmartynov
 * @since 19.05.2025
 */
@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE_NAME = "directExchange";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue groupQueue1() {
        return new Queue("groupQueue_1", false);
    }

    @Bean
    public Queue groupQueue2() {
        return new Queue("groupQueue_2", false);
    }

    @Bean
    public Queue groupQueue3() {
        return new Queue("groupQueue_3", false);
    }

    @Bean
    public Binding bindingGroupQueue1(Queue groupQueue1, DirectExchange postExchange) {
        return BindingBuilder.bind(groupQueue1).to(postExchange).with("post.created.group1");
    }

    @Bean
    public Binding bindingGroupQueue2(Queue groupQueue2, DirectExchange postExchange) {
        return BindingBuilder.bind(groupQueue2).to(postExchange).with("post.created.group2");
    }

    @Bean
    public Binding bindingGroupQueue3(Queue groupQueue3, DirectExchange postExchange) {
        return BindingBuilder.bind(groupQueue3).to(postExchange).with("post.created.group3");
    }
}
