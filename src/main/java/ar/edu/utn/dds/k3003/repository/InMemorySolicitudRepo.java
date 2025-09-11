package ar.edu.utn.dds.k3003.repository;

import ar.edu.utn.dds.k3003.model.Solicitud;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Profile("test")
public class InMemorySolicitudRepo implements SolicitudRepository {

    private final List<Solicitud> solicitudes;

    public InMemorySolicitudRepo() {
        this.solicitudes = new ArrayList<>();
    }

    @Override
    public Optional<Solicitud> findById(String id) {
        return this.solicitudes.stream()
                .filter(s -> Objects.equals(s.getId(), id))
                .findFirst();
    }

    @Override
    public List<Solicitud> findByHechoId(String hechoId) {
        return this.solicitudes.stream()
                .filter(s -> Objects.equals(s.getHechoId(), hechoId))
                .toList();
    }

    @Override
    public Solicitud save(Solicitud solicitud) {
        this.solicitudes.removeIf(s -> Objects.equals(s.getId(), solicitud.getId()));
        this.solicitudes.add(solicitud);
        return solicitud;
    }

    @Override
    public void delete(String id) {
        Optional<Solicitud> solicitudOptional = findById(id);
        if (solicitudOptional.isEmpty()) {
            throw new IllegalArgumentException("La solicitud con ID " + id + " no existe");
        }
        this.solicitudes.remove(solicitudOptional.get());
    }

    @Override
    public List<Solicitud> findAll() {
        return new ArrayList<>(this.solicitudes);
    }
}