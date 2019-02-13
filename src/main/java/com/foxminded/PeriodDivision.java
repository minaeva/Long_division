package com.foxminded;

public class PeriodDivision {

	public String divideDecimal(int numerator, int denominator) {
		Result result = getIntegerDivisionResult(numerator, denominator);
		performDecimalDivision(numerator, denominator, result);
		return createDivisionString(result);
	}

	private Result getIntegerDivisionResult(int numerator, int denominator) {
		LongDivision longDivision = new LongDivision();
		return longDivision.longDivide(numerator, denominator);
	}

	private void performDecimalDivision(int numerator, int denominator, Result result) {
		int integerOperationQuantity = result.getOperationQuantity();
		StringBuilder currentNumerator = new StringBuilder(result.getCurrentNumeratorElement(integerOperationQuantity));
		if (!currentNumerator.toString().equals("0")) {
			result.initWithAbsoluteValues(numerator, denominator);
			removeCurrentNumeratorAndQuotientSignFromResult(integerOperationQuantity, result);
			findDecimalQuotient(integerOperationQuantity, currentNumerator, result);
			addSigns(result);
		}
	}

	private void removeCurrentNumeratorAndQuotientSignFromResult(int integerOperationQuantity, Result result) {
		removeLatestNumerator(integerOperationQuantity, result);
		removeNegativeSign(result);
	}

	private void removeLatestNumerator(int integerOperationQuantity, Result result) {
		result.removeCurrentNumeratorElement(integerOperationQuantity);
		result.removeCurrentNumeratorShift(integerOperationQuantity);
	}
	
	private void removeNegativeSign(Result result) {
		if (result.getQuotient().charAt(0) == '-') {
			StringBuilder positiveQuotient = new StringBuilder(result.getQuotient().substring(1));
			result.setQuotient(positiveQuotient);
		}
	}
	
	private void findDecimalQuotient(int integerOperationQuantity, StringBuilder currentNumerator, Result result) {
		int decimalOperationQuantity = 0;
		result.addQuotient(".");
		int shift = result.getNumerator().length() - currentNumerator.toString().length();

		while ((!currentNumerator.toString().equals("0")) && (decimalOperationQuantity < 10)) {
			findCurrentNumerator(currentNumerator, decimalOperationQuantity, shift, result);
			divide(currentNumerator, decimalOperationQuantity, shift, result);
			decimalOperationQuantity++;
			shift++;
		}

		if (decimalOperationQuantity >= 10) {
			decimalOperationQuantity = processDecimalPart(result);
			result.setOperationQuantity(integerOperationQuantity + decimalOperationQuantity);
		} else {
			addRemainderToResult(currentNumerator, decimalOperationQuantity, shift, result);
		}
	}

	private void findCurrentNumerator(StringBuilder currentNumerator, int decimalOperationQuantity, int shift,
			Result result) {
		currentNumerator.append('0');
		while (Integer.parseInt(currentNumerator.toString()) < Integer.parseInt(result.getDenominator())) {
			addZeroToCurrentNumerator(currentNumerator, result);
		}
		addCurrentNumeratorToResult(currentNumerator, decimalOperationQuantity, shift, result);
	}

	private void addZeroToCurrentNumerator(StringBuilder currentNumerator, Result result) {
		result.addZeroToQuotient();
		currentNumerator.append('0');
	}

	private void addCurrentNumeratorToResult(StringBuilder currentNumerator, int decimalOperationQuantity, int shift,
			Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQuantity;
		result.addCurrentNumerator(currentIteration, currentNumerator.toString());
		result.addCurrentNumeratorShift(currentIteration, shift);
	}

	private void divide(StringBuilder currentNumerator, int decimalOperationQuantity, int shift, Result result) {
		int currentDeductor = findCurrentDeductor(currentNumerator, result);
		int numeratorDeductorLengthDifference = currentNumerator.toString().length()
				- String.valueOf(currentDeductor).length();
		addCurrentDeductorToResult(currentDeductor, decimalOperationQuantity, shift, numeratorDeductorLengthDifference,
				result);
		replaceCurrentNumerator(currentNumerator, currentDeductor);
	}

