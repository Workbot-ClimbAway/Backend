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
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "notification")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "mensaje")
    private String mensaje;

    @NotNull
    @NotBlank
    @Column(name = "scaler_id")
    private Long scalerId;

    @JsonIgnore
    @OneToMany(mappedBy = "scaler",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Notification>  notifications;
}
