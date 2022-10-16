package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.ClimbingGymService;
import workbot.climbawayapi.climbaway.mapping.ClimbingGymMapper;
import workbot.climbawayapi.climbaway.resource.ClimbingGymResource;
import workbot.climbawayapi.climbaway.resource.SaveClimbingGymResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/climbing-gyms")
public class ClimbingGymController {

    private final ClimbingGymService climbingGymService;
    private final ClimbingGymMapper climbingGymMapper;

    public ClimbingGymController(ClimbingGymService climbingGymService, ClimbingGymMapper climbingGymMapper) {
        this.climbingGymService = climbingGymService;
        this.climbingGymMapper = climbingGymMapper;
    }

    @GetMapping
    public List<ClimbingGymResource> getAllClimbingGyms(){
        return climbingGymMapper.toResource(climbingGymService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ClimbingGymResource findClimbingGymById(@PathVariable Long id){
        return climbingGymMapper.toResource(climbingGymService.findById(id));
    }

    @PostMapping
    public ClimbingGymResource createClimbingGym(@RequestBody SaveClimbingGymResource resource){
        return climbingGymMapper.toResource(climbingGymService.create(climbingGymMapper.toModelSaveResource(resource)));
    }

    @PutMapping(value = "/{id}")
    public ClimbingGymResource updateClimbingGym(@PathVariable Long id, @RequestBody ClimbingGymResource climbingGymResource){
        return climbingGymMapper.toResource(climbingGymService.update(id, climbingGymMapper.toModel(climbingGymResource)));
    }

    @DeleteMapping(value = "/{id}")
    public ClimbingGymResource deleteClimbingGym(@PathVariable Long id){
        return climbingGymMapper.toResource(climbingGymService.delete(id));
    }

}
