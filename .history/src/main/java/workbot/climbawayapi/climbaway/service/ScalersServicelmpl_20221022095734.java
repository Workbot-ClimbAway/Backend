package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;
import workbot.climbawayapi.climbaway.domain.model.entity.S;
import workbot.climbawayapi.climbaway.domain.persistence.NotificationRepository;
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
public class ScalersServicelmpl implements ClimbingGymService {

    private final ClimbingGymRepository climbingGymRepository;
    private final CategoryRepository categoryRepository;
    private final Validator validator;

    public ClimbingGymServiceImpl(ClimbingGymRepository climbingGymRepository,
                                  CategoryRepository categoryRepository,
                                  Validator validator) {
        this.climbingGymRepository = climbingGymRepository;
        this.categoryRepository = categoryRepository;
        this.validator = validator;
    }

    @Override
    public List<ClimbingGym> getAll() {
        return climbingGymRepository.getAll();
    }

    @Override
    public List<ClimbingGym> findClimbingGymsByCategoryId(long id) {
        var isExisting = categoryRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("Category not found");
        }
        return climbingGymRepository.findClimbingGymsByCategoryId(id);
    }

    @Override
    public ClimbingGym findById(long id) {
        var isExisting = climbingGymRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("ClimbingGym not found");
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
    public List<Category> createClimbingGymCategory(long id, long categoryId) {
        var climbingGym = climbingGymRepository.findById(id);
        if (climbingGym == null) {
            throw new ResourceNotFoundException("ClimbingGym not found");
        }
        var category = categoryRepository.findById(categoryId);
        if (category == null) {
            throw new ResourceNotFoundException("Category not found");
        }

        if (climbingGym.getCategories().contains(category)) {
            throw new ResourceNotFoundException("Category already exists");
        }
        Set<Category> categories = climbingGym.getCategories();
        categories.add(category);
        climbingGym.setCategories(categories);
        climbingGymRepository.save(climbingGym);
        return categoryRepository.findByClimbingGymId(id);
    }

    @Override
    public ClimbingGym update(long id, ClimbingGym climbingGym) {
        var climbingGymToUpdate = climbingGymRepository.findById(id);

        if (climbingGymToUpdate == null) {
            throw new ResourceNotFoundException("ClimbingGym not found");
        }

        climbingGymToUpdate.setName(climbingGym.getName());
        climbingGymToUpdate.setPassword(climbingGym.getPassword());
        climbingGymToUpdate.setEmail(climbingGym.getEmail());
        climbingGymToUpdate.setCity(climbingGym.getCity());
        climbingGymToUpdate.setDistrict(climbingGym.getDistrict());
        climbingGymToUpdate.setAddress(climbingGym.getAddress());
        climbingGymToUpdate.setPhone(climbingGym.getPhone());
        climbingGymToUpdate.setLogo_url(climbingGym.getLogo_url());

        climbingGymRepository.save(climbingGymToUpdate);
        return climbingGymToUpdate;
    }

    @Override
    public ClimbingGym delete(long id) {
        var isExisting = climbingGymRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("ClimbingGym not found");
        }
        climbingGymRepository.deleteById(id);
        return isExisting;
    }
}
