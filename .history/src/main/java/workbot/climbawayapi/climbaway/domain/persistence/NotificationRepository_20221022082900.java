package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;

import java.util.List;
@Qualifier("notificationRepository")
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Notification findById(long id);
    @Query(value ="SELECT n FROM Notification n ")
    List<Notification> getAll();

    @Query(value ="SELECT n FROM Notification n WHERE n.scalers.id = ?1")
    List<Notification> findByUserId(long id);

    //
    List<Notification> getAll(); --
    Notification findById(long id); -- 
    List<Notification> findByUserId(long id);
    Notification create(Notification notification);
    Notification update(long id, Notification notification);
    Notification delete(long id);
}
