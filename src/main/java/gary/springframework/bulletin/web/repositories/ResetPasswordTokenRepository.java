package gary.springframework.bulletin.web.repositories;

import gary.springframework.bulletin.data.entity.token.ResetPasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResetPasswordTokenRepository extends JpaRepository<ResetPasswordToken, Long> {

    ResetPasswordToken findByToken(String token);
}
