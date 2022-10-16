package workbot.climbawayapi.climbaway.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClimbingGymResource {

    private Long id;

    private String name;

    private String password;

    private String email;

    private String city;

    private String district;

    private String address;

    private String phone;

    private String logo_url;
}
