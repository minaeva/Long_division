package com.foxminded;

public class ResultBuilder {

	private StringBuilder resultBuilt;

	public ResultBuilder() {
		this.resultBuilt = new StringBuilder();
	}

	public String createString(Result result) {
		int lastOperationNumber = result.getOperationQuantity();
		String numerator = result.getNumerator();
		String denominator = result.getDenominator();
		boolean isNumeratorNegative = result.getIsNumeratorNegative();

		addFirstLine(numerator, denominator);
		addSecondLine(isNumeratorNegative, result, numerator);
		addThirdLine(isNumeratorNegative, result, numerator);
		addOtherLines(isNumeratorNegative, result, lastOperationNumber);
		drawStars();
		return resultBuilt.toString();
	}

	private void addFirstLine(String numerator, String denominator) {
		appendResult(numerator, " | ", denominator, "\n");
	}

	private void addSecondLine(boolean isNumeratorNegative, Result result, String numerator) {
		int spacesQuantity;
		if (isNumeratorNegative) {
			appendResult(" ");
		}
		appendResult(createLineOfSymbols(" ", result.getСurrentDeductorShiftElement(0)).toString(),
				result.getСurrentDeductorElement(0));

		if (isNumeratorNegative) {
			spacesQuantity = numerator.length() - result.getCurrentNumeratorElement(0).length() - 1;
		} else {
			spacesQuantity = numerator.length() - result.getCurrentNumeratorElement(0).length();
		}
		int dashesQuantity = findDenominatorQuotientBiggerLength(result);
		appendResult(createLineOfSymbols(" ", spacesQuantity).toString(), " |", createLineOfSymbols("-", dashesQuantity).toString(), "\n");
	}

	private int findDenominatorQuotientBiggerLength(Result result) {
		int denominatorLength = result.getDenominator().length();
		int quotientLength = result.getQuotient().length();
		return Math.max(denominatorLength, quotientLength) + 2;
	}

	private void addThirdLine(boolean isNumeratorNegative, Result result, String numerator) {
		String minusSpace = " ";
		int dashesQuantity;
		if (!isNumeratorNegative) {
			minusSpace = "";
			dashesQuantity = numerator.length();
		} else {
			dashesQuantity = numerator.length() - 1;
		}
		appendResult(minusSpace, createLineOfSymbols("-", dashesQuantity).toString(), " | ",
				result.getQuotient().toString(), "\n");
	}

	private void addOtherLines(boolean isNumeratorNegative, Result result, int operationsQuantity) {
		int i = 1;
		String minusSpace = " ";
		if (!isNumeratorNegative) {
			minusSpace = "";
		}

		while (i < operationsQuantity) {
			appendResult(minusSpace, createLineOfSymbols(" ", result.getCurrentNumeratorShiftElement(i)).toString(),
					result.getCurrentNumeratorElement(i), "\n");
			appendResult(minusSpace, createLineOfSymbols(" ", result.getСurrentDeductorShiftElement(i)).toString(),
					result.getСurrentDeductorElement(i), "\n");

			int shift = result.getCurrentNumeratorShiftElement(i);
			StringBuilder dashesQuantity = createLineOfSymbols("-", result.getCurrentNumeratorElement(i).length() + 1);
			appendResult(minusSpace, createLineOfSymbols(" ", shift).toString(), dashesQuantity.toString(), "\n");
			i++;
		}
		addLastLine(isNumeratorNegative, result, operationsQuantity);
	}

	private void addLastLine(boolean firstMinus, Result result, int operationsQuantity) {
		String minusSpace = " ";
		if (!firstMinus) {
			minusSpace = "";
		}
		StringBuilder spacesQuantity = createLineOfSymbols(" ", result.getCurrentNumeratorShiftElement(operationsQuantity));
		appendResult(minusSpace, spacesQuantity.toString(), result.getCurrentNumeratorElement(operationsQuantity), "\n");
	}

	private void appendResult(String... stringToAdd) {
		for (int i = 0; i < stringToAdd.length; i++) {
			resultBuilt.append(stringToAdd[i]);
		}
	}

	private void drawStars() {
		appendResult("\n*********************\n\n");
	}

	private StringBuilder createLineOfSymbols(String s, int times) {
		StringBuilder line = new StringBuilder();
		while (times > 0) {
			line.append(s);
			times--;
		}
		return line;
	}

}
