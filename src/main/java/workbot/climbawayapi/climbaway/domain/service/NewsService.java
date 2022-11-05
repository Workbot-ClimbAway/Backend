package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.News;

import java.util.List;

@Service
public interface NewsService {

        List<News> getAll();
        News findById(long id);
        List<News> findByClimbingGymId(long id);
        News create(News news, long climbingGymId);
        News delete(long id);
}
