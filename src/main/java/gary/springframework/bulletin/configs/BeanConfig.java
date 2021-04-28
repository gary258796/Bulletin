package gary.springframework.bulletin.configs;

import gary.springframework.bulletin.normalstuff.validations.validator.FieldMatchValidator;
import gary.springframework.bulletin.normalstuff.validations.validator.ValidEmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.i18n.LocaleContextHolder;
import java.util.Locale;

@Configuration
public class BeanConfig {

    /**
     * Validator for checking if two fields content are matched or not
     */
    @Bean
    public FieldMatchValidator passwordMatchesValidator() {
        return new FieldMatchValidator();
    }

    /**
     * Validator for checking Email format
     */
    @Bean
    public ValidEmailValidator usernameValidator() {
        return new ValidEmailValidator();
    }

    /**
     * Locale Bean for get local text
     */
    @Bean
    public Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

}
