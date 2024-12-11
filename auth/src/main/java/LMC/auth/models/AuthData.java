package LMC.auth.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "auth_data")
@AllArgsConstructor
@NoArgsConstructor
public class AuthData {
    @Id
    @Column(name = "estudiante_id")
    private long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Builder.Default
    private boolean enabled = true;

    @OneToOne
    @MapsId
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;
}
