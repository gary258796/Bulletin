package gary.springframework.bulletin.web.services.impl;

import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.data.entity.token.VerificationToken;
import gary.springframework.bulletin.web.repositories.VerificationTokenRepository;
import gary.springframework.bulletin.web.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private final VerificationTokenRepository tokenRepository;

    @Autowired
    public VerificationTokenServiceImpl(VerificationTokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    /** -------------Custom Methods------------- */

    @Override
    public void createVerificationTokenForUser(final User user, final String token) {
        final VerificationToken verificationToken = new VerificationToken(token, user);
        tokenRepository.save(verificationToken);
    }

    @Override
    public VerificationToken getVerificationToken(String token) {
        return tokenRepository.findByToken(token);
    }

    /**
     * 產生新的驗證Token並更新Verification Table
     * @param oldToken
     * @return
     */
    @Override
    public VerificationToken generateNewVerificationToken(String oldToken) {

        VerificationToken existingToken = tokenRepository.findByToken(oldToken);

        existingToken.updateToken(UUID.randomUUID().toString()); // 更新token

        return tokenRepository.save(existingToken);
    }

    /** -------------Below are methods of CrudService------------- */

    @Override
    public Set<VerificationToken> findAll() {
        return new HashSet<>(tokenRepository.findAll());
    }

    @Override
    public VerificationToken findById(Long aLong) {
        return tokenRepository.findById(aLong).orElse(null);
    }

    @Override
    public VerificationToken save(VerificationToken object) {
        return tokenRepository.save(object);
    }

    @Override
    public void delete(VerificationToken object) {
        tokenRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        tokenRepository.deleteById(aLong);
    }
}
