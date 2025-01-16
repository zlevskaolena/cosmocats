package com.cosmocats.intergalacticmarketplace.service.mapper.wordcheck;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class CosmicWordCheckValidator implements ConstraintValidator<CosmicWordCheck, String> {
    private static final List<String> COSMIC_TERMS = Arrays.asList("star", "galaxy", "comet");

    @Override
    public void initialize(CosmicWordCheck constraintAnnotation) {
        // Ініціалізація, якщо необхідно
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Якщо значення null, перевірка не проводиться
        }

        // Перевірка наявності космічного терміну у назві
        return COSMIC_TERMS.stream().anyMatch(value.toLowerCase()::contains);
    }
}
