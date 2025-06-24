package com.gabi.usuario.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue filaUsuario() {
        return new Queue("fila.usuario", true);
    }
}
