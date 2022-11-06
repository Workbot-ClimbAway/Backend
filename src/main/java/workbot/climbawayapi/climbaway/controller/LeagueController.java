package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.model.entity.League;
import workbot.climbawayapi.climbaway.domain.service.LeagueService;
import workbot.climbawayapi.climbaway.mapping.LeagueMapper;
import workbot.climbawayapi.climbaway.resource.LeagueResource;
import workbot.climbawayapi.climbaway.resource.SaveLeagueResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/league")
public class LeagueController {

    private  final LeagueService leagueService;
    private  final LeagueMapper leagueMapper;

    public LeagueController(LeagueService leagueService, LeagueMapper leagueMapper) {
        this.leagueService = leagueService;
        this.leagueMapper = leagueMapper;
    }

    @GetMapping
    public List<LeagueResource> getAllLeagues(){
        return leagueMapper.toResource(leagueService.getAll());
    }

    @GetMapping(value = "climbing-gym/{id}")
    public List<LeagueResource> findByClimbingGymId(@PathVariable Long id){
        return leagueMapper.toResource(leagueService.findByClimbingGymId(id));
    }

    @GetMapping(value = "/{id}")
    public LeagueResource findLeagueById(@PathVariable Long id){
        return leagueMapper.toResource(leagueService.findById(id));
    }

    @PostMapping(value = "/climbing-gym/{id}/scaler/{scalerId}")
    public LeagueResource createLeague(@RequestBody SaveLeagueResource saveLeagueResource, @PathVariable Long id, @PathVariable Long scalerId){
        return leagueMapper.toResource(leagueService.create(leagueMapper.toModelSaveResource(saveLeagueResource), id, scalerId));
    }

    @PutMapping(value = "/{id}")
    public LeagueResource updateLeague(@RequestBody SaveLeagueResource saveLeagueResource, @PathVariable Long id){
        return leagueMapper.toResource(leagueService.update(leagueMapper.toModelSaveResource(saveLeagueResource), id));
    }

    @PutMapping(value = "/{id}/scaler/{scalerId}")
    public LeagueResource updateNumberParticipants(@PathVariable Long id, @PathVariable Long scalerId){
        return leagueMapper.toResource(leagueService.updateNumberParticipants(id, scalerId));
    }

    @DeleteMapping(value = "/{id}")
    public LeagueResource deleteLeague(@PathVariable Long id){
        return leagueMapper.toResource(leagueService.delete(id));
    }

    // Requests

    @GetMapping(value = "/request/scaler/{id}")
    public List<LeagueResource> findRequestsByScalerId(@PathVariable Long id){
        return leagueMapper.toResource(leagueService.findRequestsByScalerId(id));
    }

    @GetMapping(value = "/request/league/{id}")
    public List<LeagueResource> findRequestsByLeagueId(@PathVariable Long id){
        return leagueMapper.toResource(leagueService.findRequestsByLeagueId(id));
    }

    @PostMapping(value = "/request/league/{id}/scaler/{scalerId}")
    public LeagueResource createRequest(@PathVariable Long id, @PathVariable Long scalerId){
        return leagueMapper.toResource(leagueService.createRequest(id, scalerId));
    }
   //League acceptRequest(Long leagueId, Long scaleId)

    @PostMapping(value = "/request/league/{id}/scaler/{scalerId}/accept")
    public LeagueResource acceptRequest(@PathVariable Long id, @PathVariable Long scalerId){
        return leagueMapper.toResource(leagueService.acceptRequest(id, scalerId));
    }

    @DeleteMapping(value = "/request/league/{id}/scaler/{scalerId}")
    public LeagueResource rejectRequest(@PathVariable Long id, @PathVariable Long scalerId){
        return leagueMapper.toResource(leagueService.rejectRequest(id, scalerId));
    }
}
