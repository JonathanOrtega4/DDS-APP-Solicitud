package ar.edu.utn.dds.k3003.repository;

import ar.edu.utn.dds.k3003.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {

    Optional<Solicitud> findById(String id);
    List<Solicitud> findByHechoId(String id);

    Solicitud save(Solicitud solicitud);

    //Solicitud save(Solicitud solicitud);
    void delete(String id);

    List<Solicitud> findAll();
}
