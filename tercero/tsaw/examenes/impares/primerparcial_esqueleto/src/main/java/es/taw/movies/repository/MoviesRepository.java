package es.taw.movies.repository;

import es.taw.movies.entity.Movies;
import es.taw.movies.entity.ProductionCompanies;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MoviesRepository extends JpaRepository<Movies, Integer> {

    @Query("select m from Movies m join m.productionCompaniesList p where p.id in :checkedProductionCompanies")
    public List<Movies> filtrarPorProductionCompany(@Param("checkedProductionCompanies") List<Integer> checkedProductionCompanies);

}
