package LMC.auth.config.boostrap;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Data
@Component
@ConfigurationProperties(prefix = "init")
public class DataInitializationProperties {
    private boolean enabled;
    private List<RoleDefinition> roles;
    private List<String> permissions;

    @Getter
    @Setter
    public static class RoleDefinition {
        private String name;
        private Set<String> permissions;
    }
}