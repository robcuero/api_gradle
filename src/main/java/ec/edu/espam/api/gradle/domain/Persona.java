package ec.edu.espam.api.gradle.domain;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "identificacion")
    @NotEmpty(message = "identificacion requerida")
    private String identificacion;

    @Column(name = "genero")
    @NotEmpty(message = "genero requerida")
    private String genero;

    @Column(name = "direccion")
    @NotEmpty(message = "direccion requerida")
    private String direccion;

    @Column(name = "telefono")
    @NotEmpty(message = "telefono requerida")
    private String telefono;

    @Column(name = "edad")
    @NotNull(message = "edad requerida")
    private Integer edad;
}
