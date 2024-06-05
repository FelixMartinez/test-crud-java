package com.example.servicioclientepersona.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Integration test class for ClienteListener.
 */
@SpringBootTest
@ActiveProfiles("test")
public class ClienteListenerTest {

    @InjectMocks
    private ClienteListener clienteListener;

    /**
     * Initializes the mocks for the test.
     */
    public ClienteListenerTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the receiveMessage method of ClienteListener.
     */
    @Test
    void testReceiveMessage() {
        String message = "Test message";
        clienteListener.receiveMessage(message);
    }
}
