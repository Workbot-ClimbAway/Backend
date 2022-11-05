package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeague;
import workbot.climbawayapi.climbaway.resource.CompetitionLeagueResource;
import workbot.climbawayapi.climbaway.resource.SaveCompetitionLeagueResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionLeagueMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public CompetitionLeague toModelSaveResource(SaveCompetitionLeagueResource resource) {
        return mapper.map(resource, CompetitionLeague.class);
    }

    public CompetitionLeagueResource toResource(CompetitionLeague model){
        return mapper.map(model, CompetitionLeagueResource.class);
    }

    //List Mapping

    public List<CompetitionLeagueResource> toResource(List<CompetitionLeague> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
