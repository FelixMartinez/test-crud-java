package com.example.serviciocuentamovimientos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.serviciocuentamovimientos.entity.Movimiento;
import com.example.serviciocuentamovimientos.service.MovimientoService;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing "Movimiento" entities.
 */
@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    /**
     * Retrieves all "Movimiento" entities.
     *
     * @return a list of all "Movimiento" entities.
     */
    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }

    /**
     * Retrieves a "Movimiento" entity by its ID.
     *
     * @param id the ID of the "Movimiento" entity to retrieve.
     * @return the "Movimiento" entity with the specified ID, or a 404 Not Found status if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Optional<Movimiento> movimiento = movimientoService.getMovimientoById(id);
        if (movimiento.isPresent()) {
            return ResponseEntity.ok(movimiento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new "Movimiento" entity.
     *
     * @param movimiento the "Movimiento" entity to create.
     * @return the created "Movimiento" entity.
     */
    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) {
        try {
            Movimiento nuevoMovimiento = movimientoService.createMovimiento(movimiento);
            return ResponseEntity.ok(nuevoMovimiento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    /**
     * Updates an existing "Movimiento" entity by its ID.
     *
     * @param id the ID of the "Movimiento" entity to update.
     * @param movimientoDetails the updated details of the "Movimiento" entity.
     * @return the updated "Movimiento" entity, or a 404 Not Found status if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimientoDetails) {
        try {
            Movimiento updatedMovimiento = movimientoService.updateMovimiento(id, movimientoDetails);
            return ResponseEntity.ok(updatedMovimiento);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a "Movimiento" entity by its ID.
     *
     * @param id the ID of the "Movimiento" entity to delete.
     * @return a 204 No Content status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
