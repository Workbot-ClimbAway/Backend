package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.climbaway.domain.model.entity.Member;
import workbot.climbawayapi.security.domain.model.entity.Scalers;
import workbot.climbawayapi.climbaway.domain.persistence.ClimbingGymRepository;
import workbot.climbawayapi.climbaway.domain.persistence.LeagueRepository;
import workbot.climbawayapi.climbaway.domain.persistence.MembersRepository;
import workbot.climbawayapi.security.domain.persistence.ScalersRepository;
import workbot.climbawayapi.climbaway.domain.service.LeagueService;
import workbot.climbawayapi.climbaway.domain.service.MemberService;
import workbot.climbawayapi.shared.exception.ResourceNotFoundException;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class LeagueServiceImpl implements LeagueService {

    private final LeagueRepository leagueRepository;
    private final ScalersRepository scalersRepository;
    private final ClimbingGymRepository climbingGymRepository;
    private final MemberService memberService;
    private final MembersRepository membersRepository;
    private final Validator validator;

    public LeagueServiceImpl(LeagueRepository leagueRepository, ScalersRepository scalersRepository, ClimbingGymRepository climbingGymRepository, MemberService memberService, MembersRepository membersRepository, Validator validator) {
        this.leagueRepository = leagueRepository;
        this.scalersRepository = scalersRepository;
        this.climbingGymRepository = climbingGymRepository;
        this.memberService = memberService;
        this.membersRepository = membersRepository;
        this.validator = validator;
    }

    @Override
    public List<League> getAll() {
        return leagueRepository.findAll();
    }

    @Override
    public League findById(Long id) {
        return leagueRepository.findById(id).orElse(null);
    }

    @Override
    public List<League> findByClimbingGymId(Long id) {
        return leagueRepository.findByClimbingGymId(id);
    }

    @Override
    public League create(League league, Long climbingGymId, Long scaleId) {
        var isClimbingGym = climbingGymRepository.findById(climbingGymId).orElse(null);
        if (isClimbingGym == null) {
            throw new ResourceNotFoundException("Climbing gym not found");
        }
        var isScale = scalersRepository.findById(scaleId).orElse(null);
        if (isScale == null) {
            throw new ResourceNotFoundException("Scale not found");
        }
        league.setClimbingGym(isClimbingGym);
        league.setScaler(isScale);
        league.setClimbingGymId(climbingGymId);
        league.setScaleId(scaleId);
        league.setNumberParticipants(1);
        league.setAdministrator(isScale.getFirstName() + " " + isScale.getLastName());
        Set<ConstraintViolation<League>> violations = validator.validate(league);
        if (!violations.isEmpty()) {
            throw new ResourceNotFoundException("League is not valid");
        }
        var isMember = new Member();
        var isLeague = leagueRepository.save(league);
        memberService.createMemberNew(isMember,league.getClimbingGymId(),isLeague,scaleId);
        return isLeague;
    }

    @Override
    public League update(League league, Long id) {
        var isLeague = leagueRepository.findById(id).orElse(null);
        if (isLeague == null) {
            throw new ResourceNotFoundException("League not found");
        }
        league.setName(league.getName());
        league.setUrlPhoto(league.getUrlPhoto());
        league.setDescription(league.getDescription());
        Set<ConstraintViolation<League>> violations = validator.validate(league);
        if (!violations.isEmpty()) {
            throw new ResourceNotFoundException("League is not valid");
        }
        return leagueRepository.save(league);
    }

    @Override
    public League updateNumberParticipants(Long leagueId, Long numberParticipants) {
        var league = leagueRepository.findById(leagueId).orElse(null);
        if (league == null) {
            throw new ResourceNotFoundException("League not found");
        }
        league.setNumberParticipants(numberParticipants);
        return leagueRepository.save(league);
    }

    @Override
    public League deleteParticipant(Long leagueId, Long scaleId) {
        var league = leagueRepository.findById(leagueId).orElse(null);
        if (league == null) {
            throw new ResourceNotFoundException("League not found");
        }
        var scale = scalersRepository.findById(scaleId).orElse(null);
        if (scale == null) {
            throw new ResourceNotFoundException("Scale not found");
        }
        league.setNumberParticipants(league.getNumberParticipants() - 1);
        return leagueRepository.save(league);
    }

    @Override
    public League delete(Long id) {
        var league = leagueRepository.findById(id).orElse(null);
        if (league == null) {
            throw new ResourceNotFoundException("League not found");
        }
        membersRepository.deleteByLeagueId(id);
        leagueRepository.deleteByLeagueId(id);
        return league;
    }

    @Override
    public List<League> findRequestsByScalerId(Long scaleId) {
        return leagueRepository.findRequestsByScalerId(scaleId);
    }

    @Override
    public List<Scalers> findRequestsByLeagueId(long leagueId) {
        return leagueRepository.findRequestsByLeagueId(leagueId);
    }
    @Override
    public League createRequest(Long leagueId, Long scaleId) {
        var league = leagueRepository.findById(leagueId).orElse(null);
        if (league == null) {
            throw new ResourceNotFoundException("League not found");
        }
        var scale = scalersRepository.findById(scaleId).orElse(null);
        if (scale == null) {
            throw new ResourceNotFoundException("Scale not found");
        }
        var isRequest = leagueRepository.findRequestByScalerIdAndLeagueId(scaleId,leagueId);
        if (isRequest != null) {
            throw new ResourceNotFoundException("Request already exists");
        }
        var alreadyMember = memberService.findByClimbingGymIdAndLeagueIdAndScalerId(league.getClimbingGymId(),leagueId,scaleId);
        if (alreadyMember != null) {
            throw new ResourceNotFoundException("You are already a member");
        }
        Set<League> leagues = scale.getLeagues_requested();
        leagues.add(league);
        scale.setLeagues_requested(leagues);
        scalersRepository.save(scale);
        return league;
    }

    @Override
    public League acceptRequest(Long leagueId, Long scaleId) {
        var league = leagueRepository.findById(leagueId).orElse(null);
        if (league == null) {
            throw new ResourceNotFoundException("League not found");
        }
        var scale = scalersRepository.findById(scaleId).orElse(null);
        if (scale == null) {
            throw new ResourceNotFoundException("Scale not found");
        }

        league.setNumberParticipants(league.getNumberParticipants() + 1);
        leagueRepository.save(league);
        var isMember = new Member();
        memberService.createMember(isMember,league.getClimbingGymId(),leagueId,scaleId);
        rejectRequest(leagueId,scaleId);
        return league;
    }

    @Override
    public League rejectRequest(Long leagueId, Long scaleId) {
        var league = leagueRepository.findById(leagueId).orElse(null);
        if (league == null) {
            throw new ResourceNotFoundException("League not found");
        }
        var scale = scalersRepository.findById(scaleId).orElse(null);
        if (scale == null) {
            throw new ResourceNotFoundException("Scale not found");
        }
        Set<League> leagues = scale.getLeagues_requested();
        leagues.remove(league);
        scale.setLeagues_requested(leagues);
        scalersRepository.save(scale);
        return league;
    }
}
