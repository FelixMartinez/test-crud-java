package com.example.serviciocuentamovimientos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.entity.Movimiento;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import com.example.serviciocuentamovimientos.repository.MovimientoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing "Movimiento" entities.
 */
@Service
public class MovimientoService {
    
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    /**
     * Retrieves all "Movimiento" entities.
     *
     * @return a list of all "Movimiento" entities.
     */
    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    /**
     * Retrieves a "Movimiento" entity by its ID.
     *
     * @param id the ID of the "Movimiento" entity to retrieve.
     * @return an Optional containing the "Movimiento" entity if found, or an empty Optional if not found.
     */
    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    /**
     * Creates a new "Movimiento" entity and updates the related account's balance.
     *
     * @param movimiento the "Movimiento" entity to create.
     * @return the created "Movimiento" entity.
     * @throws RuntimeException if the related account is not found or if there is insufficient balance for the movement.
     */
    public Movimiento createMovimiento(Movimiento movimiento) {
        try {
            Optional<Cuenta> cuentaOpt = cuentaRepository.findById(movimiento.getCuenta().getId());
            if (!cuentaOpt.isPresent()) {
                throw new RuntimeException("Cuenta no encontrada");
            }
            Cuenta cuenta = cuentaOpt.get();
            if (movimiento.getValor() < 0 && cuenta.getSaldoInicial() + movimiento.getValor() < 0) {
                throw new RuntimeException("Saldo no disponible");
            }
            cuenta.setSaldoInicial(cuenta.getSaldoInicial() + movimiento.getValor());
            cuentaRepository.save(cuenta);
            movimiento.setSaldo(cuenta.getSaldoInicial());
            return movimientoRepository.save(movimiento);
        } catch (RuntimeException e) {
            throw e; // Re-throwing to be handled at a higher level, such as the controller
        } catch (Exception e) {
            throw new RuntimeException("Error creating movimiento", e);
        }
    }

    /**
     * Updates an existing "Movimiento" entity.
     *
     * @param id the ID of the "Movimiento" entity to update.
     * @param movimientoDetails the updated details of the "Movimiento" entity.
     * @return the updated "Movimiento" entity.
     * @throws RuntimeException if the "Movimiento" entity is not found.
     */
    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        try {
            Movimiento movimiento = movimientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimiento not found"));
            movimiento.setFecha(movimientoDetails.getFecha());
            movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
            movimiento.setValor(movimientoDetails.getValor());
            movimiento.setSaldo(movimientoDetails.getSaldo());
            return movimientoRepository.save(movimiento);
        } catch (RuntimeException e) {
            throw e; // Re-throwing to be handled at a higher level, such as the controller
        } catch (Exception e) {
            throw new RuntimeException("Error updating movimiento", e);
        }
    }

    /**
     * Deletes a "Movimiento" entity by its ID.
     *
     * @param id the ID of the "Movimiento" entity to delete.
     */
    public void deleteMovimiento(Long id) {
        try {
            movimientoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Error deleting movimiento", e);
        }
    }
}
