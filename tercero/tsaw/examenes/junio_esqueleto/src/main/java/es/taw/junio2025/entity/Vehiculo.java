package es.taw.junio2025.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVehiculo;

    private String modelo;
    private Integer anio;

    @ManyToOne
    @JoinColumn(name = "idMarca", nullable = false)
    private Marca marca;

    @ManyToMany
    @JoinTable(
        name = "vehiculo_propietario",
        joinColumns = @JoinColumn(name = "idVehiculo"),
        inverseJoinColumns = @JoinColumn(name = "idPropietario")
    )
    private List<Propietario> propietarios;

    @OneToMany(mappedBy = "vehiculo")
    private List<Servicio> servicios;

    @Override
    public String toString() {
        return "es.taw.junio2025.Vehiculo[ idVehiculo=" + idVehiculo + " ]";
    }

    public String getPropietariosNombre() {
        String str = "";
        for (Propietario prop : propietarios) {
            str += prop.getNombre() + " | ";
        }
        return str;
    }

}
