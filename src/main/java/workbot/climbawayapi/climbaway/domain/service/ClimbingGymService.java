package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Category;
import workbot.climbawayapi.climbaway.domain.model.entity.ClimbingGym;

import java.util.List;

@Service
public interface ClimbingGymService {
    List<ClimbingGym> getAll();
    List<ClimbingGym> findClimbingGymsByCategoryId(long id);
    ClimbingGym findById(long id);
    ClimbingGym create(ClimbingGym climbingGym);
    List<Category> createClimbingGymCategory(long id, long categoryId);
    ClimbingGym update(long id, ClimbingGym climbingGym);
    ClimbingGym delete(long id);
    //Favorites
    List<ClimbingGym> findClimbingGymsByScalerId(long id);
    List<ClimbingGym> createClimbingGymScaler(long id, long scalerId);
    List<ClimbingGym> deleteClimbingGymScaler(long id, long scalerId);
}
