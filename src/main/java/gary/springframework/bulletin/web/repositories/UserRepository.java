package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

    User findByEmail(String email);

    User findByUserNameAndEmail(String userName, String email);
}
