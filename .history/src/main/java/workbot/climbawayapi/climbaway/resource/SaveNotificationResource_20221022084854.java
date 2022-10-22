package workbot.climbawayapi.climbaway.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SaveNotificationResource {

    @NotBlank
    @Size(max = 100)
    private String name;

}
public class SaveNotificationResource {
}
