package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.S;

import java.util.List;

@Qualifier("climbingGymRepository")
@Repository
public interface ClimbingGymRepository extends JpaRepository<ClimbingGym, Long> {
    ClimbingGym findById(long id);

    @Query(value ="SELECT c FROM ClimbingGym c")
    List<ClimbingGym> getAll();

    //createClimbingGymCategory


    @Query(value ="SELECT cg FROM ClimbingGym cg Join fetch cg.categories c WHERE c.id = ?1")
    List<ClimbingGym> findClimbingGymsByCategoryId(long id);
}
public interface ScalersRepository {
}
