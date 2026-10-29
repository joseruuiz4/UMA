package es.tesaw.movies.dao;

import es.tesaw.movies.entity.UserEditorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserEditorRepository extends JpaRepository<UserEditorEntity, Integer> {

    @Query("select u from UserEditorEntity u where u.username = ?1 and u.password = ?2")
    public UserEditorEntity autheticate (String username, String password);


}
