package ec.edu.espam.api.gradle.service;

import ec.edu.espam.api.gradle.domain.Cuenta;

import java.util.List;

public interface CuentaServicio {
    List<Cuenta> obtnerTodo();
    Cuenta guardar(Cuenta cuenta);
    Cuenta modificar(Long id, Cuenta cuenta) throws Exception;

}
