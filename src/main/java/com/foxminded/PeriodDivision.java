package com.foxminded;

public class PeriodDivision {

	public String divideDecimal(int numerator, int denominator) {
		Result result = getIntegerDivisionResult(numerator, denominator);
		performDecimalDivision(numerator, denominator, result);
		return createDivisionString(result);
	}


	private Result getIntegerDivisionResult(int numerator, int denominator){
		LongDivision longDivision = new LongDivision();
		return longDivision.longDivide(numerator, denominator);
	}
	
	private void performDecimalDivision(int numerator, int denominator, Result result) {
		int integerOperationQty = result.getOperationQuantity();
		StringBuilder currentNumerator = new StringBuilder(result.getCurrentNumeratorElement(integerOperationQty));
		if (!currentNumerator.toString().equals("0")) {
			result.initWithAbsoluteValues(numerator, denominator);
			int shift = result.getNumerator().length() - currentNumerator.toString().length();
			removeCurrentNumeratorAndQuotientSignFromResult(integerOperationQty, result);
			findDecimalQuotient(integerOperationQty, currentNumerator, shift, result);
			addSigns(result);
		}
	}
	
	private void removeCurrentNumeratorAndQuotientSignFromResult(int integerOperationQty, Result result) {
		result.removeCurrentNumeratorElement(integerOperationQty);
		result.removeCurrentNumeratorShift(integerOperationQty);
		if (result.getQuotient().charAt(0) == '-') {
			StringBuilder positiveQuotient = new StringBuilder(result.getQuotient().substring(1));
			result.setQuotient(positiveQuotient);
		}
	}

	private void findDecimalQuotient(int integerOperationQty, StringBuilder currentNumerator, int shift,
			Result result) {
		int decimalOperationQty = 0;
		result.addQuotient(".");

		while ((!currentNumerator.toString().equals("0")) && (decimalOperationQty < 10)) {
			findCurrentNumerator(currentNumerator, decimalOperationQty, shift, result);
			divide(currentNumerator, decimalOperationQty, shift, result);
			decimalOperationQty++;
			shift++;
		}

		if (decimalOperationQty >= 10) {
			decimalOperationQty = processDecimalPart(currentNumerator, result);
			result.setOperationQuantity(integerOperationQty + decimalOperationQty);
		} else {
			addRemainderToResult(currentNumerator, decimalOperationQty, shift, result);
		}
	}

	private void findCurrentNumerator(StringBuilder currentNumerator, int decimalOperationQty, int shift,
			Result result) {
		currentNumerator.append('0');
		while (Integer.parseInt(currentNumerator.toString()) < Integer.parseInt(result.getDenominator())) {
			addZeroToCurrentNumerator(currentNumerator, decimalOperationQty, result);
		}
		addCurrentNumeratorToResult(currentNumerator, decimalOperationQty, shift, result);
	}

	private void addZeroToCurrentNumerator(StringBuilder currentNumerator, int decimalOperationQty, Result result) {
		result.addZeroToQuotient();
		currentNumerator.append('0');
	}

	private void addCurrentNumeratorToResult(StringBuilder currentNumerator, int decimalOperationQty, int shift,
			Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQty;
		result.addCurrentNumerator(currentIteration, currentNumerator.toString());
		result.addCurrentNumeratorShift(currentIteration, shift);
	}

	private void divide(StringBuilder currentNumerator, int decimalOperationQty, int shift, Result result) {
		int currentDeductor = findCurrentDeductor(currentNumerator, result);
		int numeratorDeductorLengthDifference = currentNumerator.toString().length()
				- String.valueOf(currentDeductor).length();
		addCurrentDeductorToResult(currentDeductor, decimalOperationQty, shift, numeratorDeductorLengthDifference,
				result);
		replaceCurrentNumerator(currentNumerator, currentDeductor);
	}

