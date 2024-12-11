package LMC.auth.services;

import LMC.auth.models.Estudiante;
import LMC.auth.repositories.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public void deleteProfile(Long profileId) {
        profileRepository.deleteById(profileId);
    }

    public Estudiante getProfile(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new IllegalArgumentException("Profile does not exist"));
    }

    public Estudiante saveProfile(Estudiante estudiante) {
        return profileRepository.save(estudiante);
    }


}
