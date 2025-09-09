package ar.edu.utn.dds.k3003.service;

import ar.edu.utn.dds.k3003.model.Coleccion;
import ar.edu.utn.dds.k3003.repository.ColeccionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ColeccionService {

    private final ColeccionRepository coleccionRepository;

    public ColeccionService(ColeccionRepository coleccionRepository) {
        this.coleccionRepository = coleccionRepository;
    }

    public Optional<Coleccion> obtenerPorId(Long id) {
        return coleccionRepository.findById(id);
    }

    public Optional<Coleccion> obtenerPorNombre(String nombre) {
        return coleccionRepository.findByNombre(nombre);
    }

    public Coleccion guardar(Coleccion coleccion) {
        coleccion.setFechaModificacion(LocalDateTime.now());
        return coleccionRepository.save(coleccion);
    }

    public void eliminarPorId(Long id) {
        coleccionRepository.deleteById(id);
    }

    public void eliminarTodas() {
        coleccionRepository.deleteAll();
    }

    public List<Coleccion> obtenerTodas() {
        return coleccionRepository.findAll();
    }
}
