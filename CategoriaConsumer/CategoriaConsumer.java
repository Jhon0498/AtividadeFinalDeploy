package com.seuprojeto.transacao.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CategoriaConsumer {

    @RabbitListener(queues = "categoria.queue")
    public void ouvirCategoria(String mensagem) {
        System.out.println("[Consumer - Transações] Mensagem recebida: " + mensagem);
    }
}
