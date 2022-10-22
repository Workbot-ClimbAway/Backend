package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Notification;
import workbot.climbawayapi.climbaway.domain.persistence.NotificationRepository;
import workbot.climbawayapi.climbaway.domain.persistence.ScalersRepository;
import workbot.climbawayapi.climbaway.domain.service.NotificationService;
import workbot.climbawayapi.shared.exception.ResourceNotFoundException;
import workbot.climbawayapi.shared.exception.ResourceValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;

@Service
public class public class NotificationServicecelmpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ScalersRepository scalersRepository;
    private final Validator validator;

    public NotificationServicecelmpl(NotificationRepository notificationRepository, ScalersRepository scalersRepository, Validator validator) {
        this.notificationRepository = notificationRepository;
        this.scalersRepository = scalersRepository;
        this.validator = validator;
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.getAll();
    }
    @Override
    public Notification findById(long id) {
        var isExisting = notificationRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("Notification not found");
        }
        return notificationRepository.findById(id);
    }
    @Override
    public List<Notification> findByScalersId(long id) {
        var isExisting = scalersRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("Scalers not found");
        }
        return notificationRepository.findByScalersId(id);
    }
    @Override
    public Notification create(Notification notification) {
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return notificationRepository.create(notification);
    }

    @Override
    public Notification update(Notification notification) {
        Set<ConstraintViolation<Notification>> violations = validator.validate(notification);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return notificationRepository.update(notification);
    }
    

    @Override
    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    @Override
    public Category findById(long id) {
        var isExisting = categoryRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("Category not found");
        }
        return categoryRepository.findById(id);
    }

    @Override
    public List<Category> findByClimbingGymId(long id) {
        var isExisting = climbingGymRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceNotFoundException("ClimbingGym not found");
        }
        return categoryRepository.findByClimbingGymId(id);
    }

    @Override
    public Category create(Category category) {
        Set<ConstraintViolation<Category>> violations = validator.validate(category);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return categoryRepository.save(category);
    }

    @Override
    public Category update(long id, Category category) {
        var categoryToUpdate = categoryRepository.findById(id);
        if (categoryToUpdate == null) {
            throw new ResourceNotFoundException("Category not found");
        }
        categoryToUpdate.setName(category.getName());
        return categoryRepository.save(categoryToUpdate);
    }

    @Override
    public Category delete(long id) {
        var categoryToDelete = categoryRepository.findById(id);
        if (categoryToDelete == null) {
            throw new ResourceNotFoundException( "Category not found");
        }
        categoryRepository.delete(categoryToDelete);
        return categoryToDelete;
    }
}
