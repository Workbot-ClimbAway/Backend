package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;

import java.util.List;

@Service
public interface NotificationService {
    List<Notification> getAll();
    Notification findById(long id); 
    List<Notification> findByScalerId(long id);
    Notification create(Notification notification, long scalerId);
    Notification delete(long id);
}
