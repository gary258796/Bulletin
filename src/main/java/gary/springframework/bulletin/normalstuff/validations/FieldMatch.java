package gary.springframework.bulletin.normalstuff.validations;

import gary.springframework.bulletin.normalstuff.validations.validator.FieldMatchValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE,ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = FieldMatchValidator.class)
@Documented
public @interface FieldMatch {
    String message() default "The fields must match.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String first();
    String second();

    @Target({TYPE,ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List{
        // 加了這個之後可以同時驗證多個 Ex :
        // @FieldMatch.List({
        //        @FieldMatch(first = "password", second = "confirmPassword", message = "The password fields must match"),
        //        @FieldMatch(first = "email", second = "confirmEmail", message = "The email fields must match")
        //})
        FieldMatch[] value();
    }
}