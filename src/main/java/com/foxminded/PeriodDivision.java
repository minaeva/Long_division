package com.foxminded;

public class PeriodDivision {

	private final static int DIGITS_AFTER_POINT = 10;

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
			removeLatestNumeratorAndQuotientSignFromResult(integerOperationQuantity, result);
			findDecimalQuotient(integerOperationQuantity, currentNumerator, result);
			addSigns(result);
		}
	}

	private void removeLatestNumeratorAndQuotientSignFromResult(int integerOperationQuantity, Result result) {
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
		result.addQuotient(".");
		int decimalOperationQuantity = 0;
		int currentShift = firstOperation(currentNumerator, decimalOperationQuantity, result);

		decimalOperationQuantity++;
		while ((!currentNumerator.toString().equals("0")) && (decimalOperationQuantity < DIGITS_AFTER_POINT)) {
			updateCurrentNumerator(currentNumerator, decimalOperationQuantity, currentShift, result);
			int numeratorLength = currentNumerator.length();
			int currentDeductor = divide(currentNumerator, decimalOperationQuantity, currentShift, result);
			int remainderLength = findNextNumerator(currentNumerator, currentDeductor);
			currentShift += numeratorLength - remainderLength;
			decimalOperationQuantity++;
		}

		if (decimalOperationQuantity >= DIGITS_AFTER_POINT) {
			processDecimalPart(currentNumerator, integerOperationQuantity, decimalOperationQuantity, result);
		} else {
			finishDivisionWithoutPeriod(currentNumerator, decimalOperationQuantity, result);
		}
	}

	private int firstOperation(StringBuilder currentNumerator, int decimalOperationQuantity, Result result) {
		int firstShift = result.getNumerator().length() - currentNumerator.toString().length();
		updateCurrentNumerator(currentNumerator, decimalOperationQuantity, firstShift, result);
		int numeratorLength = currentNumerator.length();
		int currentDeductor = divide(currentNumerator, decimalOperationQuantity, firstShift, result);
		int remainderLength = findNextNumerator(currentNumerator, currentDeductor);
		return firstShift + numeratorLength - remainderLength;
	}

	private void updateCurrentNumerator(StringBuilder currentNumerator, int decimalOperationQuantity, int shift,
			Result result) {
		currentNumerator.append('0');
		while (Integer.parseInt(currentNumerator.toString()) < Integer.parseInt(result.getDenominator())) {
			result.addZeroToQuotient();
			currentNumerator.append('0');
		}
		addCurrentNumeratorToResult(currentNumerator, decimalOperationQuantity, shift, result);
	}

	private void addCurrentNumeratorToResult(StringBuilder currentNumerator, int decimalOperationQuantity, int shift,
			Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQuantity;
		result.addCurrentNumerator(currentIteration, currentNumerator.toString());
		result.addCurrentNumeratorShift(currentIteration, shift);
	}

	private int divide(StringBuilder currentNumerator, int decimalOperationQuantity, int shift, Result result) {
		int currentDeductor = findCurrentDeductor(currentNumerator, result);
		int numeratorDeductorLengthDifference = currentNumerator.toString().length()
				- String.valueOf(currentDeductor).length();
		addCurrentDeductorToResult(currentDeductor, decimalOperationQuantity, shift, numeratorDeductorLengthDifference,
				result);
		return currentDeductor;
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

	private int findNextNumerator(StringBuilder currentNumerator, int currentDeductor) {
		int remainder = Integer.parseInt(currentNumerator.toString()) - currentDeductor;
		currentNumerator.delete(0, currentNumerator.length());
		currentNumerator.append(remainder);
		return String.valueOf(remainder).length();
	}

	private void addSigns(Result result) {
		addQuotientSign(result);
		addNumeratorSign(result);
		addDenominatorSign(result);
	}

	private void addQuotientSign(Result result) {
		if (result.calculateResultSign()) {
			result.setQuotient(result.getQuotient().insert(0, '-'));
		}
	}

	private void addDenominatorSign(Result result) {
		if (result.getIsDenominatorNegative()) {
			StringBuilder oldDenominator = new StringBuilder(result.getDenominator());
			result.setDenominator(oldDenominator.insert(0, '-').toString());
		}
	}

	private void addNumeratorSign(Result result) {
		if (result.getIsNumeratorNegative()) {
			StringBuilder oldNumerator = new StringBuilder(result.getNumerator());
			result.setNumerator(oldNumerator.insert(0, '-').toString());
		}
	}

	private int processDecimalPart(StringBuilder currentNumerator, int integerOperationQuantity,
			int decimalOperationQuantity, Result result) {
		int nonZeroFractionalDigits = -1;
		for (int first = integerOperationQuantity; first < integerOperationQuantity + DIGITS_AFTER_POINT; first++) {
			for (int second = first + 1; second < integerOperationQuantity + DIGITS_AFTER_POINT; second++) {
				if (result.getCurrentNumeratorElement(first).equals(result.getCurrentNumeratorElement(second))) {
					nonZeroFractionalDigits = finishDivisionPeriodFound(first, second, result);
					result.setOperationQuantity(integerOperationQuantity + nonZeroFractionalDigits);
					return 1;
				}
			}
		}
		if (nonZeroFractionalDigits == -1) {
			finishDivisionNoPeriodFound(currentNumerator, integerOperationQuantity, decimalOperationQuantity, result);
		}
		return 1;
	}

	private int finishDivisionPeriodFound(int first, int second, Result result) {
		int periodLength = findNonZeroDigits(first, second, result);
		StringBuilder newQuotient = new StringBuilder(result.getQuotient().substring(0, first + 1));
		String newDecimal = "(" + result.getQuotient().substring(first + 1, first + periodLength) + ")";
		newQuotient.append(newDecimal);
		result.setQuotient(newQuotient);
		return findNonZeroDecimalWithPeriod(newQuotient.toString());
	}

	private void finishDivisionNoPeriodFound(StringBuilder currentNumerator, int integerOperationQuantity,
			int decimalOperationQuantity, Result result) {
		addRemainder(currentNumerator, decimalOperationQuantity, result);
		String oldInteger = result.getQuotient().substring(0, integerOperationQuantity + 1);
		String newDecimal = result.getQuotient().substring(integerOperationQuantity + 1,
				integerOperationQuantity + DIGITS_AFTER_POINT + 1);
		result.setQuotient(oldInteger + newDecimal);
		int nonZeroFractionalDigits = findNonZeroDecimalNoPeriod(newDecimal);
		result.setOperationQuantity(integerOperationQuantity + nonZeroFractionalDigits);
	}

	private void finishDivisionWithoutPeriod(StringBuilder currentNumerator, int decimalOperationQuantity,
			Result result) {
		addRemainder(currentNumerator, decimalOperationQuantity, result);
		int currentIteration = result.getOperationQuantity() + decimalOperationQuantity;
		result.setOperationQuantity(currentIteration);
	}
	
	private int findNonZeroDecimalWithPeriod(String newDecimalPart) {
		int integerPart = newDecimalPart.indexOf('.');
		String decimalString = newDecimalPart.substring(integerPart + 1);
		int toExclude = 0;
		for (int i = 0; i < decimalString.length(); i++) {
			if ((decimalString.charAt(i) == '0') || (decimalString.charAt(i) == '(')
					|| (decimalString.charAt(i) == ')')) {
				toExclude++;
			}
		}
		return decimalString.length() - toExclude;
	}

	private int findNonZeroDigits(int first, int second, Result result) {
		int quantity = 0;
		int i = 0;
		while (quantity <= (second - first)) {
			if (result.getQuotient().charAt(first + i) != '0') {
				quantity++;
			}
			i++;
		}
		return i;
	}

	private void addRemainder(StringBuilder currentNumerator, int decimalOperationQuantity, Result result) {
		int currentIteration = result.getOperationQuantity() + decimalOperationQuantity;
		String previousNumerator = result.getCurrentNumeratorElement(currentIteration - 1);
		int previousShift = result.getCurrentNumeratorShiftElement(currentIteration - 1);
		int lengthDifference = previousNumerator.length() - currentNumerator.toString().length();
		result.addCurrentNumerator(currentIteration, currentNumerator.toString());
		result.addCurrentNumeratorShift(currentIteration, previousShift + lengthDifference);
	}

	private int findNonZeroDecimalNoPeriod(String newDecimalPart) {
		int zeroesQuantity = 0;
		for (int i = 0; i < newDecimalPart.length(); i++) {
			if ((newDecimalPart.charAt(i) == '0')) {
				zeroesQuantity++;
			}
		}
		return newDecimalPart.length() - zeroesQuantity;
	}

	private String createDivisionString(Result result) {
		ResultBuilder resultBuilder = new ResultBuilder();
		String periodDivisionResult = resultBuilder.createString(result);
		System.out.println(periodDivisionResult);
		return periodDivisionResult;
	}

}
