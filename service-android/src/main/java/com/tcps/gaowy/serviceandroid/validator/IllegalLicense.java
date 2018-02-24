package com.tcps.gaowy.serviceandroid.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 事故校验
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IllegalLicenseValidator.class)
public @interface IllegalLicense {

    String message() default "{校验没通过！}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
