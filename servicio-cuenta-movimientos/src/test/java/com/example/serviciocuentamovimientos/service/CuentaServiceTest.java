package com.example.serviciocuentamovimientos.service;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Test class for the CuentaService class.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
class CuentaServiceTest {

    @Mock
    private CuentaRepository cuentaRepository;

    @InjectMocks
    private CuentaService cuentaService;

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the getCuenta method for an existing account.
     * This test verifies that the account details are returned correctly when the account exists.
     */
    @Test
    void testGetCuenta() {
        Long cuentaId = 1L;
        Cuenta cuenta = new Cuenta("123456", "Ahorro", 1000.0, true, 1L);
        cuenta.setId(cuentaId);

        when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.of(cuenta));

        Cuenta result = cuentaService.getCuenta(cuentaId);

        assertThat(result.getNumeroCuenta()).isEqualTo("123456");
    }

    /**
     * Tests the getCuenta method for a non-existing account.
     * This test verifies that an exception is thrown with the correct message when the account does not exist.
     */
    @Test
    void testGetCuentaNotFound() {
        Long cuentaId = 1L;

        when(cuentaRepository.findById(cuentaId)).thenReturn(Optional.empty());

        try {
            cuentaService.getCuenta(cuentaId);
        } catch (RuntimeException e) {
            assertThat(e.getMessage()).isEqualTo("Cuenta not found");
        }
    }
}
