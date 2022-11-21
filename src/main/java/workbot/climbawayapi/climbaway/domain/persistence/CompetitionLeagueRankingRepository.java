package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeagueRanking;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Qualifier("competitionLeagueRankingRepository")
@Repository
public interface CompetitionLeagueRankingRepository extends JpaRepository<CompetitionLeagueRanking, Long> {
    CompetitionLeagueRanking findById(long id);

    //get all
    @Query(value ="SELECT c FROM CompetitionLeagueRanking c")
    List<CompetitionLeagueRanking> getAll();

    //find Scalers by CompetitionId
    @Query(value ="SELECT s FROM CompetitionLeagueRanking c join c.scale s WHERE c.id = ?1")
    List<Scalers> findScalersByCompetitionId(long competitionId);
    //find by leagueId
    @Query(value ="SELECT c FROM CompetitionLeagueRanking c WHERE c.competitionLeague.id = ?1")
    List<CompetitionLeagueRanking> findByCompetitionLeagueId(long competitionLeagueId);

    //find by CompetitionLeagueId and ScalerId
    @Query(value ="SELECT c FROM CompetitionLeagueRanking c WHERE c.competitionLeague.id = ?1 and c.scale.id = ?2")
    List<CompetitionLeagueRanking> findByCompetitionLeagueIdAndScalerId(long competitionLeagueId, long scalerId);


}
