package workbot.climbawayapi.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.security.domain.service.ScalersService;
import workbot.climbawayapi.security.mapping.ScalersMapper;
import workbot.climbawayapi.security.resource.ScalersResource;
import workbot.climbawayapi.security.resource.SaveScalersResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/scaler")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ScalersController {
    private final ScalersService scalersService;
    private final ScalersMapper scalersMapper;

    public ScalersController(ScalersService scalersService, ScalersMapper scalersMapper) {
        this.scalersService = scalersService;
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

    @GetMapping(value = "/email/{email}")
    public ScalersResource findScalersByEmail(@PathVariable String email){
        return scalersMapper.toResource(scalersService.findByEmail(email));
    }

    @PostMapping
    public ScalersResource createScalers(@RequestBody SaveScalersResource resource){
        return scalersMapper.toResource(scalersService.create(scalersMapper.toModelSaveResource(resource)));
    }

    @PutMapping(value = "/{id}")
    public ScalersResource updateScalers(@PathVariable Long id, @RequestBody SaveScalersResource resource){
        return scalersMapper.toResource(scalersService.update(id, scalersMapper.toModelSaveResource(resource)));
    }

    @DeleteMapping(value = "/{id}")
    public ScalersResource deleteScalers(@PathVariable Long id){
        return scalersMapper.toResource(scalersService.delete(id));
    }

    @GetMapping(value = "email/{email}/password/{password}")
    public ScalersResource findByEmailAndPasswaord(@PathVariable String email,@PathVariable String password){
        return scalersMapper.toResource(scalersService.findByEmailAndPassword(email,password));
    }

}
