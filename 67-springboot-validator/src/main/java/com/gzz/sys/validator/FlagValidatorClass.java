package com.gzz.sys.validator;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Object> {

	private String values;

	@Override
	public void initialize(FlagValidator flagValidator) {
		this.values = flagValidator.values();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		if (Arrays.asList(values.split(",")).contains(value)) {
			return true;
		}
		return false;
	}
}
