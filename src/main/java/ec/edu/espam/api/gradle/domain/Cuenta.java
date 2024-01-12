package ec.edu.espam.api.gradle.domain;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "cuenta")
public class Cuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "numero")
    @NotEmpty(message = "numero requerida")
    private String numero;

    @Column(name = "TipoCuenta")
    @NotNull(message = "TipoCuenta requerida")
    @Enumerated(EnumType.STRING)
    private TipoCuenta tipo;

    @Column(name = "balanceInicial")
    @NotNull(message = "balanceInicial requerida")
    private BigDecimal balanceInicial;

    @Column(name = "saldo")
    @NotNull(message = "saldo requerida")
    private BigDecimal saldo;

    @Column(name = "estado")
    @NotNull(message = "estado requerida")
    private Boolean estado;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id",nullable = false)
//    private Cliente client;

//    @OneToMany(mappedBy = "cuenta")
//    private List<Movimento> movimientos;
    public enum TipoCuenta{
        AHORRO,CORRIENTE
    }
}
