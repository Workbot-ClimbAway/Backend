package workbot.climbawayapi.climbaway.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import workbot.climbawayapi.security.domain.model.entity.Scalers;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "competitions_leagues_rankings")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionLeagueRanking {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "competition_league_id")
    private Long competitionLeagueId;

    @NotNull
    @Column(name = "scale_id")
    private Long scaleId;

    @NotNull
    @Column(name = "score")
    private Double score;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "competition_league_id",insertable = false, updatable = false)
    private CompetitionLeague competitionLeague;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "scale_id",insertable = false, updatable = false)
    private Scalers scale;

}
