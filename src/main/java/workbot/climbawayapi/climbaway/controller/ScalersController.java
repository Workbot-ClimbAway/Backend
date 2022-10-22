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

    private final ScalersService scalersService;
    private final NotificationMapper notificationMapper;
    private final ScalersMapper scalersMapper;

    public ScalersController(ScalersService scalersService, NotificationMapper notificationMapper, ScalersMapper scalersMapper) {
        this.scalersService = scalersService;
        this.notificationMapper = notificationMapper;
        this.scalersMapper = scalersMapper;
    }

    @GetMapping
    public List<ScalersResource> getAllScalers(){
        return scalersMapper.toResource(scalersService.getAll());
    }

    @GetMapping(value = "/{id}")
    public ScalersResource findScalersById(@PathVariable Long id){
        return scalersMapper.toResource(scalersService.findById(id));
    }

    @GetMapping(value = "/scalers-controller/{id}")
    public List<ScalersResource> findScalersByUserId(@PathVariable Long id){
        return scalersMapper.toResource(scalersService.findByUserId(id));
    }

    @PostMapping
    public ScalersResource createScalers(@RequestBody SaveScalersResource resource){
        return scalersMapper.toResource(scalersService.create(scalersMapper.toModel(resource)));
    }

    @PutMapping(value = "/{id}")
    public ScalersResource updateScalers(@PathVariable Long id, @RequestBody SaveScalersResource resource){
        return scalersMapper.toResource(scalersService.update(id, scalersMapper.toModel(resource)));
    }

    @DeleteMapping(value = "/{id}")
    public ScalersResource deleteScalers(@PathVariable Long id){
        return scalersMapper.toResource(scalersService.delete(id));
    }


    /*
    @PostMapping(value = "/{id}/notification/{notificationId}")
    public ScalersResource assignNotification(@PathVariable Long id, @PathVariable Long notificationId){
        return scalersMapper.toResource(scalersService.assignNotification(id, notificationId));
    }
     */
}
