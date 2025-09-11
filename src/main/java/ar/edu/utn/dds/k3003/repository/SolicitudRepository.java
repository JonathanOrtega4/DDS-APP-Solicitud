package ar.edu.utn.dds.k3003.repository;

import ar.edu.utn.dds.k3003.model.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, String> {

    List<Solicitud> findByHechoId(String hechoId);
}
