package com.example.serviciocuentamovimientos.controller;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.service.CuentaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CuentaControllerTest {

    @Mock
    private CuentaService cuentaService;

    @InjectMocks
    private CuentaController cuentaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @SuppressWarnings("null")
    @Test
    void testGetCuenta() {
        Long cuentaId = 1L;
        Cuenta cuenta = new Cuenta("123456", "Ahorro", 1000.0, true, 1L);
        cuenta.setId(cuentaId);

        when(cuentaService.getCuenta(cuentaId)).thenReturn(cuenta);

        ResponseEntity<Cuenta> response = cuentaController.getCuentaById(cuentaId);

        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getNumeroCuenta()).isEqualTo("123456");
    }

    @Test
    void testGetCuentaNotFound() {
        Long cuentaId = 1L;

        when(cuentaService.getCuenta(cuentaId)).thenThrow(new RuntimeException("Cuenta not found"));

        ResponseEntity<Cuenta> response = cuentaController.getCuentaById(cuentaId);

        assertThat(response.getStatusCode().value()).isEqualTo(404);
    }
}
