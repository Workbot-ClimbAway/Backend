package workbot.climbawayapi.climbaway.domain.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.climbaway.domain.model.entity.Member;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import java.util.List;

@Service
public interface MemberService {
    List<Member> getAll();
    List<Scalers> findScalersByLeagueId(long leagueId);
    List<League> findLeagueByClimbingGymIdAndScalerId(long climbingGymId, long scalerId);
    Member findByClimbingGymIdAndLeagueIdAndScalerId(long climbingGymId, long leagueId, long scalerId);
    Member createMember(Member member, long climbingGymId, long leagueId, long scalerId);
    Member createMemberNew(Member member, long climbingGymId, League league, long scalerId);
    Member deleteMember(long climbingGymId, long leagueId, long scalerId);
}
