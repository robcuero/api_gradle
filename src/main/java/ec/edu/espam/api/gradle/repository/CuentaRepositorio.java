package ec.edu.espam.api.gradle.repository;

import ec.edu.espam.api.gradle.domain.Cliente;
import ec.edu.espam.api.gradle.domain.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepositorio extends JpaRepository<Cuenta, Long> {


}
