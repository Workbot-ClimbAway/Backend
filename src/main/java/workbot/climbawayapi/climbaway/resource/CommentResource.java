package workbot.climbawayapi.climbaway.resource;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Setter
@Getter
public class CommentResource {

    private Long id;
    private String description;
    private double score;
    private Date date;

}
