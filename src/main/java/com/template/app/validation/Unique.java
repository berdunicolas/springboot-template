package com.template.app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "El valor ya existe";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String table();
    String column();
}