package com.lucasteixeira.notificacao.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${mq.queues.emailcadastro-queue}")
    private String emailCadastroQueue;

    @Value("${mq.queues.emailcadastrotarefas-queue}")
    private String emailCadastroTarefasQueue;

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public Queue queueEmailCadastro() {
        return new Queue(emailCadastroQueue, true);
    }

    @Bean
    public Queue queueEmailCadastroTarefas() {
        return new Queue(emailCadastroTarefasQueue, true);
    }

}
