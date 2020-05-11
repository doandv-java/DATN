package haui.stores.validations.annotaions;

import haui.stores.validations.validators.EqualsValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {EqualsValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER, ElementType.TYPE})
@Retention(RUNTIME)
public @interface Equals {

    String source();

    String target();

    String message() default "{source} must have equals {target}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
