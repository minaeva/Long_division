package com.foxminded;

public class LongDivision {

	private String numerator;
	private String denominator;
	private String currentNumerator;
	private String nextNumerator;
	private String remainder;
	private int currentNumeratorLength;
	private int remainderLength;
	private int currentDeductor;
	private int quotient;
	private int iteration;
	private int pointer = -1;

	public Result longDivide(int toDivide, int divider) {
		Result result = new Result();
		validateInput(divider);
		addInputToResult(toDivide, divider, result);
		while (pointer < (this.numerator.length() - 1)) {
			if (findCurrentNumerator(result)) {
				divide(result);
				findNextNumerator();
			}
		}
		finishDivision(result);
//		return createDivisionString(result);
		return result;
	}

	private void validateInput(int denominator) throws ArithmeticException {
		if (denominator == 0) {
			throw new ArithmeticException("Zero denominator is not acceptable");
		}
	}

	private void addInputToResult(int numerator, int denominator, Result result) {
		result.init(numerator, denominator);
		init(numerator, denominator, result);
	}

	private void init(int numerator, int denominator, Result result) {
		this.numerator = String.valueOf(Math.abs(numerator));
		this.nextNumerator = String.valueOf(Math.abs(numerator));
		this.denominator = String.valueOf(Math.abs(denominator));
	}

	private boolean findCurrentNumerator(Result result) {
		currentNumeratorLength = remainderLength;
		currentNumerator = nextNumerator.substring(0, currentNumeratorLength + 1);
		currentNumeratorLength++;
		pointer++;

		while (Integer.parseInt(currentNumerator) < Integer.parseInt(denominator)) {
			if (isLastSymbol()) {
				result.addZeroToQuotient();
				pointer++;
				return false;
			} else {
				addOneDigitToCurrentNumerator(result);
			}
		}
		removeCurrentNumeratorFirstZero();
		addCurrentNumeratorToResult(result);
		return true;
	}

	private boolean isLastSymbol() {
		return pointer == numerator.length() - 1;
	}

	private void addOneDigitToCurrentNumerator(Result result) {
		if (iteration > 0) {
			result.addZeroToQuotient();
		}
		currentNumerator = nextNumerator.substring(0, currentNumeratorLength + 1);
		currentNumeratorLength++;
		pointer++;
	}

	private void removeCurrentNumeratorFirstZero() {
		if ((currentNumerator.charAt(0) == '0') && (!isLastSymbol())) {
			currentNumerator = currentNumerator.substring(1);
			if (remainderLength > 0) {
				remainderLength--;
			}
		}
	}

	private void addCurrentNumeratorToResult(Result result) {
		result.addCurrentNumerator(iteration, currentNumerator);
		result.addCurrentNumeratorShift(iteration, (pointer + 1 - currentNumerator.length()));
	}

	private void divide(Result result) {
		quotient = Integer.parseInt(currentNumerator) / Integer.parseInt(denominator);
		result.addQuotient(quotient);
		currentDeductor = quotient * Integer.parseInt(denominator);
		addCurrentDeductorToResult(result);
	}

	private void addCurrentDeductorToResult(Result result) {
		result.addСurrentDeductorValue(iteration, String.valueOf(currentDeductor));
		result.addСurrentDeductorShift(iteration, (pointer + 1 - String.valueOf(currentDeductor).length()));
	}

	private void findNextNumerator() {
		remainder = String.valueOf(Integer.parseInt(currentNumerator) - currentDeductor);
		remainderLength = remainder.length();
		nextNumerator = remainder + numerator.substring(pointer + 1, numerator.length());
		removeNextNumeratorFirstZero();
		iteration++;
	}

	private void removeNextNumeratorFirstZero() {
		if ((nextNumerator.charAt(0) == '0') && (!isLastSymbol())) {
			nextNumerator = nextNumerator.substring(1);
			remainderLength--;
		}
	}

	private void finishDivision(Result result) {
		processRemainder(result);
		processQuotient(result);
	}

	private void processRemainder(Result result) {
		nextNumerator = String.valueOf(Integer.parseInt(nextNumerator));
		processDenominatorBiggerThanNumerator(result);
		addRemainderToResult(result);
	}

	private void processDenominatorBiggerThanNumerator(Result result) {
		if (iteration == 0) {
			result.addCurrentNumerator(iteration, numerator);
			result.addCurrentNumeratorShift(iteration, 0);
			result.addСurrentDeductorValue(iteration, "0");
			int numeratorLength = result.getNumerator().length();
			result.addСurrentDeductorShift(iteration, numeratorLength - 1);
			iteration++;
		}
	}

	private void addRemainderToResult(Result result) {
		result.addCurrentNumerator(iteration, nextNumerator);
		result.addCurrentNumeratorShift(iteration, (numerator.length() - nextNumerator.length()));
		result.setOperationQuantity(iteration);
	}

	private void processQuotient(Result result) {
		if (result.calculateResultSign() && Integer.parseInt(result.getQuotient().toString()) != 0) {
			result.setQuotient(result.getQuotient().insert(0, '-'));
		}
	}

	private String createDivisionString(Result result) {
		ResultBuilder resultBuilder = new ResultBuilder();
		String longDivisionResult = resultBuilder.createString(result);
		System.out.println(longDivisionResult);
		return longDivisionResult;
	}

}