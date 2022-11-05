package workbot.climbawayapi.climbaway.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "comments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "score")
    private double score;

    @NotNull
    @Column(name = "climbing_gym_id")
    private long climbingGymId;

    @NotNull
    @Column(name = "scale_id")
    private long scaleId;


    @NotNull
    @Column(name = "date")
    private Date date;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "scaler_id",insertable = false, updatable = false)
    private Scalers scaler;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "climbing_gym_id",insertable = false, updatable = false)
    private ClimbingGym climbingGym;

}
