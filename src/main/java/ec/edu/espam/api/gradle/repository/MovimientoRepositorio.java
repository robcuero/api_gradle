package ec.edu.espam.api.gradle.repository;

import ec.edu.espam.api.gradle.domain.Cuenta;
import ec.edu.espam.api.gradle.domain.Movimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientoRepositorio extends JpaRepository<Movimento, Long> {


}
