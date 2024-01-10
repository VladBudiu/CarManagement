package VladBudiu.proiectPj.Repositories;

import VladBudiu.proiectPj.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInterface extends JpaRepository<User,Long> {
    Optional<User> findByUsername(final String username);
    Optional<User> findByUsernameAndPassword(final String username, final String password);
}
