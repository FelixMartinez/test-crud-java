package com.example.serviciocuentamovimientos.service;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application-test.properties")
public class CuentaListenerTest {

    @Autowired
    private CuentaListener cuentaListener;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Test
    void testReceiveMessage() {
        String message = "1";
        cuentaListener.receiveMessage(message);

        // Verificar que la cuenta ha sido actualizada o creada
        Cuenta cuenta = cuentaRepository.findById(Long.valueOf(message)).orElse(null);
        assertThat(cuenta).isNotNull();
    }
}
