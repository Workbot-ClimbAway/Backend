package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.climbaway.domain.model.entity.Member;
import workbot.climbawayapi.security.domain.model.entity.Scalers;
import workbot.climbawayapi.climbaway.domain.persistence.ClimbingGymRepository;
import workbot.climbawayapi.climbaway.domain.persistence.LeagueRepository;
import workbot.climbawayapi.climbaway.domain.persistence.MembersRepository;
import workbot.climbawayapi.security.domain.persistence.ScalersRepository;
import workbot.climbawayapi.climbaway.domain.service.MemberService;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService {

    private final MembersRepository membersRepository;
    private final ScalersRepository scalersRepository;
    private final ClimbingGymRepository climbingGymRepository;
    private final LeagueRepository leagueRepository;

    public MemberServiceImpl(MembersRepository membersRepository,
                             ScalersRepository scalersRepository,
                             ClimbingGymRepository climbingGymRepository,
                             LeagueRepository leagueRepository) {
        this.membersRepository = membersRepository;
        this.scalersRepository = scalersRepository;
        this.climbingGymRepository = climbingGymRepository;
        this.leagueRepository = leagueRepository;
    }

    @Override
    public List<Member> getAll() {
        return membersRepository.getAll();
    }

    @Override
    public List<Scalers> findScalersByLeagueId(long leagueId) {
        return membersRepository.findScalersByLeagueId(leagueId);
    }

    @Override
    public List<League> findLeagueByClimbingGymIdAndScalerId(long climbingGymId, long scalerId) {
        return membersRepository.findLeagueByClimbingGymIdAndScalerId(climbingGymId, scalerId);
    }

    @Override
    public Member findByClimbingGymIdAndLeagueIdAndScalerId(long climbingGymId, long leagueId, long scalerId) {
        var member = membersRepository.findByClimbingGymIdAndLeagueIdAndScalerId(climbingGymId, leagueId, scalerId);
        if (member != null && member.toArray().length > 0) {
            return member.get(0);
        }
        return null;
    }

    @Override
    public Member createMember(Member member, long climbingGymId, long leagueId, long scalerId) {
        var isClimbingGym = climbingGymRepository.findById(climbingGymId);
        if (isClimbingGym==null) {
            throw new IllegalArgumentException("Climbing Gym does not exist");
        }
        var isLeague = leagueRepository.findById(leagueId);
        if (isLeague==null) {
            throw new IllegalArgumentException("League does not exist");
        }
        var isScaler = scalersRepository.findById(scalerId);
        if (isScaler==null) {
            throw new IllegalArgumentException("Scaler does not exist");
        }
        var members = findByClimbingGymIdAndLeagueIdAndScalerId(climbingGymId, leagueId, scalerId);
        if (members!=null) {
            throw new IllegalArgumentException("Member already exists");
        }
        member.setScaleId(scalerId);
        member.setScale(isScaler);
        member.setLeagueId(leagueId);
        member.setLeague(isLeague);
        member.setClimbingGymId(climbingGymId);
        member.setClimbingGym(isClimbingGym);
        member.setStatus("Active");
        return membersRepository.save(member);
    }

    @Override
    public Member createMemberNew(Member member, long climbingGymId, League league, long scalerId) {
        var isClimbingGym = climbingGymRepository.findById(climbingGymId);
        var isScaler = scalersRepository.findById(scalerId);
        var members = findByClimbingGymIdAndLeagueIdAndScalerId(climbingGymId, league.getId(), scalerId);
        if (members!=null) {
            throw new IllegalArgumentException("Member already exists");
        }
        member.setScaleId(scalerId);
        member.setScale(isScaler);
        member.setLeagueId(league.getId());
        member.setLeague(league);
        member.setClimbingGymId(climbingGymId);
        member.setClimbingGym(isClimbingGym);
        member.setStatus("Active");
        return membersRepository.save(member);
    }

    @Override
    public Member deleteMember(long climbingGymId, long leagueId, long scalerId) {
        var isClimbingGym = climbingGymRepository.findById(climbingGymId);
        if (isClimbingGym==null) {
            throw new IllegalArgumentException("Climbing Gym does not exist");
        }
        var isLeague = leagueRepository.findById(leagueId);
        if (isLeague==null) {
            throw new IllegalArgumentException("League does not exist");
        }
        var isScaler = scalersRepository.findById(scalerId);
        if (isScaler==null) {
            throw new IllegalArgumentException("Scaler does not exist");
        }
        var member = findByClimbingGymIdAndLeagueIdAndScalerId(climbingGymId, leagueId, scalerId);
        if (member==null) {
            throw new IllegalArgumentException("Member does not exist");
        }
        membersRepository.deleteByClimbingGymIdAndLeagueIdAndScalerId(climbingGymId, leagueId, scalerId);
        isLeague.setNumberParticipants(isLeague.getNumberParticipants()-1);
        leagueRepository.save(isLeague);
        return member;
    }
}
