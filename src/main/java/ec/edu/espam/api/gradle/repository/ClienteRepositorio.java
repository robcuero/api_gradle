package ec.edu.espam.api.gradle.repository;

import ec.edu.espam.api.gradle.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {


}
