package gary.springframework.bulletin.normalstuff.validations.validator;

import gary.springframework.bulletin.data.model.dto.UserRegistDto;
import gary.springframework.bulletin.normalstuff.validations.PasswordMatches;

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
