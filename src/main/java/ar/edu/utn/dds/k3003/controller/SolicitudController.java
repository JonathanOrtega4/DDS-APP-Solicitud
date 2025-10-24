package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.facades.FachadaSolicitudes;
import ar.edu.utn.dds.k3003.facades.dtos.EstadoSolicitudBorradoEnum;
import ar.edu.utn.dds.k3003.facades.dtos.SolicitudDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
    GET /solicitudes?hecho={hechoId}
    POST /solicitudes
    GET /solicitudes/{id}
    PATCH /solicitudes
*/

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {
    
    private static final Logger logger = LoggerFactory.getLogger(SolicitudController.class);
    private final FachadaSolicitudes fachadaSolicitudes;

    @Autowired
    public SolicitudController(FachadaSolicitudes fachadaSolicitudes) {
        this.fachadaSolicitudes = fachadaSolicitudes;
    }

    // GET /solicitudes?hecho={hechoId}
    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudPorHecho(@RequestParam String hecho) {
        return ResponseEntity.ok(fachadaSolicitudes.buscarSolicitudXHecho(hecho));
    }

    // POST /solicitudes
    @PostMapping
    public ResponseEntity<?> crearSolicitud(@RequestBody SolicitudDTO solicitud) {
        try {
            logger.info("Intento crear solicitud: {}", solicitud);
            SolicitudDTO creada = fachadaSolicitudes.agregar(solicitud);
            logger.info("Solicitud creada con éxito: {}", creada);
            return ResponseEntity.ok(creada);
        } catch (Exception e) {
            logger.error("Error al crear solicitud: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear solicitud: " + e.getMessage());
        }
    }

    // GET /solicitudes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerSolicitud(@PathVariable String id) {
        return ResponseEntity.ok(fachadaSolicitudes.buscarSolicitudXId(id));
    }

    @PatchMapping
    public ResponseEntity<SolicitudDTO> actualizarSolicitud(@RequestBody String solicitudId,
                                                            EstadoSolicitudBorradoEnum estado,
                                                            String descripcion) {
        return ResponseEntity.ok(fachadaSolicitudes.modificar(solicitudId, estado, descripcion));
    }


    // GET /Activos?hechoId={hechoId}
    @GetMapping("/Activos")
    public ResponseEntity<List<SolicitudDTO>> obtenerAprobadasPorHecho(@RequestParam("hechoId") String hechoId) {
        List<SolicitudDTO> todas = fachadaSolicitudes.buscarSolicitudXHecho(hechoId);
        List<SolicitudDTO> soloAprobadas = todas.stream()
                .filter(s -> s.estado() != EstadoSolicitudBorradoEnum.ACEPTADA)
                .toList();
        return ResponseEntity.ok(soloAprobadas);
    }
}