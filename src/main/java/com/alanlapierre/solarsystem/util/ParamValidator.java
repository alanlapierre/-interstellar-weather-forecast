package com.alanlapierre.solarsystem.util;

import java.util.function.Predicate;

public class ParamValidator {
	
	public static <T>  void  test(T value, Predicate<T> predicate) throws IllegalArgumentException {
		
		if(predicate.test(value)) {
			throw new IllegalArgumentException("Argument not valid");
		}
	}

}
