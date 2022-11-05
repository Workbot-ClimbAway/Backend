package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.News;
import workbot.climbawayapi.climbaway.domain.persistence.ClimbingGymRepository;
import workbot.climbawayapi.climbaway.domain.persistence.NewsRepository;
import workbot.climbawayapi.climbaway.domain.service.NewsService;
import workbot.climbawayapi.shared.exception.ResourceValidationException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.hibernate.usertype.DynamicParameterizedType.ENTITY;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final Validator validator;
    private final ClimbingGymRepository climbingGymRepository;

    public NewsServiceImpl(NewsRepository newsRepository, Validator validator, ClimbingGymRepository climbingGymRepository) {
        this.newsRepository = newsRepository;
        this.validator = validator;
        this.climbingGymRepository = climbingGymRepository;
    }

    @Override
    public List<News> getAll() {
        return newsRepository.getAll();
    }

    @Override
    public News findById(long id) {
        return newsRepository.findById(id);
    }

    @Override
    public List<News> findByClimbingGymId(long id) {
        return newsRepository.findNewsByClimbingGymId(id);
    }

    @Override
    public News create(News news, long climbingGymId) {
        var isExisting = climbingGymRepository.findById(climbingGymId);
        if (isExisting == null) {
            throw new ResourceValidationException("ClimbingGym not found");
        }
        news.setDate(new Date());
        news.setClimbingGymId(climbingGymId);
        news.setClimbingGym(isExisting);
        Set<ConstraintViolation<News>> violations = validator.validate(news);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return newsRepository.save(news);
    }

    @Override
    public News delete(long id) {
        var isExisting = newsRepository.findById(id);
        if (isExisting == null) {
            throw new ResourceValidationException("News not found");
        }
        newsRepository.deleteById(id);
        return isExisting;
    }
}
