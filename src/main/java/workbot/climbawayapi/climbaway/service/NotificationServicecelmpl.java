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
public class NotificationServicecelmpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ScalersRepository scalersRepository;
    private final Validator validator;
}
