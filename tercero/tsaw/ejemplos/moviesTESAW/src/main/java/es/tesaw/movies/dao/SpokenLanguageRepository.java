package es.tesaw.movies.dao;

import es.tesaw.movies.entity.SpokenLanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpokenLanguageRepository extends JpaRepository<SpokenLanguageEntity, Integer> {
}
