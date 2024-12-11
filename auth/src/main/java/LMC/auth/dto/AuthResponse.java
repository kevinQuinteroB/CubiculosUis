package LMC.auth.dto;

import LMC.auth.models.Estudiante;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    String token;
    Estudiante estudiante;
    String role;
}
