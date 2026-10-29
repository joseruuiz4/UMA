package es.taw.junio2025.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Propietario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPropietario;

    private String nombre;
    private String direccion;
    private String telefono;

    @ManyToMany(mappedBy = "propietarios")
    private List<Vehiculo> vehiculos;

    @OneToMany(mappedBy = "propietario")
    private List<Servicio> servicios;

    @Override
    public String toString() {
        return "es.taw.junio2025.Propietario[ idPropietario=" + idPropietario + " ]";
    }
}
