package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.Scalers;

import java.util.List;

@Qualifier("scalersRepository")
@Repository
public interface ScalersRepository extends JpaRepository<ClimbingGym, Long> {
    Scalers findById(long id);
    @Query(value ="SELECT s FROM Scalers s ")
    List<Scalers> getAll();

    @Query(value ="SELECT s FROM Scalers s WHERE s.email = ?1")
    Boolean findByEmail(String email);

    @Query(value ="SELECT s FROM Scalers s WHERE s.email = ?1 AND s.password = ?2")
    Scalers findByEmailAndPassword(String email, String password);

    @Query(value ="SELECT s FROM Scalers s WHERE s.email = ?1 AND s.password = ?2")
    Boolean findByEmailAndPasswordBoolean(String email, String password);

    
    @Query(value ="SELECT c FROM ClimbingGym c")
    List<ClimbingGym> getAll();

    //createClimbingGymCategory


    @Query(value ="SELECT cg FROM ClimbingGym cg Join fetch cg.categories c WHERE c.id = ?1")
    List<ClimbingGym> findClimbingGymsByCategoryId(long id);


    ///
    List<Scalers> getAll(); // Get all scalers to list
    Scalers findById(long id); 
    Scalers create(Scalers scalers); // ADD SCALER
    Boolean findByEmail(String email);
    Scalers findByEmailAndPassword(String email, String password);
    Scalers update(long id, Scalers scalers);
    Scalers delete(long id);
}
