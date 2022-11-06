package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.CompetitionLeagueService;
import workbot.climbawayapi.climbaway.mapping.CompetitionLeagueMapper;
import workbot.climbawayapi.climbaway.resource.CompetitionLeagueResource;
import workbot.climbawayapi.climbaway.resource.SaveCompetitionLeagueResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/competition-leagues")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class CompetitionLeagueController {

    private final CompetitionLeagueService competitionLeagueService;
    private final CompetitionLeagueMapper competitionLeagueMapper;

    public CompetitionLeagueController(CompetitionLeagueService competitionLeagueService, CompetitionLeagueMapper competitionLeagueMapper) {
        this.competitionLeagueService = competitionLeagueService;
        this.competitionLeagueMapper = competitionLeagueMapper;
    }
    /*
    * ompetitionLeague findById(long id);
    List<CompetitionLeague> findByLeagueId(long leagueId);
    List<CompetitionLeague> getAll();
    CompetitionLeague create(CompetitionLeague competitionLeague, long leagueId);
    CompetitionLeague delete(long id);*/

    @GetMapping
    public List<CompetitionLeagueResource> getAllCompetitionLeagues(){
        return competitionLeagueMapper.toResource(competitionLeagueService.getAll());
    }

    @GetMapping("/{id}")
    public CompetitionLeagueResource getCompetitionLeagueById(@PathVariable Long id){
        return competitionLeagueMapper.toResource(competitionLeagueService.findById(id));
    }

    @GetMapping("/league/{leagueId}")
    public List<CompetitionLeagueResource> findByLeagueId(@PathVariable Long leagueId){
        return competitionLeagueMapper.toResource(competitionLeagueService.findByLeagueId(leagueId));
    }

    @PostMapping("/league/{leagueId}")
    public CompetitionLeagueResource createCompetitionLeague(@RequestBody SaveCompetitionLeagueResource saveCompetitionLeagueResource, @PathVariable Long leagueId){
        return competitionLeagueMapper.toResource(competitionLeagueService.create(competitionLeagueMapper.toModelSaveResource(saveCompetitionLeagueResource), leagueId));
    }

    @DeleteMapping("/{id}")
    public CompetitionLeagueResource deleteCompetitionLeague(@PathVariable Long id){
        return competitionLeagueMapper.toResource(competitionLeagueService.delete(id));
    }

}
