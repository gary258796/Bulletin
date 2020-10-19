package gary.springframework.bulletin.validations.validator;

import gary.springframework.bulletin.models.dto.UserRegistDto;
import gary.springframework.bulletin.validations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator
        implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){
        UserRegistDto user = (UserRegistDto) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}
