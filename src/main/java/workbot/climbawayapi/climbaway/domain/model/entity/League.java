package workbot.climbawayapi.climbaway.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "leagues")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class League {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "url_photo")
    private String urlPhoto;

    @NotNull
    @NotBlank
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "number_participants")
    private long numberParticipants;

    @NotNull
    @NotBlank
    @Column(name = "administator")
    private String administrator;

    @NotNull
    @Column(name = "climbing_gym_id")
    private long climbingGymId;

    @NotNull
    @Column(name = "scaler_id")
    private long scaleId;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "scaler_id",insertable = false, updatable = false)
    private Scalers scaler;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "climbing_gym_id",insertable = false, updatable = false)
    private ClimbingGym climbingGym;

    // Request
    @JsonBackReference
    @ManyToMany(mappedBy = "leagues_requested", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Scalers> scaler_leagues = new HashSet<>();

    //Climbers-leagues
    @JsonIgnore
    @OneToMany(mappedBy = "league",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> memberLeagues;

    //Competition_leagues
    @JsonIgnore
    @OneToMany(mappedBy = "league",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompetitionLeague> competitionLeagues;
}
