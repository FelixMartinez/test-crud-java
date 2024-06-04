package com.example.serviciocuentamovimientos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.serviciocuentamovimientos.entity.Cuenta;
import com.example.serviciocuentamovimientos.entity.Movimiento;
import com.example.serviciocuentamovimientos.repository.CuentaRepository;
import com.example.serviciocuentamovimientos.repository.MovimientoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {
    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Optional<Movimiento> getMovimientoById(Long id) {
        return movimientoRepository.findById(id);
    }

    public Movimiento createMovimiento(Movimiento movimiento) {
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
    }

    public Movimiento updateMovimiento(Long id, Movimiento movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id).orElseThrow();
        movimiento.setFecha(movimientoDetails.getFecha());
        movimiento.setTipoMovimiento(movimientoDetails.getTipoMovimiento());
        movimiento.setValor(movimientoDetails.getValor());
        movimiento.setSaldo(movimientoDetails.getSaldo());
        return movimientoRepository.save(movimiento);
    }

    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}
