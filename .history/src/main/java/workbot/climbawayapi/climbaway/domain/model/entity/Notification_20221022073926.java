package workbot.climbawayapi.climbaway.domain.model.entity;

@Entity
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Notification {
    
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


    @JsonBackReference
    @ManyToOne(mappedBy = "notifications", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<>;
}