	private int findCurrentDeductor(StringBuilder currentNumerator, Result result) {
		int quotient = Integer.parseInt(currentNumerator.toString()) / Integer.parseInt(result.getDenominator());
		result.addQuotient(quotient);
		return quotient * Integer.parseInt(result.getDenominator());
	}

	private void addCurrentDeductorToResult(int currentDeductor, int decimalOperationQuantity, int shift,
			int numeratorDeductorLengthDifference, Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQuantity;
		result.addСurrentDeductorValue(currentIteration, String.valueOf(currentDeductor));
		result.addСurrentDeductorShift(currentIteration, shift + numeratorDeductorLengthDifference);
	}

	private void replaceCurrentNumerator(StringBuilder currentNumerator, int currentDeductor) {
		int remainder = Integer.parseInt(currentNumerator.toString()) - currentDeductor;
		currentNumerator.delete(0, currentNumerator.length());
		currentNumerator.append(remainder);
	}

	private void addRemainderToResult(StringBuilder currentNumerator, int decimalOperationQuantity, int shift,
			Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQuantity;
		String previousNumerator = result.getCurrentNumeratorElement(currentIteration - 1);
		int lengthDifference = previousNumerator.length() - currentNumerator.toString().length();
		result.addCurrentNumerator(currentIteration, currentNumerator.toString());
		result.addCurrentNumeratorShift(currentIteration, shift + lengthDifference - 1);
		result.setOperationQuantity(currentIteration);
	}

	private void addSigns(Result result) {
		addQuotientSign(result);
		addNumeratorSign(result);
		addDenumeratorSign(result);
	}
	
	private void addQuotientSign(Result result) {
		if (result.calculateResultSign()) {
			result.setQuotient(result.getQuotient().insert(0, '-'));
		}
	}

	private void addNumeratorSign(Result result) {
		if (result.getIsDenominatorNegative()) {
			StringBuilder oldDenominator = new StringBuilder(result.getDenominator());
			result.setDenominator(oldDenominator.insert(0, '-').toString());
		}
	}

	private void addDenumeratorSign(Result result) {
		if (result.getIsNumeratorNegative()) {
			StringBuilder oldNumerator = new StringBuilder(result.getNumerator());
			result.setDenominator(oldNumerator.insert(0, '-').toString());
		}
	}

	private int processDecimalPart(Result result) {
		int decimalPartStart = result.getQuotient().indexOf(".");
		StringBuilder newQuotient = new StringBuilder(result.getQuotient().substring(0, decimalPartStart + 1));
		StringBuilder decimalPart = new StringBuilder(
				result.getQuotient().substring(decimalPartStart + 1, decimalPartStart + 11));
		String newDecimalPart = findPeriod(decimalPart);

		int digitsAfterPoint = findNonZeroDecimalDigitsQuantity(newDecimalPart);

		newQuotient.append(newDecimalPart);
		result.setQuotient(newQuotient);
		return digitsAfterPoint;
	}

	private String findPeriod(StringBuilder decimalPart) {
		int counter = 0;
		String period = "";
		while (counter < 10) {
			String digitToCheck = decimalPart.substring(counter, counter + 1);
			int nextOccurance = decimalPart.indexOf(digitToCheck, counter + 1);
			if (nextOccurance > 0) {
				period = decimalPart.substring(counter, nextOccurance);
				break;
			}
			counter++;
		}
		if (!period.equals("")) {
			decimalPart.replace(counter, 10, "(" + period + ")");
		}
		return decimalPart.toString();
	}

	private int findNonZeroDecimalDigitsQuantity(String newDecimalPart) {
		StringBuilder sb = new StringBuilder(newDecimalPart);
		for (int i = 0; i < sb.length(); i++) {
			if ((sb.charAt(i) == '(') || (sb.charAt(i) == ')') || (sb.charAt(i) == '0')) {
				sb.deleteCharAt(i);
			}
		}
		return sb.length();
	}

	private String createDivisionString(Result result) {
		ResultBuilder resultBuilder = new ResultBuilder();
		String periodDivisionResult = resultBuilder.createString(result);
		System.out.println(periodDivisionResult);
		return periodDivisionResult;
	}

}
