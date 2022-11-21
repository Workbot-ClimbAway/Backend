package workbot.climbawayapi.security.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Qualifier("scalersRepository")
@Repository
public interface ScalersRepository extends JpaRepository<Scalers, Long> {

    Scalers findById(long id);
    Scalers findByEmail(String email);

    @Query(value ="SELECT s FROM Scalers s ")
    List<Scalers> getAll();

    @Query(value ="SELECT s FROM Scalers s WHERE s.email = ?1 AND s.password = ?2")
    Scalers findByEmailAndPassword(String email, String password);


}
