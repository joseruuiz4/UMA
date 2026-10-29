package es.taw.junio2025.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idServicio;

    @ManyToOne
    @JoinColumn(name = "idVehiculo", nullable = false)
    private Vehiculo vehiculo;

    @ManyToOne
    @JoinColumn(name = "idPropietario", nullable = false)
    private Propietario propietario;

    private LocalDate fecha;
    private String descripcion;
    private Double costo;

    @Override
    public String toString() {
        return "es.taw.junio2025.Servicio[ idServicio=" + idServicio + " ]";
    }
}
