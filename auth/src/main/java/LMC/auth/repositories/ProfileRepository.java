package LMC.auth.repositories;

import LMC.auth.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Estudiante, Long> {
}
