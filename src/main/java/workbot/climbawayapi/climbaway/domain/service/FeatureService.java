package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Feature;

import java.util.List;

@Service
public interface FeatureService {
    Feature findById(long id);
    List<Feature> getAll();
    Feature create(Feature feature, long climbingGymId);
    Feature update(Feature feature, long climbingGymId);
    Feature delete(long id);
}
