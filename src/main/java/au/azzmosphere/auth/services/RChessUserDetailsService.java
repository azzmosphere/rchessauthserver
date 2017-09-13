package au.azzmosphere.auth.services;

import au.azzmosphere.auth.persistence.RChessUserPrincipal;
import au.azzmosphere.auth.persistence.dao.UserRepository;
import au.azzmosphere.auth.persistence.enitites.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
@ConfigurationProperties("app.user")
public class RChessUserDetailsService  implements UserDetailsService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(RChessUserDetailsService.class);

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("recieved request to find user " + username);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new RChessUserPrincipal(user);
    }

    /**
     * registers a user to the system. Once registered the user can login into the
     * the system.
     *
     * @param user
     */
    public void registerUser(User user) {
        logger.debug("registering user " + user + " with details " + user.toString());
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1);
        Date accountExpires = calendar.getTime();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountExpires(accountExpires);
        user.setAccountLocked(false);
        user.setEnabled(true);


        userRepository.save(user);

    }


}
