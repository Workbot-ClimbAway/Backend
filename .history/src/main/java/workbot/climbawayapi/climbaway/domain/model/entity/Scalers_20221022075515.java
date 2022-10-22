package workbot.climbawayapi.climbaway.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

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

    // Relationships
    @OneToMany
    @JoinTable(name = "scalers_notifications",
            joinColumns = @JoinColumn(name = "scaler_id"),
            inverseJoinColumns = @JoinColumn(name = "notification_id"))
    private Set<Notification> Notification = new HashSet<>();
}