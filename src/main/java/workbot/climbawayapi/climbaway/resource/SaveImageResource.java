package workbot.climbawayapi.climbaway.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SaveImageResource {

    @NotBlank
    @Size(max = 1000)
    private String urlImage;

}
