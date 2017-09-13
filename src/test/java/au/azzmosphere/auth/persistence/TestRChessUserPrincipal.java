package au.azzmosphere.auth.persistence;

import au.azzmosphere.auth.persistence.enitites.User;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class TestRChessUserPrincipal {
    private User user =  mock(User.class);
    private RChessUserPrincipal rChessUserPrincipal;
    private final static long DAY_IN_MS = 1000 * 60 * 60 * 24;

    @Before
    public void setup() {
        rChessUserPrincipal = new RChessUserPrincipal(user);
    }

    @Test
    public void testPassword() {
        String password = "password";
        when(rChessUserPrincipal.getPassword()).thenReturn(password);
        assertThat(rChessUserPrincipal.getPassword(), is(password));
    }

    @Test
    public void testUsername() {
        String username = "username";
        when(rChessUserPrincipal.getUsername()).thenReturn(username);
        assertThat(rChessUserPrincipal.getUsername(), is(username));
    }

    @Test
    public void testAccountIsExpired() {

        // 7 days ago
        when(user.getAccountExpires()).thenReturn(new Date(System.currentTimeMillis() - (7 * DAY_IN_MS)));

        assertThat(rChessUserPrincipal.isAccountNonExpired(), is(false));
    }

    @Test
    public void testAccountIsNonExpired() {

        // 7 days in the future
        when(user.getAccountExpires()).thenReturn(new Date(System.currentTimeMillis() + (7 * DAY_IN_MS)));

        assertThat(rChessUserPrincipal.isAccountNonExpired(), is(true));
    }

    @Test
    public void testIsAccountNonLocked() {
        when(user.isAccountLocked()).thenReturn(false);

        assertThat(rChessUserPrincipal.isAccountNonLocked(), is(true));
    }

    @Test
    public void testIsAccountLocked() {
        when(user.isAccountLocked()).thenReturn(true);

        assertThat(rChessUserPrincipal.isAccountNonLocked(), is(false));
    }

    @Test
    public void testIsCredentialsNonExpired() {
    }
}
