package com.example.serviciocuentamovimientos.service;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for the CuentaListener class.
 */
@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class CuentaListenerTest {

    @Autowired
    private CuentaListener cuentaListener;

    @Autowired
    private CuentaRepository cuentaRepository;

    /**
     * Test for the receiveMessage method.
     * This test sends a message to the CuentaListener and verifies that the corresponding account is created or updated.
     */
    @Test
    void testReceiveMessage() {
        String message = "1";
        cuentaListener.receiveMessage(message);

        // Verify that the account has been updated or created
        Cuenta cuenta = cuentaRepository.findById(Long.valueOf(message)).orElse(null);
        assertThat(cuenta).isNotNull();
    }
}
