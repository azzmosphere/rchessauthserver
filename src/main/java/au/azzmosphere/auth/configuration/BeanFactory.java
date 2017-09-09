package au.azzmosphere.auth.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

@EnableWebSecurity
@Configuration
public class BeanFactory {

    @Bean
    public UserDetailsService userDetailsService() throws Exception {
        UserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("aaron").password("password").roles("USER").build());
        return manager;
    }
}
