package ec.edu.espam.api.gradle.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "movimiento")
public class Movimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "fecha")
    @NotNull(message = "fecha requerida")
    private LocalDate fecha;

    @Column(name = "tipo")
    @NotNull(message = "tipo requerida")
    @Enumerated(EnumType.STRING)
    private TipoMovimiento tipo;

    @Column(name = "monto")
    @NotNull(message = "monto requerida")
    private BigDecimal monto;

    @Column(name = "balance")
    @NotNull(message = "balance requerida")
    private BigDecimal balance;

//    @NotNull(message = "cuenta requerida")
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Cuenta cuenta;
    private enum TipoMovimiento{
        DEBITO,CREDITO
    }
}
