package com.elouissi.hunters_league.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Constraint(validatedBy = MinDaysInFutureValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface MinDaysInFuture {
    String message() default "The date must be at least {days} days in the future";
    int days() default 2;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
