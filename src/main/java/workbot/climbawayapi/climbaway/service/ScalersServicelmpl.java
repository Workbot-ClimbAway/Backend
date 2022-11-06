package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Scalers;
import workbot.climbawayapi.climbaway.domain.persistence.ScalersRepository;
import workbot.climbawayapi.climbaway.domain.service.ScalersService;
import workbot.climbawayapi.shared.exception.ResourceNotFoundException;
import workbot.climbawayapi.shared.exception.ResourceValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;


@Service
public class ScalersServicelmpl implements ScalersService {

    private final ScalersRepository scalersRepository;
    private final Validator validator;

    public ScalersServicelmpl(ScalersRepository scalersRepository, Validator validator) {
        this.scalersRepository = scalersRepository;
        this.validator = validator;
    }

    @Override
    public List<Scalers> getAll() {
        return scalersRepository.getAll();
    }

    @Override
    public Scalers findById(long id) {
        var isExisting = scalersRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("Notification not found");
        }
        return isExisting;
    }

    @Override
    public Scalers create(Scalers scalers) {
        Set<ConstraintViolation<Scalers>> violations = validator.validate(scalers);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        scalers.setNotifications(null);
        return scalersRepository.save(scalers);
    }

    @Override
    public Scalers findByEmailAndPassword(String email, String password) {
        var isExisting = scalersRepository.findByEmailAndPassword(email,password);
        if (isExisting == null) {
            throw new ResourceNotFoundException("Notification not found");
        }
        return isExisting;
    }

    @Override
    public Scalers update(long id, Scalers scalers) {
        var scalerUpdate = scalersRepository.findById(id);
        if (scalerUpdate == null) {
            throw new ResourceNotFoundException("Notification not found");
        }
        scalerUpdate.setFirstName(scalers.getFirstName());
        scalerUpdate.setLastName(scalers.getLastName());
        scalerUpdate.setPassword(scalers.getPassword());
        scalerUpdate.setCity(scalers.getCity());
        scalerUpdate.setDistrict(scalers.getDistrict());
        scalerUpdate.setUrl_photo(scalers.getUrl_photo());
        scalerUpdate.setAddress(scalers.getAddress());
        scalerUpdate.setEmail(scalers.getEmail());
        scalerUpdate.setPhone(scalers.getPhone());

        return scalersRepository.save(scalerUpdate);
    }

    @Override
    public Scalers delete(long id) {
        var isExisting = scalersRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("Notification not found");
        }
        scalersRepository.deleteById(id);
        return isExisting;
    }
}
