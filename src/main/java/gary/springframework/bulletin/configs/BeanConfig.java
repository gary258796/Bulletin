package gary.springframework.bulletin.configs;

import gary.springframework.bulletin.normalstuff.validations.validator.PasswordMatchesValidator;
import gary.springframework.bulletin.normalstuff.validations.validator.ValidEmailValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

}
