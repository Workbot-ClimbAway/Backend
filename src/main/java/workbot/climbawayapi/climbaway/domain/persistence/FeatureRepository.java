package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.Feature;

import java.util.List;

@Qualifier("featureRepository")
@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    Feature findById(long id);

    //get all
    @Query(value ="SELECT f FROM Feature f")
    List<Feature> getAll();

    //find by climbingGymId
    @Query(value ="SELECT f FROM Feature f WHERE f.climbingGym.id = ?1")
    List<Feature> findByClimbingGymId(long climbingGymId);
}
