package com.alanlapierre.solarsystem.validator.unit;

import static org.junit.Assert.*;

import java.util.function.Predicate;

import org.junit.Test;

import com.alanlapierre.solarsystem.validator.ConditionsComposer;

public class ConditionComposerOrShould {

	@Test
	public void return_preditate_with_value_of_test_method_equal_TRUE_when_condition_is_TRUE() {
		
		Predicate<Integer> nullValuePredicate = (i) -> i == null;
		Predicate<Integer> zeroValuePredicate = (i) -> i == 0;
			
		Predicate<Integer> predicate = ConditionsComposer.or(nullValuePredicate, zeroValuePredicate);
		assertTrue(predicate.test(null));
		assertTrue(predicate.test(0));
	}
	
	
	@Test
	public void return_preditate_with_value_of_test_method_equal_FALSE_when_condition_is_FALSE() {
		
		Predicate<Integer> nullValuePredicate = (i) -> i == null;
		Predicate<Integer> zeroValuePredicate = (i) -> i == 0;
			
		Predicate<Integer> predicate = ConditionsComposer.or(nullValuePredicate, zeroValuePredicate);
		assertFalse(predicate.test(1));
		assertFalse(predicate.test(2));
	}

	@Test
	public void return_preditate_with_value_of_test_method_equal_TRUE_OR_FALSE_when_condition_is_TRUE_OR_FALSE() {
		
		Predicate<Integer> nullValuePredicate = (i) -> i == null;
		Predicate<Integer> zeroValuePredicate = (i) -> i == 0;
			
		Predicate<Integer> predicate = ConditionsComposer.or(nullValuePredicate, zeroValuePredicate);
		assertTrue(predicate.test(null));
		assertFalse(predicate.test(1));
	}
	

}
