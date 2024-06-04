package com.example.serviciocuentamovimientos.integration;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CuentaIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CuentaRepository cuentaRepository;

    @BeforeEach
    void setUp() {
        cuentaRepository.deleteAll();
        Cuenta cuenta = new Cuenta("123456", "Ahorro", 1000.0, true, 1L);
        cuentaRepository.save(cuenta);
    }

    @SuppressWarnings("null")
    @Test
    void testGetCuenta() {
        Cuenta cuenta = cuentaRepository.findAll().get(0);
        ResponseEntity<Cuenta> response = restTemplate.exchange(
                "/cuentas/" + cuenta.getId(),
                HttpMethod.GET,  // Asegúrate de que esto coincida con el método del controlador
                HttpEntity.EMPTY,
                Cuenta.class
        );

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNumeroCuenta()).isEqualTo("123456");
    }
}
