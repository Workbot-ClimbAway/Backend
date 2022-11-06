package workbot.climbawayapi.climbaway.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.FeatureService;
import workbot.climbawayapi.climbaway.mapping.FeatureMapper;
import workbot.climbawayapi.climbaway.resource.FeatureResource;
import workbot.climbawayapi.climbaway.resource.SaveFeatureResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/features")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class FeatureController {
    private final FeatureService featureService;
    private final FeatureMapper featureMapper;

    public FeatureController(FeatureService featureService, FeatureMapper featureMapper) {
        this.featureService = featureService;
        this.featureMapper = featureMapper;
    }

    @GetMapping
    public List<FeatureResource> getAll(){
        return featureMapper.toResource(featureService.getAll());
    }

    @GetMapping(value = "/{id}")
    public FeatureResource getFeatureById(@PathVariable Long id){
        return featureMapper.toResource(featureService.findById(id));
    }

    @PostMapping(value = "/climbing-gyms/{id}")
    public FeatureResource createFeature(@RequestBody SaveFeatureResource resource, @PathVariable Long id){
        return featureMapper.toResource(featureService.create(featureMapper.toModelSaveResource(resource),id));
    }

    @PutMapping(value = "/climbing-gyms/{id}")
    public FeatureResource updateFeature(@RequestBody SaveFeatureResource resource, @PathVariable Long id){
        return featureMapper.toResource(featureService.update(featureMapper.toModelSaveResource(resource),id));
    }

    @DeleteMapping(value = "/{id}")
    public FeatureResource deleteFeature(@PathVariable Long id){
        return featureMapper.toResource(featureService.delete(id));
    }


}
