package com.example.servicioclientepersona.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ClienteListenerTest {

    @InjectMocks
    private ClienteListener clienteListener;

    public ClienteListenerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testReceiveMessage() {
        String message = "Test message";
        clienteListener.receiveMessage(message);
        // Aquí podrías verificar algún comportamiento esperado en la recepción del mensaje
        // Por ejemplo, si se actualiza algún estado, se guarda algo en la base de datos, etc.
    }
}
