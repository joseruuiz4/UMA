package es.taw.junio2025.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Marca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMarca;

    @Column(nullable = false, unique = true)
    private String nombre;

    @OneToMany(mappedBy = "marca")
    private List<Vehiculo> vehiculos;

    @Override
    public String toString() {
        return "es.taw.junio2025.Marca[ idMarca=" + idMarca + " ]";
    }

}
