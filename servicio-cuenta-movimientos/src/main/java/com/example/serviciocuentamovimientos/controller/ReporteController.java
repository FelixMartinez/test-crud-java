package com.example.serviciocuentamovimientos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.entity.Movimiento;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import com.example.serviciocuentamovimientos.repository.MovimientoRepository;
import com.example.serviciocuentamovimientos.dto.ReporteDTO;

import java.util.Date;
import java.util.List;

/**
 * REST controller for generating reports.
 */
@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    /**
     * Generates a report of account status within a date range for a specific client.
     *
     * @param clienteId the ID of the client.
     * @param fechaInicio the start date of the range.
     * @param fechaFin the end date of the range.
     * @return a report containing the associated accounts and their balances, along with the details of account movements.
     */
    @GetMapping
    public ResponseEntity<ReporteDTO> getReporte(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin) {
        try {
            List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
            List<Movimiento> movimientos = movimientoRepository.findByCuentaClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin);
            ReporteDTO reporte = new ReporteDTO(cuentas, movimientos);
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
