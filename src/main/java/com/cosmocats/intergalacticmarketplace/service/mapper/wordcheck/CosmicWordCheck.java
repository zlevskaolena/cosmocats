package com.cosmocats.intergalacticmarketplace.service.mapper.wordcheck;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CosmicWordCheckValidator.class)
public @interface CosmicWordCheck {

    String message() default "Назва продукту повинна містити космічні терміни (наприклад, star, galaxy, comet)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
