package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeagueRanking;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Service
public interface CompetitionLeagueRankingService {
    CompetitionLeagueRanking findById(long id);
    List<CompetitionLeagueRanking> getAll();
    List<Scalers> findScalersByCompetitionId(long competitionId);
    List<CompetitionLeagueRanking> findByCompetitionLeagueId(long competitionLeagueId);
    List<CompetitionLeagueRanking> findByCompetitionLeagueIdAndScalerId(long competitionLeagueId, long scalerId);
    CompetitionLeagueRanking create(CompetitionLeagueRanking competitionLeagueRanking, long competitionLeagueId, long scalerId);
    CompetitionLeagueRanking update(CompetitionLeagueRanking competitionLeagueRanking, long competitionLeagueId, long scalerId);
    CompetitionLeagueRanking delete(long competitionLeagueId, long scalerId);
}
