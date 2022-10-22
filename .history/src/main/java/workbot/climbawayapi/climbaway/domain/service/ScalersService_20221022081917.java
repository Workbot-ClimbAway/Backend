package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;


import workbot.climbawayapi.climbaway.domain.model.entity.Notification;


import workbot.climbawayapi.climbaway.domain.model.entity.Scalers;

import java.util.List;

@Service
public interface ScalersService {
    List<ClimbingGym> getAll();
    List<ClimbingGym> findClimbingGymsByCategoryId(long id);
    ClimbingGym findById(long id);
    ClimbingGym create(ClimbingGym climbingGym);
    List<Category> createClimbingGymCategory(long id, long categoryId);
    ClimbingGym update(long id, ClimbingGym climbingGym);
    ClimbingGym delete(long id);
}
