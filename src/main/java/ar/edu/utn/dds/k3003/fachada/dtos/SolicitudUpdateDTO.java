package ar.edu.utn.dds.k3003.facades.dtos;

import lombok.Data;

@Data
public class SolicitudUpdateDTO {
    private String id;
    private String descripcion;
    private EstadoSolicitudBorradoEnum estado;
}
