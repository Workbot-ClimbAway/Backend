package workbot.climbawayapi.climbaway.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "features")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feature {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "type_climb")
    private String type_climb;

    @NotNull
    @NotBlank
    @Column(name = "start_office_hours")
    private String start_office_hours;

    @NotNull
    @NotBlank
    @Column(name = "end_office_hours")
    private String end_office_hours;

    @NotNull
    @NotBlank
    @Column(name = "days_of_week")
    private String days_of_week;

    @NotNull
    @Column(name = "routes")
    private Integer routes;

    @NotNull
    @Column(name = "max_height")
    private Double max_height;

    @NotNull
    @NotBlank
    @Column(name = "rock_type")
    private String rock_type;

    @NotNull
    @NotBlank
    @Column(name = "bolting")
    private String bolting;

    @NotNull
    @Column(name = "price")
    private Double price;

    @OneToOne(mappedBy = "feature")
    private ClimbingGym climbingGym;

}
