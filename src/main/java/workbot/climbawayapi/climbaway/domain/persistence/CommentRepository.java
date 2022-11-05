package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.Comment;

import java.util.List;

@Qualifier("commentRepository")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findById(long id);

    @Query(value ="SELECT c FROM Comment c")
    List<Comment> getAll();

    @Query(value ="SELECT c FROM Comment c WHERE c.climbingGym.id = ?1 and c.scaleId = ?2")
    Comment findByClimbingGymAndScalerId(long climbingGymId, long scaleId);


    @Query(value ="SELECT c FROM Comment c WHERE c.climbingGym.id = ?1")
    List<Comment> findByClimbingGymId(long climbingGymId);
}
