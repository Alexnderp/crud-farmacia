package com.generation.crud_farmacia.utils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class PriceValidator implements ConstraintValidator<ValidationPrice, BigDecimal> {

    @Override
    public boolean isValid(BigDecimal price, ConstraintValidatorContext context) {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }
}
