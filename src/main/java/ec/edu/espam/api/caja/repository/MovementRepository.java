package ec.edu.espam.api.caja.repository;

import ec.edu.espam.api.caja.domain.Account;
import ec.edu.espam.api.caja.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByAccountId(Long accountId);

    List<Movement> findByAccountIdAndDate(Long accountId, LocalDate date);

    @Query(nativeQuery = true, value = "SELECT TOP 1 * FROM MOVEMENT WHERE ACCOUNT_ID = :accountId ORDER BY DATE, ID DESC")
    Optional<Movement> findByAccountOrderByDateDesc(Long accountId);

}
