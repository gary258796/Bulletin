package gary.springframework.bulletin.web.services.impl;

import gary.springframework.bulletin.data.entity.ResetPasswordToken;
import gary.springframework.bulletin.data.entity.User;
import gary.springframework.bulletin.web.repositories.ResetPasswordTokenRepository;
import gary.springframework.bulletin.web.services.ResetPasswordTokenService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class ResetPasswordTokenServiceImpl implements ResetPasswordTokenService {

    private final MessageSource messageSource;
    private final ResetPasswordTokenRepository resetPasswordTokenRepository;

    public ResetPasswordTokenServiceImpl(MessageSource messageSource, ResetPasswordTokenRepository resetPasswordTokenRepository) {
        this.messageSource = messageSource;
        this.resetPasswordTokenRepository = resetPasswordTokenRepository;
    }

    /** -------------Custom Methods------------- */

    @Override
    public ResetPasswordToken createPasswordResetTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        ResetPasswordToken pwdToken = new ResetPasswordToken(token, user);
        return resetPasswordTokenRepository.save(pwdToken);
    }

    /**
     *
     * @param token
     * @return String as result. If not null means there is some problem of the token
     */
    @Override
    public String validatePasswordResetToken(String token) {
        Locale locale = LocaleContextHolder.getLocale();

        ResetPasswordToken resetPasswordToken = resetPasswordTokenRepository.findByToken(token);

        return !isTokenFound(resetPasswordToken) ? messageSource.getMessage("invalidToken", null, locale)
                : isTokenExpired(resetPasswordToken) ? messageSource.getMessage("expired", null, locale)
                : null ;
    }

    private Boolean isTokenFound(ResetPasswordToken resetPasswordToken) {
        return resetPasswordToken != null ;
    }

    private Boolean isTokenExpired(ResetPasswordToken resetPasswordToken){
        final Calendar calendar = Calendar.getInstance();
        return resetPasswordToken.getExpiryDate().before(calendar.getTime());
    }

    /** -------------Below are methods of CrudService------------- */

    @Override
    public Set<ResetPasswordToken> findAll() {
        Set<ResetPasswordToken> resetPasswordTokens = new HashSet<>();
        resetPasswordTokenRepository.findAll().forEach(resetPasswordTokens::add);
        return resetPasswordTokens;
    }

    @Override
    public ResetPasswordToken findById(Long aLong) {
        return resetPasswordTokenRepository.findById( aLong).orElse(null);
    }

    @Override
    public ResetPasswordToken save(ResetPasswordToken object) {
        return resetPasswordTokenRepository.save(object);
    }

    @Override
    public void delete(ResetPasswordToken object) {
        resetPasswordTokenRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        resetPasswordTokenRepository.deleteById(aLong);
    }
}
