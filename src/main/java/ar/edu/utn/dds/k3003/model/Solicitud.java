package ar.edu.utn.dds.k3003.model;

import ar.edu.utn.dds.k3003.facades.dtos.EstadoSolicitudBorradoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Solicitud {

    // Constructor sin args (requerido por JPA/Hibernate)
    public Solicitud() {
    }

    // Constructor con campos
    public Solicitud(String id, String descripcion, EstadoSolicitudBorradoEnum estado, String hechoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.hechoId = hechoId;
    }

    @Id
    private String id;
    private String descripcion;
    private EstadoSolicitudBorradoEnum estado;
    private String hechoId;

}
