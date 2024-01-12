package ec.edu.espam.api.gradle.service.impl;

import ec.edu.espam.api.gradle.domain.Cuenta;
import ec.edu.espam.api.gradle.repository.CuentaRepositorio;
import ec.edu.espam.api.gradle.service.CuentaServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CuentaServicioImpl implements CuentaServicio {
    @Autowired
    private CuentaRepositorio cuentaRepositorio;
    @Override
    public List<Cuenta> obtnerTodo() {
       return cuentaRepositorio.findAll();
    }

    @Override
    public Cuenta guardar(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    @Override
    public Cuenta modificar(Long id, Cuenta cuenta) throws Exception {
        Optional<Cuenta> cuentaOptional = cuentaRepositorio.findById(id);
        if(cuentaOptional.isPresent()){
            Cuenta cuentaOp = cuentaOptional.get();
            cuentaOp.setTipo(cuenta.getTipo());
            cuentaOp.setEstado(cuenta.getEstado());
            cuentaOp.setSaldo(cuenta.getSaldo());
            cuentaOp.setNumero(cuenta.getNumero());
            cuentaOp.setBalanceInicial(cuenta.getBalanceInicial());
            return cuentaRepositorio.save(cuentaOp);
        }else{
            throw new Exception("No se encuentra la cuenta");
        }

    }
}
