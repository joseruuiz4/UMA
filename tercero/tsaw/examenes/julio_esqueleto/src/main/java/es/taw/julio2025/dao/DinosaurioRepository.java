package es.taw.julio2025.dao;

import es.taw.julio2025.entity.Dinosaurio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DinosaurioRepository extends JpaRepository<Dinosaurio, Integer> {


    @Query("select d from Dinosaurio d join d.habitats h where h.id = ?1 and d.tamaño_metros > ?2 and d.peso_toneladas < ?3")
    public List<Dinosaurio> filtroHabitatTamanyoPeso(Integer idHabitat, Float tamanyo, Float peso);

    @Query("select d from Dinosaurio d join d.habitats h where h.id = ?1 and d.tamaño_metros > ?2 ")
    public List<Dinosaurio> filtroHabitatTamanyo(Integer idHabitat, Float tamanyo);

    @Query("select d from Dinosaurio d join d.habitats h where h.id = ?1 and d.peso_toneladas < ?2")
    public List<Dinosaurio> filtroHabitatPeso(Integer idHabitat, Float peso);

    @Query("select d from Dinosaurio d join d.habitats h where d.tamaño_metros > ?1 and d.peso_toneladas < ?2")
    public List<Dinosaurio> filtroTamanyoPeso(Float tamanyo, Float peso);

    @Query("select d from Dinosaurio d join d.habitats h where h.id = ?1")
    public List<Dinosaurio> filtroHabitat(Integer idHabitat);

    @Query("select d from Dinosaurio d join d.habitats h where d.tamaño_metros > ?1")
    public List<Dinosaurio> filtroTamanyo(Float tamanyo);

    @Query("select d from Dinosaurio d join d.habitats h where d.peso_toneladas < ?1")
    public List<Dinosaurio> filtroPeso(Float peso);

}
