package com.example.servicioclientepersona.service;
import com.example.servicioclientepersona.config.RabbitMQConfig;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ClienteListener {

    @RabbitListener(queues = RabbitMQConfig.QUEUE)
    public void receiveMessage(String message) {
        // Lógica para manejar el mensaje recibido
        System.out.println("Received message: " + message);
    }
}
