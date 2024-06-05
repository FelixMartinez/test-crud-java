package com.example.serviciocuentamovimientos.dto;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.entity.Movimiento;

import java.util.List;

/**
 * Data Transfer Object for the account report.
 */
public class ReporteDTO {

    private List<Cuenta> cuentas;
    private List<Movimiento> movimientos;

    public ReporteDTO(List<Cuenta> cuentas, List<Movimiento> movimientos) {
        this.cuentas = cuentas;
        this.movimientos = movimientos;
    }

    // Getters and Setters
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
