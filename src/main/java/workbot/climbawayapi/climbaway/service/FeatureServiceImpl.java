package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Feature;
import workbot.climbawayapi.climbaway.domain.persistence.ClimbingGymRepository;
import workbot.climbawayapi.climbaway.domain.persistence.FeatureRepository;
import workbot.climbawayapi.climbaway.domain.service.FeatureService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class FeatureServiceImpl implements FeatureService {
    private final FeatureRepository featureRepository;
    private final ClimbingGymRepository climbingGymRepository;
    private final Validator validator;

    public FeatureServiceImpl(FeatureRepository featureRepository, ClimbingGymRepository climbingGymRepository, Validator validator) {
        this.featureRepository = featureRepository;
        this.climbingGymRepository = climbingGymRepository;
        this.validator = validator;
    }

    @Override
    public Feature findById(long id) {
        return featureRepository.findById(id);
    }

    @Override
    public List<Feature> getAll() {
        return featureRepository.getAll();
    }

    @Override
    public Feature create(Feature feature, long climbingGymId) {
        var isExistingClimbingGym = climbingGymRepository.findById(climbingGymId);
        if (isExistingClimbingGym == null) {
            throw new IllegalArgumentException("Climbing gym does not exist");
        }
        var isExistingFeature = featureRepository.findByClimbingGymId(climbingGymId);
        if (isExistingFeature.size() > 0) {
            throw new IllegalArgumentException("Feature already exists");
        }
        feature.setClimbingGym(isExistingClimbingGym);
        Set<ConstraintViolation<Feature>> violations = validator.validate(feature);
        if (!violations.isEmpty()) {
            throw new RuntimeException("Feature is not valid");
        }
        return featureRepository.save(feature);
    }

    @Override
    public Feature update(Feature feature, long climbingGymId) {
        var isExistingClimbingGym = climbingGymRepository.findById(climbingGymId);
        if (isExistingClimbingGym == null) {
            throw new IllegalArgumentException("Climbing gym does not exist");
        }
        var isExistingFeature = featureRepository.findByClimbingGymId(climbingGymId);
        if (isExistingFeature == null) {
            throw new IllegalArgumentException("Feature does not exist");
        }
        var updatedFeature = isExistingFeature.get(0);

        updatedFeature.setType_climb(feature.getType_climb());
        updatedFeature.setStart_office_hours(feature.getStart_office_hours());
        updatedFeature.setEnd_office_hours(feature.getEnd_office_hours());
        updatedFeature.setDays_of_week(feature.getDays_of_week());
        updatedFeature.setRoutes(feature.getRoutes());
        updatedFeature.setMax_height(feature.getMax_height());
        updatedFeature.setRock_type(feature.getRock_type());
        updatedFeature.setBolting(feature.getBolting());
        updatedFeature.setPrice(feature.getPrice());

        return featureRepository.save(feature);
    }

    @Override
    public Feature delete(long id) {
        var isExistingFeature = featureRepository.findById(id);
        if (isExistingFeature == null) {
            throw new IllegalArgumentException("Feature does not exist");
        }
        featureRepository.delete(isExistingFeature);
        return isExistingFeature;
    }
}
