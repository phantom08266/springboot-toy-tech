package study.toy.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    AuditorAware<String> auditorProvider() {
        return () -> Optional.of("admin");
    }
}
