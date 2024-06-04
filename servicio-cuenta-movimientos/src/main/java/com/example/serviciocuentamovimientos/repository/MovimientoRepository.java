package com.example.serviciocuentamovimientos.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.serviciocuentamovimientos.entity.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
  List<Movimiento> findByCuentaClienteIdAndFechaBetween(Long clienteId, Date fechaInicio, Date fechaFin);
}
