package com.foxminded;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResultTest {

	@Test
	public void calculateResultSign_negative_negative_positive() {
		Result result = new Result();
		result.setIsNumeratorNegative(true);
		result.setIsDenominatorNegative(true);
		boolean resultSign = result.calculateResultSign();
		assertFalse(resultSign);
	}

	@Test
	public void calculateResultSign_positive_positive_positive() {
		Result result = new Result();
		result.setIsNumeratorNegative(false);
		result.setIsDenominatorNegative(false);
		boolean resultSign = result.calculateResultSign();
		assertFalse(resultSign);
	}

	@Test
	public void calculateResultSign_negative_positive_negative() {
		Result result = new Result();
		result.setIsNumeratorNegative(true);
		result.setIsDenominatorNegative(false);
		boolean resultSign = result.calculateResultSign();
		assertTrue(resultSign);
	}

	@Test
	public void calculateResultSign_positive_negative_negative() {
		Result result = new Result();
		result.setIsNumeratorNegative(false);
		result.setIsDenominatorNegative(true);
		boolean resultSign = result.calculateResultSign();
		assertTrue(resultSign);
	}

}
