package es.taw.movies.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import es.taw.movies.entity.People;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PeopleRepository extends JpaRepository<People, Integer> {



    @Query("select distinct p from People p left join p.movieCastList cast left join p.movieCrewList crew where cast.characterName like concat('%', :filtro, '%') or (crew.job like concat('%', :filtro, '%'))")
    public List<People> filtrarPorNombre(@Param("filtro") String filtro);

    @Query("select p from People p join p.movieCastList cast where cast.characterName like concat('%', :filtro, '%')")
    public List<People> filtrarPorNombreReparto(@Param("filtro") String filtro);

    @Query("select p from People p join p.movieCrewList crew where crew.job like concat('%', :filtro, '%')")
    public List<People> filtrarPorNombreTrabajo(@Param("filtro") String filtro);




}
