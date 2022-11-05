package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.climbaway.resource.LeagueResource;
import workbot.climbawayapi.climbaway.resource.SaveLeagueResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class LeagueMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public LeagueResource toResource(League model){
        return mapper.map(model, LeagueResource.class);
    }

    public League toModelSaveResource(SaveLeagueResource resource) {
        return mapper.map(resource, League.class);
    }

    //List Mapping

    public List<LeagueResource> toResource(List<League> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
