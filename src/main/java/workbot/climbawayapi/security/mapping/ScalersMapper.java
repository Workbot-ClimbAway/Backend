package workbot.climbawayapi.security.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.security.domain.model.entity.Scalers;
import workbot.climbawayapi.security.resource.SaveScalersResource;
import workbot.climbawayapi.security.resource.ScalersResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ScalersMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public ScalersResource toResource(Scalers model){
        return mapper.map(model, ScalersResource.class);
    }
    public Scalers toModelSaveResource(SaveScalersResource resource) {
        return mapper.map(resource, Scalers.class);
    }

    //List Mapping

    public List<ScalersResource> toResource(List<Scalers> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
