package ar.edu.utn.dds.k3003.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
public class Solicitud {
    public Solicitud() {
    }

    // Constructor con campos
    public Solicitud(String id, String descripcion, EstadoSolicitudEnum estado, String hechoId) {
        this.id = id;
        this.descripcion = descripcion;
        this.estado = estado;
        this.hechoId = hechoId;
    }

    @Id
    private String id;

    private String descripcion;

    @Enumerated(EnumType.STRING)
    private EstadoSolicitudEnum estado;

    private String hechoId;
}
