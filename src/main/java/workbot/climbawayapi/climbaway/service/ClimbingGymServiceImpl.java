package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.ClimbingGym;
import workbot.climbawayapi.climbaway.domain.persistence.ClimbingGymRepository;
import workbot.climbawayapi.climbaway.domain.service.ClimbingGymService;
import workbot.climbawayapi.shared.exception.ResourceNotFoundException;
import workbot.climbawayapi.shared.exception.ResourceValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;


@Service
public class ClimbingGymServiceImpl implements ClimbingGymService {

    private final ClimbingGymRepository climbingGymRepository;
    private final Validator validator;

    public ClimbingGymServiceImpl(ClimbingGymRepository climbingGymRepository, Validator validator) {
        this.climbingGymRepository = climbingGymRepository;
        this.validator = validator;
    }

    @Override
    public List<ClimbingGym> getAll() {
        return climbingGymRepository.getAll();
    }

    @Override
    public ClimbingGym findById(long id) {
        var isExisting = climbingGymRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("ClimbingGym", id);
        }
        return climbingGymRepository.findById(id);
    }

    @Override
    public ClimbingGym create(ClimbingGym climbingGym) {
        Set<ConstraintViolation<ClimbingGym>> violations = validator.validate(climbingGym);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return climbingGymRepository.save(climbingGym);
    }

    @Override
    public ClimbingGym update(long id, ClimbingGym climbingGym) {
        var isExisting = climbingGymRepository.findById(id);

        if (isExisting == null) {
            throw new ResourceNotFoundException("ClimbingGym", id);
        }
        ClimbingGym climbingGymToUpdate = climbingGymRepository.findById(id);

        climbingGymToUpdate.setName(climbingGym.getName());
        climbingGymToUpdate.setPassword(climbingGym.getPassword());
        climbingGymToUpdate.setEmail(climbingGym.getEmail());
        climbingGymToUpdate.setCity(climbingGym.getCity());
        climbingGymToUpdate.setDistrict(climbingGym.getDistrict());
        climbingGymToUpdate.setAddress(climbingGym.getAddress());
        climbingGymToUpdate.setPhone(climbingGym.getPhone());
        climbingGymToUpdate.setLogo_url(climbingGym.getLogo_url());

        climbingGymRepository.save(climbingGymToUpdate);
        return isExisting;
    }

    @Override
    public ClimbingGym delete(long id) {
        var isExisting = climbingGymRepository.findById(id);

        if (isExisting == null) {
            throw new ResourceNotFoundException("ClimbingGym", id);
        }

        climbingGymRepository.deleteById(id);
        return isExisting;
    }
}
