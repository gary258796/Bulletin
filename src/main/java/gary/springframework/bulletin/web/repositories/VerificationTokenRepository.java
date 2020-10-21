package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(User user);

}
