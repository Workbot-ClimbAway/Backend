package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.ClimbingGym;
import workbot.climbawayapi.climbaway.resource.ClimbingGymResource;
import workbot.climbawayapi.climbaway.resource.SaveClimbingGymResource;
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

    public Scalers toModel(ScalersResource resource) {
        return mapper.map(resource, Scalers.class);
    }

    public Scalers toModelSaveResource(SaveScalersResource resource) {
        return mapper.map(resource, Scalers.class);
    }

    //List Mapping

    public List<ScalersResource> toResource(List<Scalers> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }

    public ClimbingGymResource toResource(ClimbingGym model){
        return mapper.map(model, ClimbingGymResource.class);
    }

    public ClimbingGym toModelSaveResource(SaveClimbingGymResource model){
        return mapper.map(model, ClimbingGym.class);
    }

    public ClimbingGym toModel(ClimbingGymResource resource) {
        return mapper.map(resource, ClimbingGym.class);
    }

    //List Mapping

    public List<ClimbingGymResource> toResource(List<ClimbingGym> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
