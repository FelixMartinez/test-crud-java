package com.example.servicioclientepersona.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.servicioclientepersona.entity.Cliente;
import com.example.servicioclientepersona.repository.ClienteRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Unit test class for ClienteService.
 */
public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    /**
     * Initializes the mocks for the test.
     */
    public ClienteServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the creation of a new Cliente.
     */
    @Test
    void testCreateCliente() {
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        Cliente result = clienteService.createCliente(cliente);

        assertEquals("Juan", result.getNombre());
        verify(clienteRepository, times(1)).save(cliente);
    }

    /**
     * Tests the retrieval of a Cliente by ID.
     */
    @Test
    void testGetClienteById() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNombre("Juan");
        when(clienteRepository.findById(1L)).thenReturn(Optional.of(cliente));

        Optional<Cliente> result = clienteService.getClienteById(1L);

        assertEquals(true, result.isPresent());
        assertEquals("Juan", result.get().getNombre());
        verify(clienteRepository, times(1)).findById(1L);
    }
}
