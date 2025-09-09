package ar.edu.utn.dds.k3003.repository;

import ar.edu.utn.dds.k3003.model.Coleccion;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface ColeccionRepository extends JpaRepository<Coleccion, Long>{
    Optional<Coleccion> findByNombre(String nombre);
    // JPA nos da por defecto findAll, findById, save, deleteById, entre otros.
}