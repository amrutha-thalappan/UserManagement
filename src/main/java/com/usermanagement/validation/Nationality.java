package com.usermanagement.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom Annotation for applying country validation
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CountryValidator.class)
public @interface Nationality {
    String message() default "{com.usermanagement.validation.Nationality.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
