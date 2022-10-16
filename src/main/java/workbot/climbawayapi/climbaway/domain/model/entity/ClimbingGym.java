package workbot.climbawayapi.climbaway.domain.model.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "climbing_gyms")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClimbingGym implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @NotBlank
    @Column(name = "password")
    private String password;

    @NotNull
    @NotBlank
    @Column(name = "email")
    private String email;

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
    @Column(name = "address")
    private String address;

    @NotNull
    @NotBlank
    @Column(name = "phone")
    private String phone;

    @NotNull
    @NotBlank
    @Column(name = "logo")
    private String logo_url;
}
