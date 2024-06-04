package com.example.serviciocuentamovimientos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.entity.Movimiento;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import com.example.serviciocuentamovimientos.repository.MovimientoRepository;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    @GetMapping
    public ReporteDTO getReporte(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("fechaInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaInicio,
            @RequestParam("fechaFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date fechaFin) {
          List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
          List<Movimiento> movimientos = movimientoRepository.findByCuentaClienteIdAndFechaBetween(clienteId, fechaInicio, fechaFin);
        return new ReporteDTO(cuentas, movimientos);
    }
}

class ReporteDTO {
  private List<Cuenta> cuentas;
  private List<Movimiento> movimientos;

  public ReporteDTO(List<Cuenta> cuentas, List<Movimiento> movimientos) {
      this.cuentas = cuentas;
      this.movimientos = movimientos;
  }

  // Getters y Setters
  public List<Cuenta> getCuentas() {
      return cuentas;
  }

  public void setCuentas(List<Cuenta> cuentas) {
      this.cuentas = cuentas;
  }

  public List<Movimiento> getMovimientos() {
      return movimientos;
  }

  public void setMovimientos(List<Movimiento> movimientos) {
      this.movimientos = movimientos;
  }
}

