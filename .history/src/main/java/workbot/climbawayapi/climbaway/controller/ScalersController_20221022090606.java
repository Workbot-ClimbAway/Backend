package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.ClimbingGymService;
import workbot.climbawayapi.climbaway.mapping.No;
import workbot.climbawayapi.climbaway.mapping.ClimbingGymMapper;
import workbot.climbawayapi.climbaway.resource.CategoryResource;
import workbot.climbawayapi.climbaway.resource.ClimbingGymResource;
import workbot.climbawayapi.climbaway.resource.SaveClimbingGymResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/scalers-controller")
public class ScalersController {

    private final ClimbingGymService climbingGymService;
    private final CategoryMapper categoryMapper;
    private final ClimbingGymMapper climbingGymMapper;

    public ClimbingGymController(ClimbingGymService climbingGymService,
                                 CategoryMapper categoryMapper,
                                 ClimbingGymMapper climbingGymMapper) {
        this.climbingGymService = climbingGymService;
        this.categoryMapper = categoryMapper;
        this.climbingGymMapper = climbingGymMapper;
    }

    @GetMapping
    public List<ClimbingGymResource> getAllClimbingGyms(){
        return climbingGymMapper.toResource(climbingGymService.getAll());
    }

    @GetMapping(value = "/categories/{id}")
    public List<ClimbingGymResource> findClimbingGymsByCategoryId(@PathVariable Long id){
        return climbingGymMapper.toResource(climbingGymService.findClimbingGymsByCategoryId(id));
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

    @PostMapping(value = "/{id}/categories/{categoryId}")
    public List<CategoryResource> assignCategory(@PathVariable Long id, @PathVariable Long categoryId){
        return categoryMapper.toResource(climbingGymService.createClimbingGymCategory(id, categoryId));
    }
}
