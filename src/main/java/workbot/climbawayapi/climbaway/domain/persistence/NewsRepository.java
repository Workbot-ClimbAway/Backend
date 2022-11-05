package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.News;

import java.util.List;

@Qualifier("newsRepository")
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {
    News findById(long id);

    @Query(value ="SELECT n FROM News n")
    List<News> getAll();

    @Query(value ="SELECT n FROM News n WHERE n.climbingGymId = ?1")
    List<News> findNewsByClimbingGymId(long id);
}
