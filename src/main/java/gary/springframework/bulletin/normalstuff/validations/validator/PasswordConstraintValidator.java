package gary.springframework.bulletin.normalstuff.validations.validator;

import gary.springframework.bulletin.normalstuff.validations.ValidPassword;
import org.passay.*;
import com.google.common.base.Joiner;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public void initialize(ValidPassword constraintAnnotation) {
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        // Use PasswordValidator from org.passay
        final PasswordValidator validator = new PasswordValidator(Arrays.asList(
                new LengthRule(8, 30), // MIN && MAX LENGTH
                new DigitCharacterRule(1), // At least have N digital characters in your password
                new NumericalSequenceRule(3, false), // Can't have continuous N numbers, ex: 123 is not acceptable
                new AlphabeticalCharacterRule(1), // At least have N alphabet characters in your password
                new WhitespaceRule() // Can't have whitespace in password
        ));

        final RuleResult ruleResult = validator.validate(new PasswordData(password));
        if( ruleResult.isValid())
            return true ;

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
                Joiner.on(',').join(validator.getMessages(ruleResult))).addConstraintViolation();
        return false;
    }
}
