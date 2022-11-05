package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Image;
import workbot.climbawayapi.climbaway.domain.persistence.ClimbingGymRepository;
import workbot.climbawayapi.climbaway.domain.persistence.ImageRepository;
import workbot.climbawayapi.climbaway.domain.service.ImageService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final ClimbingGymRepository climbingGymRepository;
    private final Validator validator;

    public ImageServiceImpl(ImageRepository imageRepository, ClimbingGymRepository climbingGymRepository, Validator validator) {
        this.imageRepository = imageRepository;
        this.climbingGymRepository = climbingGymRepository;
        this.validator = validator;
    }

    @Override
    public Image create(Image image, long climbingGymId) {
        var isExist = climbingGymRepository.findById(climbingGymId);
        if(isExist == null){
            throw new RuntimeException("Climbing gym not found");
        }
        image.setClimbingGymId(climbingGymId);
        Set<ConstraintViolation<Image>> violations = validator.validate(image);
        if (!violations.isEmpty()) {
            throw new RuntimeException("Image is not valid");
        }

        return imageRepository.save(image);
    }

    @Override
    public Image delete(long id) {
        var image = imageRepository.findById(id);
        if(image == null){
            throw new RuntimeException("Image not found");
        }
        imageRepository.delete(image);
        return image;
    }

    @Override
    public Image findById(long id) {
        return imageRepository.findById(id);
    }

    @Override
    public List<Image> getAll() {
        return imageRepository.getAll();
    }

    @Override
    public List<Image> findByClimbingGymId(long climbingGymId) {
        return imageRepository.findByClimbingGymId(climbingGymId);
    }
}
