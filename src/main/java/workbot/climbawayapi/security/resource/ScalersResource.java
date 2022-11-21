package workbot.climbawayapi.security.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScalersResource {

    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String city;
    private String district;
    private String url_photo;
    private String address;
    private String email;
    private String phone;

}
