package es.taw.junio2025.repository;

import es.taw.junio2025.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {


    @Query("select s from Servicio s join s.vehiculo v where v.marca.idMarca = ?1 and v.modelo in (?2) ")
    public List<Servicio> serviciosPorMarcaYVehiculo(Integer marcaID, List<String> vehiculos);

    List<Servicio> findByVehiculo_Marca_IdMarca(Integer vehiculoMarcaIdMarca);
}
