package gary.springframework.bulletin.web.services;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.token.VerificationToken;

public interface VerificationTokenService extends CrudService<VerificationToken, Long> {

    void createVerificationTokenForUser(User user, String token);

    VerificationToken getVerificationToken(String token);

    VerificationToken generateNewVerificationToken(String oldToken);
}
