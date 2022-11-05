package workbot.climbawayapi.climbaway.resource;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
public class LeagueResource {

    private Long id;
    private String name;
    private String urlPhoto;
    private String description;
    private long numberParticipants;
    private String administrator;

}
