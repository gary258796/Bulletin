package gary.springframework.bulletin.normalstuff.validations;

import gary.springframework.bulletin.normalstuff.validations.validator.PasswordConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordConstraintValidator.class)
@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "{message.invalid.password}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
