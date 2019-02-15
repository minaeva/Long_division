package com.foxminded;

public class ResultBuilder {

	private StringBuilder result;

	public ResultBuilder() {
		this.result = new StringBuilder();
	}

	public String createString(Result r) {
		int lastOperationNumber = r.getOperationQuantity();
		String numerator = r.getNumerator();
		String denominator = r.getDenominator();
		boolean isNumeratorNegative = r.getIsNumeratorNegative();

		addFirstLine(numerator, denominator);
		addSecondLine(isNumeratorNegative, r, numerator);
		addThirdLine(isNumeratorNegative, r, numerator);
		addOtherLines(isNumeratorNegative, r, lastOperationNumber);
		drawStars();
		return result.toString();
	}

	private void addFirstLine(String numerator, String denominator) {
		appendResult(numerator, " | ", denominator, "\n");
	}

	private void addSecondLine(boolean isNumeratorNegative, Result r, String numerator) {
		int spaces = 0;
		if (isNumeratorNegative) {
			appendResult(" ");
		}
		appendResult(createLineOfSymbols(" ", r.getСurrentDeductorShiftElement(0)).toString(), r.getСurrentDeductorElement(0));

		if (isNumeratorNegative) {
			spaces = numerator.length() - r.getCurrentNumeratorElement(0).length() - 1;
		} else {
			spaces = numerator.length() - r.getCurrentNumeratorElement(0).length();
		}
		int dashes = findDenominatorQuotientMaxLength(r);
		appendResult(createLineOfSymbols(" ", spaces).toString(), " |", createLineOfSymbols("-", dashes).toString(), "\n");
	}

	private int findDenominatorQuotientMaxLength(Result result) {
		int denominatorLength = result.getDenominator().length();
		int quotientLength = result.getQuotient().length();
		return Math.max(denominatorLength, quotientLength) + 2;
	}
	
	
	private void addThirdLine(boolean isNumeratorNegative, Result r, String numerator) {
		String minusSpace = " ";
		int underscores = 0;
		if (!isNumeratorNegative) {
			minusSpace = "";
			underscores = numerator.length();
		} else {
			underscores = numerator.length() - 1;
		}
		appendResult(minusSpace, createLineOfSymbols("-", underscores).toString(), " | ", r.getQuotient().toString(), "\n");
	}

	private void addOtherLines(boolean isNumeratorNegative, Result r, int operationsQty) {
		int i = 1;
		String minusSpace = " ";

		while (i < operationsQty) {
			if (!isNumeratorNegative) {
				minusSpace = "";
			}
			appendResult(minusSpace, createLineOfSymbols(" ", r.getCurrentNumeratorShiftElement(i)).toString(),
					r.getCurrentNumeratorElement(i), "\n");

			if (!isNumeratorNegative) {
				minusSpace = "";
			}
			appendResult(minusSpace, createLineOfSymbols(" ", r.getСurrentDeductorShiftElement(i)).toString(),
					r.getСurrentDeductorElement(i), "\n");

			if (!isNumeratorNegative) {
				minusSpace = "";
			}
			int shift = r.getCurrentNumeratorShiftElement(i);
			StringBuilder underscores = createLineOfSymbols("-", r.getCurrentNumeratorElement(i).toString().length() + 1);
			appendResult(minusSpace, createLineOfSymbols(" ", shift).toString(), underscores.toString(), "\n");
			i++;
		}
		addLastLine(isNumeratorNegative, r, operationsQty);
	}

	private void addLastLine(boolean firstMinus, Result r, int operationsQuantity) {
		String minusSpace = " ";
		StringBuilder spaces;

		if (!firstMinus) {
			minusSpace = "";
		}
		spaces = createLineOfSymbols(" ", r.getCurrentNumeratorShiftElement(operationsQuantity));
		appendResult(minusSpace, spaces.toString(), r.getCurrentNumeratorElement(operationsQuantity), "\n");
	}

	private void appendResult(String... stringToAdd) {
		for (int i = 0; i < stringToAdd.length; i++) {
			result.append(stringToAdd[i]);
		}
	}

	private void drawStars() {
		appendResult("\n*********************\n\n");
	}

	private StringBuilder createLineOfSymbols(String s, int times) {
		StringBuilder result = new StringBuilder();
		while (times > 0) {
			result.append(s);
			times--;
		}
		return result;
	}

}
