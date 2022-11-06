package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.ImageService;
import workbot.climbawayapi.climbaway.mapping.ImageMapper;
import workbot.climbawayapi.climbaway.resource.ImageResource;
import workbot.climbawayapi.climbaway.resource.SaveImageResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/images")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class ImageController {
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    public ImageController(ImageService imageService, ImageMapper imageMapper) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @GetMapping
    public List<ImageResource> getall(){
        return imageMapper.toResource(imageService.getAll());
    }
    @GetMapping(value = "/{id}")
    public ImageResource getImageById(@PathVariable Long id){
        return imageMapper.toResource(imageService.findById(id));
    }

    @GetMapping(value = "/climbing-gyms/{id}")
    public List<ImageResource> getImageByClimbingGymId(@PathVariable Long id){
        return imageMapper.toResource(imageService.findByClimbingGymId(id));
    }

    @PostMapping(value = "/climbing-gyms/{id}")
    public ImageResource createImage(@RequestBody SaveImageResource resource, @PathVariable Long id){
        return imageMapper.toResource(imageService.create(imageMapper.toModelSaveResource(resource),id));
    }

    @DeleteMapping(value = "/{id}")
    public ImageResource deleteImage(@PathVariable Long id){
        return imageMapper.toResource(imageService.delete(id));
    }

}
