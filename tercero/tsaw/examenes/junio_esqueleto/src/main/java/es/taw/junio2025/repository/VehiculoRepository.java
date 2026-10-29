package es.taw.junio2025.repository;

import es.taw.junio2025.entity.Servicio;
import es.taw.junio2025.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    @Query("select s from Vehiculo v join v.servicios s where v.marca.idMarca = ?1 and v.modelo in (?2)")
    public List<Servicio> serviciosPorMarcaYVehiculo(Integer marcaID, List<String> vehiculos);



}
