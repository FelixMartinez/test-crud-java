package com.example.servicioclientepersona.integration;

import com.example.servicioclientepersona.entity.Cliente;
import com.example.servicioclientepersona.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Integration test class for the Cliente entity.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ClienteIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Sets up the test environment by clearing the Cliente repository and adding a test Cliente.
     */
    @BeforeEach
    void setUp() {
        clienteRepository.deleteAll();
        Cliente cliente = new Cliente("Juan Perez", "Masculino", 30, "123456789", "Av. Siempre Viva 123", "987654321", "juanp", "password", true);
        clienteRepository.save(cliente);
    }

    /**
     * Tests the retrieval of a Cliente by ID.
     */
    @SuppressWarnings("null")
    @Test
    void testGetCliente() {
        Cliente cliente = clienteRepository.findAll().get(0);
        ResponseEntity<Cliente> response = restTemplate.exchange(
                "/clientes/" + cliente.getId(),
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Cliente.class
        );
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertNotNull(response.getBody());
        assertThat(response.getBody().getNombre()).isEqualTo("Juan Perez");
    }
}
