package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Qualifier("leagueRepository")
@Repository
public interface LeagueRepository extends JpaRepository<League, Long> {
    League findById(long id);

    @Query(value ="SELECT l FROM League l")
    List<League> getAll();

    @Query(value ="SELECT l FROM League l WHERE l.climbingGym.id = ?1")
    List<League> findByClimbingGymId(long climbingGymId);

    //Leagues and requests
    @Query(value ="SELECT sl FROM League l Join l.scaler_leagues sl Join sl.leagues_requested s WHERE s.id = ?1")
    List<Scalers> findRequestsByLeagueId(long leagueId);

    //FIND REQUESTS BY LEAGUE ID
    @Query(value ="SELECT l FROM League l Join fetch l.scaler_leagues r WHERE r.id = ?1")
    List<League> findRequestsByScalerId(long scaleId);

    @Query(value ="SELECT l FROM League l Join fetch l.scaler_leagues sl Join fetch sl.leagues_requested s WHERE l.id = ?1 AND sl.id = ?2")
    League findRequestByScalerIdAndLeagueId(long scaleId, long leagueId);

    // Delete by league id
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM League l WHERE l.id = ?1")
    void deleteByLeagueId(long id);
}
