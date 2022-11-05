package workbot.climbawayapi.climbaway.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "competitions_leagues")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionLeague {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "date")
    private Date date;

    @NotNull
    @NotBlank
    @Column(name = "type")
    private String type;

    @NotNull
    @Column(name = "league_id")
    private Long leagueId;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "league_id",insertable = false, updatable = false)
    private League league;

    //Competition_leagues_rankings
    @JsonIgnore
    @OneToMany(mappedBy = "competitionLeague",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CompetitionLeagueRanking> competitionLeagueRankings;


}
