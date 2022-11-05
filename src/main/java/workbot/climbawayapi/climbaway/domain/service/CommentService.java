package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.Comment;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getAll();
    Comment findById(long id);
    Comment findByClimbingGymAndScalerId(long climbingGymId, long scaleId);
    List<Comment> findByClimbingGymId(long climbingGymId);
    Comment create(Comment comment, long climbingGymId, long scalerId);
    Comment update(Comment comment, long climbingGymId, long scaleId);
    Comment delete(long id);
}
