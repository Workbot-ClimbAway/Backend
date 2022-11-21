package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Comment;
import workbot.climbawayapi.climbaway.domain.persistence.ClimbingGymRepository;
import workbot.climbawayapi.climbaway.domain.persistence.CommentRepository;
import workbot.climbawayapi.security.domain.persistence.ScalersRepository;
import workbot.climbawayapi.climbaway.domain.service.CommentService;
import workbot.climbawayapi.shared.exception.ResourceValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final Validator validator;
    private final ClimbingGymRepository climbingGymRepository;
    private final ScalersRepository scalersRepository;

    public CommentServiceImpl(CommentRepository commentRepository, Validator validator, ClimbingGymRepository climbingGymRepository, ScalersRepository scalersRepository) {
        this.commentRepository = commentRepository;
        this.validator = validator;
        this.climbingGymRepository = climbingGymRepository;
        this.scalersRepository = scalersRepository;
    }


    @Override
    public List<Comment> getAll() {
        return commentRepository.getAll();
    }

    @Override
    public Comment findById(long id) {
        return commentRepository.findById(id);
    }

    @Override
    public Comment findByClimbingGymAndScalerId(long climbingGymId, long scaleId) {
        return commentRepository.findByClimbingGymAndScalerId(climbingGymId, scaleId);
    }

    @Override
    public List<Comment> findByClimbingGymId(long climbingGymId) {
        return commentRepository.findByClimbingGymId(climbingGymId);
    }

    @Override
    public Comment create(Comment comment, long climbingGymId, long scaleId) {
        var isExist = climbingGymRepository.findById(climbingGymId);
        if (isExist == null) {
            throw new RuntimeException("Climbing gym yet not found");
        }
        var isScalerExist = scalersRepository.findById(scaleId);
        if (isScalerExist == null) {
            throw new RuntimeException("Scaler yet not found");
        }
        comment.setDate(new Date());
        comment.setClimbingGym(isExist);
        comment.setClimbingGymId(climbingGymId);
        comment.setScaler(isScalerExist);
        comment.setScaleId(scaleId);
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Comment comment, long climbingGymId, long scaleId) {
        var isExistComment = commentRepository.findByClimbingGymAndScalerId(climbingGymId, scaleId);
        if (isExistComment == null) {
            throw new RuntimeException("Comment not found");
        }
        isExistComment.setDescription(comment.getDescription());
        isExistComment.setScore(comment.getScore());
        isExistComment.setDate(new Date());
        Set<ConstraintViolation<Comment>> violations = validator.validate(isExistComment);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return commentRepository.save(isExistComment);
    }

    @Override
    public Comment delete(long id) {
        var isExist = commentRepository.findById(id);
        if (isExist == null) {
            throw new RuntimeException("Comment yet not found");
        }
        commentRepository.deleteById(id);
        return isExist;
    }
}
