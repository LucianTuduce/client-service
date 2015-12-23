package ro.fortech.constraints;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ro.fortech.validator.CheckUnicityValidator;

@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckUnicityValidator.class)
@Documented
public @interface ConstraintUnicity {

	String message() default "{FIN must be unique}";
	
	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	
	
}
