package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.Image;
import workbot.climbawayapi.climbaway.resource.ImageResource;
import workbot.climbawayapi.climbaway.resource.SaveImageResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ImageMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public ImageResource toResource(Image model){
        return mapper.map(model, ImageResource.class);
    }

    public Image toModelSaveResource(SaveImageResource resource) {
        return mapper.map(resource, Image.class);
    }

    //List Mapping

    public List<ImageResource> toResource(List<Image> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
