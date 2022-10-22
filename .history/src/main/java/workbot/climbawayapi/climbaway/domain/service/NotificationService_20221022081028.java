package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;

import java.util.List;

@Service
public interface NotificationService {

    List<Notification> getAll();

    List<Category> getAll();
    Category findById(long id);
    List<Category> findByClimbingGymId(long id);
    Category create(Category category);
    Category update(long id, Category category);
    Category delete(long id);

}
