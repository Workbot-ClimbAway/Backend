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
public class FeatureResource {
    private Long id;
    private String type_climb;
    private String start_office_hours;
    private String end_office_hours;
    private String days_of_week;
    private Integer routes;
    private Double max_height;
    private String rock_type;
    private String bolting;
    private Double price;
}
