package workbot.climbawayapi.climbaway.domain.persistence;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.climbaway.domain.model.entity.Member;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Qualifier("memberRepository")
@Repository
public interface MembersRepository extends JpaRepository<Member, Long> {
    @Query(value ="SELECT m FROM Member m")
    List<Member> getAll();

    //Find scaler by leagueId
    @Query(value ="SELECT s FROM Member m Join m.scale s Join  m.league l WHERE l.id = ?1")
    List<Scalers> findScalersByLeagueId(long leagueId);

    //Find league by climbingGymId and scalerId
    @Query(value ="SELECT l FROM Member m Join  m.league l Join  m.scale s Join  m.climbingGym cg WHERE cg.id = ?1 and s.id = ?2")
    List<League> findLeagueByClimbingGymIdAndScalerId(long climbingGymId, long scalerId);

    //Find by climbingGymId and leagueId and scalerId return only one response
    @Query(value ="SELECT m FROM Member m Join  m.climbingGym cg Join  m.league l Join  m.scale s WHERE cg.id = ?1 and l.id = ?2 and s.id = ?3")
    List<Member> findByClimbingGymIdAndLeagueIdAndScalerId(long climbingGymId, long leagueId, long scalerId);

    //Delete by climbingGymId and leagueId and scalerId
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM Member m where m.climbingGym.id = ?1 and m.league.id = ?2 and m.scale.id = ?3")
    void deleteByClimbingGymIdAndLeagueIdAndScalerId(long climbingGymId, long leagueId, long scalerId);

    //Delete by leagueId
    @Modifying
    @Transactional
    @Query(value ="DELETE FROM Member m where m.league.id = ?1")
    void deleteByLeagueId(long leagueId);
}
