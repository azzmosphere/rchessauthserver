package au.azzmosphere.auth.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Defines the basic security configuration that the service will use.
 */

@Configuration
@EnableWebSecurity
public class RoboChessAuthWebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();

        // Permit all users to login
        http.authorizeRequests().antMatchers("/login*").permitAll()

        // Permit anyone to create a account
        .and().authorizeRequests().antMatchers("/user/registration").permitAll()

        // only system users can verify users
        .and().authorizeRequests().antMatchers("/verify**").hasRole("SYSTEM");
    }


}
