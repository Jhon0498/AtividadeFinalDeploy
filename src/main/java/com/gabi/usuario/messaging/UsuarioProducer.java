package com.gabi.usuario.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String NOME_FILA = "fila.usuario";

    public void enviarMensagem(String mensagem) {
        rabbitTemplate.convertAndSend(NOME_FILA, mensagem);
        System.out.println("Mensagem enviada para o RabbitMQ: " + mensagem);
    }
}
