package haui.stores.validations.validators;

import haui.stores.validations.annotaions.Equals;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.InvocationTargetException;

public class EqualsValidator implements ConstraintValidator<Equals, Object> {

    private String target;

    private String source;

    private String message;

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {
        boolean valid = true;
        try {
            String target = BeanUtils.getProperty(object, this.target);
            String source = BeanUtils.getProperty(object, this.source);
            valid = target.equals(source) || StringUtils.isBlank(target) || StringUtils.isBlank(source);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            //ignore
        }
        if (!valid) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(target)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }
        return valid;
    }

    @Override
    public void initialize(Equals constraintAnnotation) {
        target = constraintAnnotation.target();
        source = constraintAnnotation.source();
        message = constraintAnnotation.message();
    }
}
