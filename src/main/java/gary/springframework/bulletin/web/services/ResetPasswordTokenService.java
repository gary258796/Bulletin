package gary.springframework.bulletin.web.services;

import gary.springframework.bulletin.data.entity.token.ResetPasswordToken;
import gary.springframework.bulletin.data.entity.User;

public interface ResetPasswordTokenService extends CrudService<ResetPasswordToken, Long> {

    ResetPasswordToken createPasswordResetTokenForUser(User user);

    String validatePasswordResetToken(String token);

}
