package workbot.climbawayapi.climbaway.domain.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "members")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "scale_id")
    private long scaleId;


    @NotNull
    @Column(name = "climbing_gym_id")
    private long climbingGymId;

    @NotNull
    @Column(name = "league_id")
    private long leagueId;

    @NotNull
    @NotBlank
    @Column(name = "status")
    private String status;
    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "climbing_gym_id",insertable = false, updatable = false)
    private ClimbingGym climbingGym;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "scale_id",insertable = false, updatable = false)
    private Scalers scale;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id",insertable = false, updatable = false)
    private League league;

}
