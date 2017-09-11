package au.azzmosphere.auth.persistence;

import au.azzmosphere.auth.persistence.enitites.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class RChessUserPrincipal implements UserDetails {
    private User user;

    public RChessUserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return  (new Date()).before(user.getAccountExpires());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !user.isAccountLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return  (new Date()).before(user.getAccountExpires());
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }
}
