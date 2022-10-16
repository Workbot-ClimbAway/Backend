package workbot.climbawayapi.climbaway.resource;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SaveClimbingGymResource {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String password;

    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String district;

    @NotBlank
    @Size(max = 1000)
    private String address;

    @NotBlank
    @Size(max = 100)
    private String phone;

    @NotBlank
    @Size(max = 1000)
    private String logo_url;
}