	private int findCurrentDeductor(StringBuilder currentNumerator, Result result) {
		int quotient = Integer.parseInt(currentNumerator.toString()) / Integer.parseInt(result.getDenominator());
		result.addQuotient(quotient);
		return quotient * Integer.parseInt(result.getDenominator());
	}

	private void addCurrentDeductorToResult(int currentDeductor, int decimalOperationQty, int shift,
			int numeratorDeductorLengthDifference, Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQty;
		result.addСurrentDeductorValue(currentIteration, String.valueOf(currentDeductor));
		result.addСurrentDeductorShift(currentIteration, shift + numeratorDeductorLengthDifference);
	}

	private void replaceCurrentNumerator(StringBuilder currentNumerator, int currentDeductor) {
		int remainder = Integer.parseInt(currentNumerator.toString()) - currentDeductor;
		currentNumerator.delete(0, currentNumerator.length());
		currentNumerator.append(String.valueOf(remainder));
	}

	private void addRemainderToResult(StringBuilder currentNumerator, int decimalOperationQty, int shift,
			Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQty;
		String previousNumerator = result.getCurrentNumeratorElement(currentIteration - 1);
		int lengthDifference = previousNumerator.length() - currentNumerator.toString().length();
		result.addCurrentNumerator(currentIteration, currentNumerator.toString());
		result.addCurrentNumeratorShift(currentIteration, shift + lengthDifference - 1);
		result.setOperationQuantity(currentIteration);
	}

	private void addSigns(Result result) {
		if (result.calculateResultSign()) {
			result.setQuotient(result.getQuotient().insert(0, '-'));
		}
		if (result.getIsDenominatorNegative()) {
			StringBuilder oldDenominator = new StringBuilder(result.getDenominator());
			result.setDenominator(oldDenominator.insert(0, '-').toString());
		}
		if (result.getIsNumeratorNegative()) {
			StringBuilder oldNumerator = new StringBuilder(result.getNumerator());
			result.setDenominator(oldNumerator.insert(0, '-').toString());
		}
	}

	private int processDecimalPart(StringBuilder currentNumerator, Result result) {
		int decimalPartStart = result.getQuotient().indexOf(".");
		StringBuilder newQuotient = new StringBuilder(result.getQuotient().substring(0, decimalPartStart + 1));
		StringBuilder decimalPart = new StringBuilder(
				result.getQuotient().substring(decimalPartStart + 1, decimalPartStart + 11));
		String newDecimalPart = findPeriod(decimalPart);

		int digitsAfterPoint = findNonZeroDecimalDigitsQty(newDecimalPart);

		newQuotient.append(newDecimalPart);
		replaceQuotient(newQuotient, result);
		return digitsAfterPoint;
	}

	private String findPeriod(StringBuilder sb) {
		int counter = 0;
		String period = "";
		while (counter < 10) {
			String digitToCheck = sb.substring(counter, counter + 1);
			int nextOccurance = sb.indexOf(digitToCheck, counter + 1);
			if (nextOccurance > 0) {
				period = sb.substring(counter, nextOccurance);
				break;
			}
			counter++;
		}
		if (!period.equals("")) {
			sb.replace(counter, 10, "(" + period + ")");
		}
		return sb.toString();
	}

	private int findNonZeroDecimalDigitsQty(String newDecimalPart) {
		StringBuilder sb = new StringBuilder(newDecimalPart);
		for (int i = 0; i < sb.length(); i++) {
			if ((sb.charAt(i) == '(') || (sb.charAt(i) == ')') || (sb.charAt(i) == '0')) {
				sb.deleteCharAt(i);
			}
		}
		return sb.length();
	}

	private void replaceQuotient(StringBuilder newQuotient, Result result) {
		result.setQuotient(null);
		result.setQuotient(newQuotient);
	}

	private String createDivisionString(Result result) {
		ResultBuilder resultBuilder = new ResultBuilder();
		String periodDivisionResult = resultBuilder.createString(result);
		System.out.println(periodDivisionResult);
		return periodDivisionResult;
	}

}
