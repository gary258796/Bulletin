package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);

    User findByUserEmail(String email);

    User findByUserNameAndUserEmail(String userName, String email);
}
