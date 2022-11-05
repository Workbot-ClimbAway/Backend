package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeague;

import java.util.List;

@Qualifier("climber_leagueRepository")
@Repository
public interface CompetitionLeagueRepository extends JpaRepository<CompetitionLeague, Long> {
    CompetitionLeague findById(long id);

    //find by leagueId
    @Query(value ="SELECT c FROM CompetitionLeague c WHERE c.league.id = ?1")
    List<CompetitionLeague> findByLeagueId(long leagueId);

    //get all
    @Query(value ="SELECT c FROM CompetitionLeague c")
    List<CompetitionLeague> getAll();
}

