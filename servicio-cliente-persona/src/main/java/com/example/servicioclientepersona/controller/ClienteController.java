package com.example.servicioclientepersona.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.servicioclientepersona.entity.Cliente;
import com.example.servicioclientepersona.service.ClienteService;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Cliente entities.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    /**
     * GET /clientes : Get all clients.
     * 
     * @return a list of all clients.
     */
    @GetMapping
    public List<Cliente> getAllClientes() {
        return clienteService.getAllClientes();
    }

    /**
     * GET /clientes/{id} : Get a client by ID.
     * 
     * @param id the ID of the client to retrieve.
     * @return the ResponseEntity with status 200 (OK) and with body the client, or with status 404 (Not Found) if the client does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
        Optional<Cliente> cliente = clienteService.getClienteById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * POST /clientes : Create a new client.
     * 
     * @param cliente the client to create.
     * @return the created client.
     */
    @PostMapping
    public Cliente createCliente(@RequestBody Cliente cliente) {
        return clienteService.createCliente(cliente);
    }

    /**
     * PUT /clientes/{id} : Update an existing client.
     * 
     * @param id the ID of the client to update.
     * @param clienteDetails the new details of the client.
     * @return the ResponseEntity with status 200 (OK) and with body the updated client, or with status 404 (Not Found) if the client does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
        try {
            Cliente updatedCliente = clienteService.updateCliente(id, clienteDetails);
            return ResponseEntity.ok(updatedCliente);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * DELETE /clientes/{id} : Delete a client.
     * 
     * @param id the ID of the client to delete.
     * @return the ResponseEntity with status 204 (No Content).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteCliente(id);
        return ResponseEntity.noContent().build();
    }
}
