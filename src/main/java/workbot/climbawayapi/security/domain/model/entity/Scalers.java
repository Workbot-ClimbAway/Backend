package workbot.climbawayapi.security.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import workbot.climbawayapi.climbaway.domain.model.entity.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "scalers")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Scalers implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @NotNull
    @NotBlank
    @Column(name = "city")
    private String city;

    @NotNull
    @NotBlank
    @Column(name = "district")
    private String district;

    @NotNull
    @NotBlank
    @Column(name = "url_photo")
    private String url_photo;

    @NotNull
    @NotBlank
    @Column(name = "email")
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "address")
    private String address;

    @NotNull
    @NotBlank
    @Column(name = "phone")
    private String phone;

    @JsonIgnore
    @OneToMany(mappedBy = "scaler",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification> notifications;

    // Relationships
    @JsonBackReference
    @ManyToMany(mappedBy = "scaler", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ClimbingGym> climbingGyms = new HashSet<>();

    // Comments
    @JsonIgnore
    @OneToMany(mappedBy = "scaler",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    // Leagues
    @JsonIgnore
    @OneToMany(mappedBy = "scaler",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<League> leagues;

    // Requests
    @ManyToMany
    @JoinTable(name = "requests",
            joinColumns = @JoinColumn(name = "scaler_id"),
            inverseJoinColumns = @JoinColumn(name = "league_id"))
    private Set<League> leagues_requested = new HashSet<>();

    //Climbers-leagues
    @JsonIgnore
    @OneToMany(mappedBy = "scale",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Member> memberLeagues;

    //Competition_leagues_rankings
    @JsonIgnore
    @OneToMany(mappedBy = "scale",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompetitionLeagueRanking> competitionLeagueRankings;
}