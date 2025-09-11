package ar.edu.utn.dds.k3003.controller;

import ar.edu.utn.dds.k3003.dtos.SolicitudDTO;
import ar.edu.utn.dds.k3003.dtos.SolicitudUpdateDTO;
import ar.edu.utn.dds.k3003.model.EstadoSolicitudEnum;
import ar.edu.utn.dds.k3003.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    @Autowired
    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDTO>> obtenerSolicitudPorHecho(@RequestParam String hecho) {
        return ResponseEntity.ok(solicitudService.buscarSolicitudXHecho(hecho));
    }

    @PostMapping
    public ResponseEntity<SolicitudDTO> crearSolicitud(@RequestBody SolicitudDTO solicitud) {
        return ResponseEntity.ok(solicitudService.agregar(solicitud));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudDTO> obtenerSolicitud(@PathVariable String id) {
        return ResponseEntity.ok(solicitudService.buscarSolicitudXId(id));
    }

    @PatchMapping
    public ResponseEntity<SolicitudDTO> actualizarSolicitud(@RequestBody SolicitudUpdateDTO solicitudUpdateDTO) {
        return ResponseEntity.ok(solicitudService.modificar(
                solicitudUpdateDTO.getId(),
                solicitudUpdateDTO.getEstado(),
                solicitudUpdateDTO.getDescripcion()
        ));
    }
}
