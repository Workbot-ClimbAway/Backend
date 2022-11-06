package workbot.climbawayapi.climbaway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import workbot.climbawayapi.climbaway.domain.service.CommentService;
import workbot.climbawayapi.climbaway.mapping.CommentMapper;
import workbot.climbawayapi.climbaway.resource.CommentResource;
import workbot.climbawayapi.climbaway.resource.SaveCommentResource;

import java.util.List;

@Controller
@RestController
@RequestMapping("/api/v1/comments")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class CommentController {

    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @GetMapping
    public List<CommentResource> getAllComments(){
        return commentMapper.toResource(commentService.getAll());
    }

    @GetMapping("/{id}")
    public CommentResource getCommentById(long id){
        return commentMapper.toResource(commentService.findById(id));
    }

    @GetMapping("/climbing-gym/{climbingGymId}/scaler/{scaleId}")
    public CommentResource findByClimbingGymAndScalerId(@PathVariable Long climbingGymId, @PathVariable Long scaleId){
        return commentMapper.toResource(commentService.findByClimbingGymAndScalerId(climbingGymId, scaleId));
    }

    @GetMapping("/climbing-gym/{climbingGymId}")
    public List<CommentResource> findByClimbingGymId(@PathVariable Long climbingGymId){
        return commentMapper.toResource(commentService.findByClimbingGymId(climbingGymId));
    }

    @PostMapping("/climbing-gym/{climbingGymId}/scaler/{scaleId}")
    public CommentResource createComment(@RequestBody SaveCommentResource saveCommentResource, @PathVariable Long climbingGymId, @PathVariable Long scaleId){
        return commentMapper.toResource(commentService.create(commentMapper.toModelSaveResource(saveCommentResource), climbingGymId, scaleId));
    }

    @PutMapping("/climbing-gym/{climbingGymId}/scaler/{scaleId}")
    public CommentResource updateComment(@RequestBody SaveCommentResource saveCommentResource, @PathVariable Long climbingGymId, @PathVariable Long scaleId){
        return commentMapper.toResource(commentService.update(commentMapper.toModelSaveResource(saveCommentResource), climbingGymId, scaleId));
    }

    @DeleteMapping("/climbing-gym/{climbingGymId}")
    public CommentResource deleteComment(@PathVariable Long climbingGymId){
        return commentMapper.toResource(commentService.delete(climbingGymId));
    }
}
