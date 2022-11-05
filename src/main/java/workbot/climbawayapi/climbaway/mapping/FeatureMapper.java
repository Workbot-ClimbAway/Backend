package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.Feature;
import workbot.climbawayapi.climbaway.resource.FeatureResource;
import workbot.climbawayapi.climbaway.resource.SaveFeatureResource;
import workbot.climbawayapi.climbaway.resource.SaveImageResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class FeatureMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public FeatureResource toResource(Feature model){
        return mapper.map(model, FeatureResource.class);
    }

    public Feature toModelSaveResource(SaveFeatureResource resource) {
        return mapper.map(resource, Feature.class);
    }

    //List Mapping

    public List<FeatureResource> toResource(List<Feature> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
