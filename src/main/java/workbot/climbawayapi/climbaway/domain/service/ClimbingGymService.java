package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.ClimbingGym;

import java.util.List;

@Service
public interface ClimbingGymService {
    List<ClimbingGym> getAll();
    ClimbingGym findById(long id);
    ClimbingGym create(ClimbingGym climbingGym);
    ClimbingGym update(long id, ClimbingGym climbingGym);
    ClimbingGym delete(long id);
}
