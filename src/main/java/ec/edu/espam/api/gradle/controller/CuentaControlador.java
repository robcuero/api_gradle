package ec.edu.espam.api.gradle.controller;

import ec.edu.espam.api.gradle.domain.Cuenta;
import ec.edu.espam.api.gradle.service.CuentaServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value ="/cuentas",produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class CuentaControlador {
    private final CuentaServicio cuentaServicio;

    @GetMapping
    public ResponseEntity<List<Cuenta>> obtenerTodos() {
        return ResponseEntity.ok(cuentaServicio.obtnerTodo());
    }

    @PostMapping
    public ResponseEntity<Cuenta> guardarCuenta(@RequestBody @Valid Cuenta cuenta) {
        return new ResponseEntity<>(cuentaServicio.guardar(cuenta), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cuenta> modificarCuenta(@PathVariable(value = "id") Long id, @RequestBody @Valid Cuenta cuenta) throws Exception {
        return new ResponseEntity<>(cuentaServicio.modificar(id,cuenta),HttpStatus.OK);
    }

}
