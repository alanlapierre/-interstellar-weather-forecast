package com.alanlapierre.solarsystem.validator;

import java.util.function.Predicate;

public class ConditionsComposer {

	@SafeVarargs
	public static <T> Predicate<T> or(Predicate<T> ...predicates) {
		Predicate<T> combinePredicates= predicates[0];
		for (Predicate<T> predicate : predicates) {
			combinePredicates=combinePredicates.or(predicate);
		}
		return combinePredicates;
	}
	
}
