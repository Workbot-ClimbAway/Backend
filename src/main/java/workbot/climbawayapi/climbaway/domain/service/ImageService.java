package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Image;

import java.util.List;

@Service
public interface ImageService {
    Image create(Image image, long climbingGymId);
    Image delete(long id);
    Image findById(long id);
    List<Image> getAll();
    List<Image> findByClimbingGymId(long climbingGymId);
}
