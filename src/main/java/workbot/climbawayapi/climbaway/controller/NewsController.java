package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.NewsService;
import workbot.climbawayapi.climbaway.mapping.NewsMapper;
import workbot.climbawayapi.climbaway.resource.NewsResource;
import workbot.climbawayapi.climbaway.resource.SaveNewsResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/news")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class NewsController {
    private final NewsService newsService;
    private final NewsMapper newsMapper;

    public NewsController(NewsService newsService, NewsMapper newsMapper) {
        this.newsService = newsService;
        this.newsMapper = newsMapper;
    }

    @GetMapping
    public List<NewsResource> getAllNews(){
        return newsMapper.toResource(newsService.getAll());
    }

    @GetMapping(value = "/{id}")
    public NewsResource findNewsById(@PathVariable Long id){
        return newsMapper.toResource(newsService.findById(id));
    }

    @GetMapping(value = "/climbingGym/{id}")
    public List<NewsResource> findByClimbingGymId(@PathVariable Long id){
        return newsMapper.toResource(newsService.findByClimbingGymId(id));
    }

    @PostMapping(value = "/climbingGym/{id}")
    public NewsResource createNews(@RequestBody SaveNewsResource saveNewsResource, @PathVariable Long id){
        return newsMapper.toResource(newsService.create(newsMapper.toModelSaveResource(saveNewsResource), id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteNews(@PathVariable Long id){
        newsService.delete(id);
    }


}
