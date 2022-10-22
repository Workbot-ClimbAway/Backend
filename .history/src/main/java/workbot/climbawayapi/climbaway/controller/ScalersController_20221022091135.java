package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.ClimbingGymService;
import workbot.climbawayapi.climbaway.mapping.NotificationMapper;
import workbot.climbawayapi.climbaway.mapping.ScalersMapper;
import workbot.climbawayapi.climbaway.resource.NotificationResource;
import workbot.climbawayapi.climbaway.resource.ScalersResource;
import workbot.climbawayapi.climbaway.resource.SaveScalersResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/scalers-controller")
public class ScalersController {

    private final ClimbingGymService climbingGymService;
    private final ScalersMapper scalersMapper;
    private final NotificationMapper notificationMapper;

    public ScalersController(ClimbingGymService climbingGymService, ScalersMapper scalersMapper, NotificationMapper notificationMapper) {
        this.climbingGymService = climbingGymService;
        this.scalersMapper = scalersMapper;
        this.notificationMapper = notificationMapper;
    }

    @GetMapping
    public List<ScalersResource> getAllScalers(){
        return scalersMapper.toResource(climbingGymService.getAll());
    }

    @GetMapping(value = "/notification/{id}")
    public List<NotificationResource> findNotificationsByUserId(@PathVariable Long id){
        return notificationMapper.toResource(climbingGymService.findByUserId(id));
    }

    @GetMapping(value = "/{id}")
    public ScalersResource findScalersById(@PathVariable Long id){
        return scalersMapper.toResource(climbingGymService.findById(id));
    }

    @PostMapping
    public ScalersResource createScalers(@RequestBody SaveScalersResource resource){
        return scalersMapper.toResource(climbingGymService.create(scalersMapper.toModel(resource)));
    }

    @PutMapping(value = "/{id}")
    public ScalersResource updateScalers(@PathVariable Long id, @RequestBody SaveScalersResource resource){
        return scalersMapper.toResource(climbingGymService.update(id, scalersMapper.toModel(resource)));
    }

    @DeleteMapping(value = "/{id}")
    public ScalersResource deleteScalers(@PathVariable Long id){
        return scalersMapper.toResource(climbingGymService.delete(id));
    }

    @PostMapping(value = "/{id}/notification")
    
}
