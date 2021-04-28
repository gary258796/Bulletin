package gary.springframework.bulletin.normalstuff.validations.validator;

import gary.springframework.bulletin.normalstuff.validations.FieldMatch;
import org.apache.commons.beanutils.BeanUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Annotation @FieldMatch will call this validator to help validate are fields matched or not
 */
public class FieldMatchValidator
        implements ConstraintValidator<FieldMatch, Object> {

    private String firstFieldName;
    private String secondFieldName;
    private String message;

    @Override
    public void initialize(FieldMatch constraintAnnotation) {
        this.firstFieldName = constraintAnnotation.first();
        this.secondFieldName = constraintAnnotation.second();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context){

        // default set field matched, valid = true
        boolean valid = true;

        try {
            // get two fields content
            final Object firstObj = BeanUtils.getProperty(obj, firstFieldName);
            final Object secondObj = BeanUtils.getProperty(obj, secondFieldName);
            // if not same , set valid as false
            valid = (firstObj == null && secondObj == null) || (firstObj != null && firstObj.equals(secondObj));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // if valid == false , re-defined returned message
        if( !valid ){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName) // 指定是哪個欄位要報錯
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation(); // 禁用default的message訊息
        }

        return valid;
    }
}
