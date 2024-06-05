package com.example.serviciocuentamovimientos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.service.CuentaService;
import org.springframework.http.HttpStatus;
import java.util.List;

/**
 * REST controller for managing "Cuenta" entities.
 */
@RestController
@RequestMapping("/cuentas")
public class CuentaController {
    
    @Autowired
    private CuentaService cuentaService;

    /**
     * Retrieves all "Cuenta" entities.
     *
     * @return a list of all "Cuenta" entities.
     */
    @GetMapping
    public List<Cuenta> getAllCuentas() {
        return cuentaService.getAllCuentas();
    }

    /**
     * Retrieves a "Cuenta" entity by its ID.
     *
     * @param id the ID of the "Cuenta" entity to retrieve.
     * @return the "Cuenta" entity with the specified ID, or a 404 Not Found status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        try {
            Cuenta cuenta = cuentaService.getCuenta(id);
            return new ResponseEntity<>(cuenta, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new "Cuenta" entity.
     *
     * @param cuenta the "Cuenta" entity to create.
     * @return the created "Cuenta" entity.
     */
    @PostMapping
    public Cuenta createCuenta(@RequestBody Cuenta cuenta) {
        return cuentaService.createCuenta(cuenta);
    }

    /**
     * Updates an existing "Cuenta" entity by its ID.
     *
     * @param id the ID of the "Cuenta" entity to update.
     * @param cuentaDetails the updated details of the "Cuenta" entity.
     * @return the updated "Cuenta" entity, or a 404 Not Found status if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> updateCuenta(@PathVariable Long id, @RequestBody Cuenta cuentaDetails) {
        try {
            Cuenta updatedCuenta = cuentaService.updateCuenta(id, cuentaDetails);
            return ResponseEntity.ok(updatedCuenta);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a "Cuenta" entity by its ID.
     *
     * @param id the ID of the "Cuenta" entity to delete.
     * @return a 204 No Content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        cuentaService.deleteCuenta(id);
        return ResponseEntity.noContent().build();
    }
}
