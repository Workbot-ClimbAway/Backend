package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;

import java.util.List;
@Qualifier("categoryRepository")
@Repository
public interface NotificationRepository extends JpaRepository<Category, Long> {
    Category findById(long id);

    @Query(value ="SELECT c FROM Category c")
    List<Category> getAll();

    @Query(value ="SELECT c FROM Category c Join fetch c.climbingGyms cg WHERE cg.id = ?1")
    List<Category> findByClimbingGymId(long id);

}
public interface NotificationRepository {
}
