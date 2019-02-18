package com.foxminded;

public class ResultBuilder {

	private StringBuilder divisionResult;

	public ResultBuilder() {
		this.divisionResult = new StringBuilder();
	}

	public String createString(Result result) {
		int lastOperationNumber = result.getOperationQuantity();
		String numerator = result.getNumerator();
		String denominator = result.getDenominator();

		addFirstLine(numerator, denominator);
		addSecondLine(result, numerator);
		addThirdLine(result, numerator);
		addRestLines(result, lastOperationNumber);
		drawStars();
		return divisionResult.toString();
	}

	private void addFirstLine(String numerator, String denominator) {
		appendToDivisionResult(numerator, " | ", denominator, "\n");
	}

	private void addSecondLine(Result result, String numerator) {
		boolean isNumeratorNegative = result.getIsNumeratorNegative();
		if (isNumeratorNegative) {
			appendToDivisionResult(" ");
		}
		appendToDivisionResult(createLineOfSymbols(" ", result.getСurrentDeductorShiftElement(0)).toString(),
				result.getСurrentDeductorElement(0));

		int spacesQuantity;
		int currentNumeratorLength = result.getCurrentNumeratorElement(0).length();
		if (isNumeratorNegative) {
			spacesQuantity = numerator.length() - currentNumeratorLength - 1;
		} else {
			spacesQuantity = numerator.length() - currentNumeratorLength;
		}
		int dashesQuantity = findDenominatorQuotientBiggerLength(result);
		appendToDivisionResult(createLineOfSymbols(" ", spacesQuantity).toString(), " |", createLineOfSymbols("-", dashesQuantity).toString(), "\n");
	}

	private int findDenominatorQuotientBiggerLength(Result result) {
		int denominatorLength = result.getDenominator().length();
		int quotientLength = result.getQuotient().length();
		return Math.max(denominatorLength, quotientLength) + 2;
	}

	private void addThirdLine(Result result, String numerator) {
		String minusSpace = " ";
		int dashesQuantity;
		boolean isNumeratorNegative = result.getIsNumeratorNegative();
		if (!isNumeratorNegative) {
			minusSpace = "";
			dashesQuantity = numerator.length();
		} else {
			dashesQuantity = numerator.length() - 1;
		}
		appendToDivisionResult(minusSpace, createLineOfSymbols("-", dashesQuantity).toString(), " | ",
				result.getQuotient().toString(), "\n");
	}

	private void addRestLines(Result result, int operationsQuantity) {
		String minusSpace = " ";
		boolean isNumeratorNegative = result.getIsNumeratorNegative();
		if (!isNumeratorNegative) {
			minusSpace = "";
		}

		int i = 1;
		while (i < operationsQuantity) {
			appendToDivisionResult(minusSpace, createLineOfSymbols(" ", result.getCurrentNumeratorShiftElement(i)).toString(),
					result.getCurrentNumeratorElement(i), "\n");
			appendToDivisionResult(minusSpace, createLineOfSymbols(" ", result.getСurrentDeductorShiftElement(i)).toString(),
					result.getСurrentDeductorElement(i), "\n");

			int shift = result.getCurrentNumeratorShiftElement(i);
			StringBuilder dashesQuantity = createLineOfSymbols("-", result.getCurrentNumeratorElement(i).length() + 1);
			appendToDivisionResult(minusSpace, createLineOfSymbols(" ", shift).toString(), dashesQuantity.toString(), "\n");
			i++;
		}
		addLastLine(result, operationsQuantity);
	}

	private void addLastLine(Result result, int operationsQuantity) {
		String minusSpace = " ";
		boolean isNumeratorNegative = result.getIsNumeratorNegative();
		if (!isNumeratorNegative) {
			minusSpace = "";
		}
		StringBuilder spacesQuantity = createLineOfSymbols(" ", result.getCurrentNumeratorShiftElement(operationsQuantity));
		appendToDivisionResult(minusSpace, spacesQuantity.toString(), result.getCurrentNumeratorElement(operationsQuantity), "\n");
	}

	private void appendToDivisionResult(String... stringToAdd) {
		for (String s : stringToAdd) {
			divisionResult.append(s);
		}
	}

	private void drawStars() {
		appendToDivisionResult("\n*********************\n\n");
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
