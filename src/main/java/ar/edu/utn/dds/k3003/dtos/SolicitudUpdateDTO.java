package ar.edu.utn.dds.k3003.dtos;

import ar.edu.utn.dds.k3003.model.EstadoSolicitudEnum;
import lombok.Data;

@Data
public class SolicitudUpdateDTO {
    private String id;
    private String descripcion;
    private EstadoSolicitudEnum estado;
}
