package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.model.Coleccion;
import ar.edu.utn.dds.k3003.service.ColeccionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/colecciones")
public class ColeccionController {

    private final ColeccionService service;

    public ColeccionController(ColeccionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Coleccion> getTodas() {
        return service.obtenerTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coleccion> getPorId(@PathVariable Long id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<Coleccion> getPorNombre(@PathVariable String nombre) {
        return service.obtenerPorNombre(nombre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Coleccion crear(@RequestBody Coleccion coleccion) {
        return service.guardar(coleccion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/todas")
    public ResponseEntity<Void> eliminarTodas() {
        service.eliminarTodas();
        return ResponseEntity.noContent().build();
    }
}
