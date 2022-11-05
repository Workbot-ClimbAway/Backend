package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.Image;

import java.util.List;

@Qualifier("imageRepository")
@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findById(long id);

    @Query(value ="SELECT i FROM Image i")
    List<Image> getAll();

    @Query(value ="SELECT i FROM Image i WHERE i.climbingGymId = ?1")
    List<Image> findByClimbingGymId(long climbingGymId);
}
