package com.app.securityservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
@Configuration

public class RabbitMQConfig {

    @Value("${app.topic.exchange}")
    private String topicExchange;

    @Value("${app.mail.queue}")
    private String mailQueue;

    @Value("${app.archive.queue}")
    private String archiveQueue;

    @Value("${app.dead.letter}")
    private String deadLetter;

    @Value("${app.deadLetter.exchange}")
    private String deadLetterExchange;

    @Value("${app.queue.binding}")
    private String queueBinding;

    @Value("${app.archive.binding}")
    private String archiveBinding;

    @Value("${app.dead.letter.queue}")
    private String deadLetterQueue;

    @Value("${spring.rabbitmq.host}")
    private String localHost;

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(topicExchange);
    }

    @Bean
    public Queue mailQueue() {
        return new Queue(mailQueue);
    }

    @Bean
    public Queue archiveQueue() {
        return new Queue(archiveQueue);
    }

    @Bean
    Binding dlqBinding() {
        return BindingBuilder.bind(dlq())
                .to(deadLetterExchange())
                .with(deadLetter);
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(deadLetterExchange);
    }

    @Bean
    Binding queueBinding(Queue mailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(mailQueue)
                .to(topicExchange)
                .with(queueBinding);

    }

    @Bean
    Binding archiveBinding(Queue archiveQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(archiveQueue)
                .to(topicExchange)
                .with(archiveBinding);
    }

    @Bean
    Queue dlq() {
        return QueueBuilder.durable(deadLetterQueue)
                .build();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
