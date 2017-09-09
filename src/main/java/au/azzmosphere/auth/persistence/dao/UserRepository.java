package au.azzmosphere.auth.persistence.dao;

import au.azzmosphere.auth.persistence.enitites.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User createUser(String username, String password);
}
