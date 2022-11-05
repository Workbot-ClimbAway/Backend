package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeague;
import workbot.climbawayapi.climbaway.domain.persistence.CompetitionLeagueRepository;
import workbot.climbawayapi.climbaway.domain.persistence.LeagueRepository;
import workbot.climbawayapi.climbaway.domain.service.CompetitionLeagueService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class CompetitionLeagueServiceImpl implements CompetitionLeagueService {

    private final CompetitionLeagueRepository competitionLeagueRepository;
    private final Validator validator;
    private final LeagueRepository leagueRepository;

    public CompetitionLeagueServiceImpl(CompetitionLeagueRepository competitionLeagueRepository, Validator validator, LeagueRepository leagueRepository) {
        this.competitionLeagueRepository = competitionLeagueRepository;
        this.validator = validator;
        this.leagueRepository = leagueRepository;
    }

    @Override
    public CompetitionLeague findById(long id) {
        return competitionLeagueRepository.findById(id);
    }

    @Override
    public List<CompetitionLeague> findByLeagueId(long leagueId) {
        return competitionLeagueRepository.findByLeagueId(leagueId);
    }

    @Override
    public List<CompetitionLeague> getAll() {
        return competitionLeagueRepository.getAll();
    }

    @Override
    public CompetitionLeague create(CompetitionLeague competitionLeague, long leagueId) {
        var isExistLeague = leagueRepository.findById(leagueId);
        if (isExistLeague == null) {
            throw new IllegalArgumentException("League not found");
        }
        competitionLeague.setDate(new Date());
        competitionLeague.setLeague(isExistLeague);
        competitionLeague.setLeagueId(leagueId);
        Set<ConstraintViolation<CompetitionLeague>> violations = validator.validate(competitionLeague);
        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Competition league is not valid");
        }
        return competitionLeagueRepository.save(competitionLeague);
    }

    @Override
    public CompetitionLeague delete(long id) {
        var competitionLeague = competitionLeagueRepository.findById(id);
        if (competitionLeague == null) {
            throw new IllegalArgumentException("Competition league not found");
        }
        competitionLeagueRepository.deleteById(id);
        return competitionLeague;
    }
}
