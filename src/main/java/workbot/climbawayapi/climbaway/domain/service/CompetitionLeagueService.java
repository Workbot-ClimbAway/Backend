package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeague;

import java.util.List;

@Service
public interface CompetitionLeagueService {
    CompetitionLeague findById(long id);
    List<CompetitionLeague> findByLeagueId(long leagueId);
    List<CompetitionLeague> getAll();
    CompetitionLeague create(CompetitionLeague competitionLeague, long leagueId);
    CompetitionLeague delete(long id);
}
