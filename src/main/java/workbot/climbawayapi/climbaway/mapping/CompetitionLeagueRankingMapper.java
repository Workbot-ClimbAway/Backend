package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeagueRanking;
import workbot.climbawayapi.climbaway.resource.CompetitionLeagueRankingResource;
import workbot.climbawayapi.climbaway.resource.SaveCompetitionLeagueRankingResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CompetitionLeagueRankingMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public CompetitionLeagueRankingResource toResource(CompetitionLeagueRanking model){
        return mapper.map(model, CompetitionLeagueRankingResource.class);
    }

    public CompetitionLeagueRanking toModelSaveResource(SaveCompetitionLeagueRankingResource resource) {
        return mapper.map(resource, CompetitionLeagueRanking.class);
    }

    //List Mapping

    public List<CompetitionLeagueRankingResource> toResource(List<CompetitionLeagueRanking> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
