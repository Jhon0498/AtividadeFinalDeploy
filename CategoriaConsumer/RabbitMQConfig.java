package com.usuario.main.transacao;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue categoriaQueue() {
        // fila durável para não perder mensagens em reinícios
        return new Queue("categoria.queue", true);
    }
}
