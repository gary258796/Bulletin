package gary.springframework.bulletin.configs;

import gary.springframework.bulletin.normalstuff.validations.validator.PasswordMatchesValidator;
import gary.springframework.bulletin.normalstuff.validations.validator.ValidEmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

@Configuration
public class BeanConfig {

    @Bean
    public PasswordMatchesValidator passwordMatchesValidator() {
        return new PasswordMatchesValidator();
    }

    @Bean
    public ValidEmailValidator usernameValidator() {
        return new ValidEmailValidator();
    }

    @Bean
    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

}
