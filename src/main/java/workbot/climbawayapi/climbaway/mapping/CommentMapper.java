package workbot.climbawayapi.climbaway.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import workbot.climbawayapi.climbaway.domain.model.entity.Comment;
import workbot.climbawayapi.climbaway.resource.CommentResource;
import workbot.climbawayapi.climbaway.resource.SaveCommentResource;
import workbot.climbawayapi.shared.mapping.EnhancedModelMapper;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class CommentMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    //Object Mapping

    public CommentResource toResource(Comment model){
        return mapper.map(model, CommentResource.class);
    }

    public Comment toModelSaveResource(SaveCommentResource resource) {
        return mapper.map(resource, Comment.class);
    }

    //List Mapping

    public List<CommentResource> toResource(List<Comment> models){
        return models.stream().map(this::toResource).collect(Collectors.toList());
    }
}
