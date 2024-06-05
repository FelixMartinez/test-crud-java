package com.example.servicioclientepersona.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.servicioclientepersona.entity.Cliente;
import com.example.servicioclientepersona.repository.ClienteRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Cliente entities.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Get all clients.
     *
     * @return a list of all clients.
     */
    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    /**
     * Get a client by ID.
     *
     * @param id the ID of the client to retrieve.
     * @return an Optional containing the client if found, or empty if not found.
     */
    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    /**
     * Create a new client.
     *
     * @param cliente the client to create.
     * @return the created client.
     */
    public Cliente createCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    /**
     * Update an existing client.
     *
     * @param id the ID of the client to update.
     * @param clienteDetails the new details of the client.
     * @return the updated client.
     * @throws NoSuchElementException if the client with the specified ID does not exist.
     */
    public Cliente updateCliente(Long id, Cliente clienteDetails) {
        Cliente cliente = clienteRepository.findById(id).orElseThrow();
        cliente.setNombre(clienteDetails.getNombre());
        cliente.setGenero(clienteDetails.getGenero());
        cliente.setEdad(clienteDetails.getEdad());
        cliente.setIdentificacion(clienteDetails.getIdentificacion());
        cliente.setDireccion(clienteDetails.getDireccion());
        cliente.setTelefono(clienteDetails.getTelefono());
        cliente.setClienteId(clienteDetails.getClienteId());
        cliente.setContraseña(clienteDetails.getContraseña());
        cliente.setEstado(clienteDetails.isEstado());
        return clienteRepository.save(cliente);
    }

    /**
     * Delete a client.
     *
     * @param id the ID of the client to delete.
     */
    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}
