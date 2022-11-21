package workbot.climbawayapi.climbaway.service;

import org.springframework.stereotype.Service;
import workbot.climbawayapi.climbaway.domain.model.entity.CompetitionLeagueRanking;
import workbot.climbawayapi.security.domain.model.entity.Scalers;
import workbot.climbawayapi.climbaway.domain.persistence.CompetitionLeagueRankingRepository;
import workbot.climbawayapi.security.domain.persistence.ScalersRepository;
import workbot.climbawayapi.climbaway.domain.service.CompetitionLeagueRankingService;
import workbot.climbawayapi.climbaway.domain.service.CompetitionLeagueService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CompetitionLeagueRankingServiceImpl implements CompetitionLeagueRankingService {
    private final CompetitionLeagueRankingRepository competitionLeagueRankingRepository;
    private final CompetitionLeagueService competitionLeagueService;
    private final ScalersRepository scalersRepository;
    private final Validator validator;

    public CompetitionLeagueRankingServiceImpl(CompetitionLeagueRankingRepository competitionLeagueRankingRepository,
                                               CompetitionLeagueService competitionLeagueService,
                                               ScalersRepository scalersRepository,
                                               Validator validator) {
        this.competitionLeagueRankingRepository = competitionLeagueRankingRepository;
        this.competitionLeagueService = competitionLeagueService;
        this.scalersRepository = scalersRepository;
        this.validator = validator;
    }

    @Override
    public CompetitionLeagueRanking findById(long id) {
        return competitionLeagueRankingRepository.findById(id);
    }

    @Override
    public List<CompetitionLeagueRanking> getAll() {
        return competitionLeagueRankingRepository.getAll();
    }

    @Override
    public List<Scalers> findScalersByCompetitionId(long competitionId) {
        return competitionLeagueRankingRepository.findScalersByCompetitionId(competitionId);
    }

    @Override
    public List<CompetitionLeagueRanking> findByCompetitionLeagueId(long competitionLeagueId) {
        return competitionLeagueRankingRepository.findByCompetitionLeagueId(competitionLeagueId);
    }

    @Override
    public List<CompetitionLeagueRanking> findByCompetitionLeagueIdAndScalerId(long competitionLeagueId, long scalerId) {
        return competitionLeagueRankingRepository.findByCompetitionLeagueIdAndScalerId(competitionLeagueId, scalerId);
    }

    @Override
    public CompetitionLeagueRanking create(CompetitionLeagueRanking competitionLeagueRanking, long competitionLeagueId, long scalerId) {
        var isExistingCompetitionLeague = competitionLeagueService.findById(competitionLeagueId);
        if (isExistingCompetitionLeague == null) {
            throw new IllegalArgumentException("Competition league does not exist");
        }

        var isExistingScaler = scalersRepository.findById(scalerId);
        if (isExistingScaler == null) {
            throw new IllegalArgumentException("Scaler does not exist");
        }
        competitionLeagueRanking.setCompetitionLeague(isExistingCompetitionLeague);
        competitionLeagueRanking.setScale(isExistingScaler);
        competitionLeagueRanking.setCompetitionLeagueId(competitionLeagueId);
        competitionLeagueRanking.setScaleId(scalerId);
        Set<ConstraintViolation<CompetitionLeagueRanking>> violations = validator.validate(competitionLeagueRanking);
        if (!violations.isEmpty()) {
            throw new RuntimeException("Competition league ranking is not valid");
        }
        return competitionLeagueRankingRepository.save(competitionLeagueRanking);
    }

    @Override
    public CompetitionLeagueRanking update(CompetitionLeagueRanking competitionLeagueRanking, long competitionLeagueId, long scalerId) {
        var isExistingCompetitionLeague = competitionLeagueService.findById(competitionLeagueId);
        if (isExistingCompetitionLeague == null) {
            throw new IllegalArgumentException("Competition league does not exist");
        }

        var isExistingScaler = scalersRepository.findById(scalerId);
        if (isExistingScaler == null) {
            throw new IllegalArgumentException("Scaler does not exist");
        }
        var isExistingCompetitionLeagueRanking = competitionLeagueRankingRepository.findByCompetitionLeagueIdAndScalerId(competitionLeagueId, scalerId);
        if (isExistingCompetitionLeagueRanking == null) {
            throw new IllegalArgumentException("Competition league ranking does not exist");
        }
        var competitionLeagueRankingNew = isExistingCompetitionLeagueRanking.get(0);
        competitionLeagueRankingNew.setScore(competitionLeagueRanking.getScore());
        competitionLeagueRankingRepository.save(competitionLeagueRankingNew);
        return competitionLeagueRankingNew;
    }

    @Override
    public CompetitionLeagueRanking delete(long competitionLeagueId, long scalerId) {
        var competitionLeagueRanking = competitionLeagueRankingRepository.findByCompetitionLeagueIdAndScalerId(competitionLeagueId, scalerId);
        if (competitionLeagueRanking == null) {
            throw new IllegalArgumentException("Competition league ranking does not exist");
        }
        competitionLeagueRankingRepository.delete(competitionLeagueRanking.get(0));
        return competitionLeagueRanking.get(0);
    }
}
