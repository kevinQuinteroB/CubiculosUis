package LMC.auth.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estudiante")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estudiante {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name="id_estado_estudiante")
    private long idEstado;

    @Column(name="codigo")
    private int codigo;

    @JsonIgnore
    @Column(name = "horas_disp", nullable = false, columnDefinition = "integer default 2")
    private int horasDisp = 2;

    @JsonIgnore
    @OneToOne(mappedBy = "estudiante", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AuthData authData;
}
