package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByAccount(String account);

    User findByEmail(String email);

}
