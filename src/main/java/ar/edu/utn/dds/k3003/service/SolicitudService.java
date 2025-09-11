package ar.edu.utn.dds.k3003.service;

import ar.edu.utn.dds.k3003.dtos.SolicitudDTO;
import ar.edu.utn.dds.k3003.model.EstadoSolicitudEnum;
import ar.edu.utn.dds.k3003.model.Solicitud;
import ar.edu.utn.dds.k3003.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    @Autowired
    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public List<SolicitudDTO> buscarSolicitudXHecho(String hechoId) {
        return solicitudRepository.findByHechoId(hechoId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public SolicitudDTO agregar(SolicitudDTO dto) {
        Solicitud solicitud = new Solicitud(
                dto.getId(),
                dto.getDescripcion(),
                dto.getEstado() != null ? dto.getEstado() : EstadoSolicitudEnum.CREADA,
                dto.getHechoId()
        );
        return toDTO(solicitudRepository.save(solicitud));
    }

    public SolicitudDTO buscarSolicitudXId(String id) {
        Optional<Solicitud> solicitud = solicitudRepository.findById(id);
        return solicitud.map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + id));
    }

    public SolicitudDTO modificar(String id, EstadoSolicitudEnum estado, String descripcion) {
        Solicitud solicitud = solicitudRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada con id: " + id));

        if (estado != null) {
            solicitud.setEstado(estado);
        }
        if (descripcion != null && !descripcion.isBlank()) {
            solicitud.setDescripcion(descripcion);
        }

        return toDTO(solicitudRepository.save(solicitud));
    }

    private SolicitudDTO toDTO(Solicitud solicitud) {
        SolicitudDTO dto = new SolicitudDTO();
        dto.setId(solicitud.getId());
        dto.setDescripcion(solicitud.getDescripcion());
        dto.setEstado(solicitud.getEstado());
        dto.setHechoId(solicitud.getHechoId());
        return dto;
    }
}
