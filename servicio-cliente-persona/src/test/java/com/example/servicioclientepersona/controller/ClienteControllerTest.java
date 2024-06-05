package com.example.servicioclientepersona.controller;

import com.example.servicioclientepersona.entity.Cliente;
import com.example.servicioclientepersona.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * Test class for the ClienteController.
 */
@SpringBootTest
@ActiveProfiles("test")
public class ClienteControllerTest {

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    /**
     * Constructor to initialize mocks.
     */
    public ClienteControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test method for getClienteById.
     */
    @SuppressWarnings("null")
    @Test
    void testGetCliente() {
        Cliente cliente = new Cliente("Juan Perez", "Masculino", 30, "123456789", "Av. Siempre Viva 123", "987654321", "juanp", "password", true);
        when(clienteService.getClienteById(1L)).thenReturn(Optional.of(cliente));

        ResponseEntity<Cliente> response = clienteController.getClienteById(1L);

        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals("Juan Perez", response.getBody().getNombre());
        verify(clienteService, times(1)).getClienteById(1L);
    }
}
