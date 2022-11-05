package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.News;
import workbot.climbawayapi.climbaway.resource.NewsResource;
import workbot.climbawayapi.climbaway.resource.SaveNewsResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class NewsMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public NewsResource toResource(News model){
        return mapper.map(model, NewsResource.class);
    }

    public News toModelSaveResource(SaveNewsResource resource) {
        return mapper.map(resource, News.class);
    }

    //List Mapping

    public List<NewsResource> toResource(List<News> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
