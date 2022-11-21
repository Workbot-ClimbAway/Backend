package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.CompetitionLeagueRankingService;
import workbot.climbawayapi.climbaway.mapping.CompetitionLeagueRankingMapper;
import workbot.climbawayapi.security.mapping.ScalersMapper;
import workbot.climbawayapi.climbaway.resource.CompetitionLeagueRankingResource;
import workbot.climbawayapi.climbaway.resource.SaveCompetitionLeagueRankingResource;
import workbot.climbawayapi.security.resource.ScalersResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/competition-league-rankings")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})

public class CompetitionLeagueRankingController {
    private final CompetitionLeagueRankingService competitionLeagueRankingService;
    private final ScalersMapper scalersMapper;
    private final CompetitionLeagueRankingMapper competitionLeagueRankingMapper;

    public CompetitionLeagueRankingController(CompetitionLeagueRankingService competitionLeagueRankingService, ScalersMapper scalersMapper, CompetitionLeagueRankingMapper competitionLeagueRankingMapper) {
        this.competitionLeagueRankingService = competitionLeagueRankingService;
        this.scalersMapper = scalersMapper;
        this.competitionLeagueRankingMapper = competitionLeagueRankingMapper;
    }

    @GetMapping
    public List<CompetitionLeagueRankingResource> getAll() {
        return competitionLeagueRankingMapper.toResource(competitionLeagueRankingService.getAll());
    }

    @GetMapping("/competition-league/{competitionLeagueId}")
    public List<CompetitionLeagueRankingResource> findByCompetitionLeagueId(@PathVariable long competitionLeagueId) {
        return competitionLeagueRankingMapper.toResource(competitionLeagueRankingService.findByCompetitionLeagueId(competitionLeagueId));
    }

    @GetMapping("/competition-league/{competitionLeagueId}/scaler/{scalerId}")
    public List<CompetitionLeagueRankingResource> findByCompetitionLeagueIdAndScalerId(@PathVariable long competitionLeagueId, @PathVariable long scalerId) {
        return competitionLeagueRankingMapper.toResource(competitionLeagueRankingService.findByCompetitionLeagueIdAndScalerId(competitionLeagueId, scalerId));
    }

    @GetMapping("/scalers/competition-league/{competitionLeagueId}")
    public List<ScalersResource> findScalersByCompetitionId(@PathVariable long competitionLeagueId) {
        return scalersMapper.toResource(competitionLeagueRankingService.findScalersByCompetitionId(competitionLeagueId));
    }

    @GetMapping("/{id}")
    public CompetitionLeagueRankingResource findById(@PathVariable long id) {
        return competitionLeagueRankingMapper.toResource(competitionLeagueRankingService.findById(id));
    }

    @PostMapping("/competition-league/{competitionLeagueId}/scaler/{scalerId}")
    public CompetitionLeagueRankingResource create(@RequestBody SaveCompetitionLeagueRankingResource resource, @PathVariable long competitionLeagueId, @PathVariable long scalerId) {
        return competitionLeagueRankingMapper.toResource(competitionLeagueRankingService.create(competitionLeagueRankingMapper.toModelSaveResource(resource), competitionLeagueId, scalerId));
    }

    @PutMapping("/competition-league/{competitionLeagueId}/scaler/{scalerId}")
    public CompetitionLeagueRankingResource update(@RequestBody SaveCompetitionLeagueRankingResource resource, @PathVariable long competitionLeagueId, @PathVariable long scalerId) {
        return competitionLeagueRankingMapper.toResource(competitionLeagueRankingService.update(competitionLeagueRankingMapper.toModelSaveResource(resource), competitionLeagueId, scalerId));
    }

    @DeleteMapping("/competition-league/{competitionLeagueId}/scaler/{scalerId}")
    public CompetitionLeagueRankingResource delete(@PathVariable long competitionLeagueId, @PathVariable long scalerId) {
        return competitionLeagueRankingMapper.toResource(competitionLeagueRankingService.delete(competitionLeagueId, scalerId));
    }
}
