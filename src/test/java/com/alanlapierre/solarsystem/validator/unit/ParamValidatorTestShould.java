package com.alanlapierre.solarsystem.validator.unit;

import java.util.function.Predicate;

import org.junit.Test;

import com.alanlapierre.solarsystem.validator.ParamValidator;

public class ParamValidatorTestShould {
	
	@Test(expected = IllegalArgumentException.class)
	public void throw_exception_when_condition_is_TRUE() throws Exception {
		
		Predicate<Integer> predicate = (i) -> i == null;
		
		ParamValidator.test(null, predicate);
		
	}
	


}
