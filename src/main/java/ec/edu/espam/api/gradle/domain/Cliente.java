package ec.edu.espam.api.gradle.domain;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity(name = "cliente")
public class Cliente extends Persona {
    @Column(name = "clave")
    @NotEmpty(message = "clave requerida")
    @Size(min = 4,message = "minimo 4 caracteres")
    private String clave;

    @Column(name = "estado")
    @NotNull(message = "estado requerida")
    private Boolean estado;

//    private List<Cuenta> cuentas;

}
