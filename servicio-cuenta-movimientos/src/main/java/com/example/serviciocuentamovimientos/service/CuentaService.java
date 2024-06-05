package com.example.serviciocuentamovimientos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing accounts (Cuenta).
 */
@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    /**
     * Retrieves all accounts.
     *
     * @return a list of all accounts.
     */
    public List<Cuenta> getAllCuentas() {
        return cuentaRepository.findAll();
    }

    /**
     * Retrieves an account by its ID.
     *
     * @param id the ID of the account.
     * @return an optional containing the account if found, or empty if not found.
     */
    public Optional<Cuenta> getCuentaById(Long id) {
        return cuentaRepository.findById(id);
    }

    /**
     * Notifies the client service with a given message.
     *
     * @param message the message to send to the client service.
     */
    public void notifyClienteService(String message) {
        System.out.println("Notifying the client service: " + message);
    }

    /**
     * Creates a new account.
     *
     * @param cuenta the account to create.
     * @return the created account.
     */
    public Cuenta createCuenta(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    /**
     * Updates an existing account.
     *
     * @param id the ID of the account to update.
     * @param cuentaDetails the updated account details.
     * @return the updated account.
     * @throws RuntimeException if the account is not found.
     */
    public Cuenta updateCuenta(Long id, Cuenta cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta not found"));
        cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDetails.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDetails.getSaldoInicial());
        cuenta.setEstado(cuentaDetails.isEstado());
        return cuentaRepository.save(cuenta);
    }

    /**
     * Deletes an account by its ID.
     *
     * @param id the ID of the account to delete.
     */
    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }

    /**
     * Retrieves an account by its ID, throwing an exception if not found.
     *
     * @param id the ID of the account.
     * @return the account if found.
     * @throws RuntimeException if the account is not found.
     */
    public Cuenta getCuenta(Long id) {
        Optional<Cuenta> cuenta = cuentaRepository.findById(id);
        if (cuenta.isPresent()) {
            return cuenta.get();
        } else {
            throw new RuntimeException("Cuenta not found");
        }
    }
}
