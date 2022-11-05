package workbot.climbawayapi.climbaway.resource;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class SaveFeatureResource {
    @NotBlank
    @Size(max = 100)
    private String type_climb;

    @NotBlank
    @Size(max = 100)
    private String start_office_hours;
    @NotBlank
    @Size(max = 100)
    private String end_office_hours;
    @NotBlank
    @Size(max = 100)
    private String days_of_week;

    private Integer routes;

    private Double max_height;

    @NotBlank
    @Size(max = 100)
    private String rock_type;

    @NotBlank
    @Size(max = 100)
    private String bolting;

    private Double price;
}
