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
		boolean firstMinus = r.getIsNumeratorNegative();

		firstLine(numerator, denominator);
		secondLine(firstMinus, r, numerator);
		thirdLine(firstMinus, r, numerator);
		otherLines(firstMinus, r, numerator, lastOperationNumber);
		drawStars();
		return result.toString();
	}

	private void firstLine(String numerator, String denominator) {
		resultAppend(numerator, " | ", denominator, "\n");
	}

	private void secondLine(boolean firstMinus, Result r, String numerator) {
		int spaces = 0;
		if (firstMinus) {
			resultAppend(" ");
		}
		resultAppend(addXtimes(" ", r.getСurrentDeductorShiftElement(0)).toString(), r.getСurrentDeductorElement(0));

		if (firstMinus) {
			spaces = numerator.length() - r.getCurrentNumeratorElement(0).length() - 1;
		} else {
			spaces = numerator.length() - r.getCurrentNumeratorElement(0).length();
		}
		int dashes = maxLengthOfDenominatorAndQuotient(r);
		resultAppend(addXtimes(" ", spaces).toString(), " |", addXtimes("-", dashes).toString(), "\n");
	}

	private int maxLengthOfDenominatorAndQuotient(Result r) {
		int denominatorLength = String.valueOf(r.getDenominator()).length();
		int quotientLength = r.getQuotient().toString().length();
		return (denominatorLength > quotientLength)
				? denominatorLength + 2
				: quotientLength + 2;
	}
	
	
	private void thirdLine(boolean firstMinus, Result r, String numerator) {
		String minusSpace = " ";
		int underscores = 0;
		if (!firstMinus) {
			minusSpace = "";
			underscores = numerator.length();
		} else {
			underscores = numerator.length() - 1;
		}
		resultAppend(minusSpace, addXtimes("-", underscores).toString(), " | ", r.getQuotient().toString(), "\n");
	}

	private void otherLines(boolean firstMinus, Result r, String numerator, int operationsQty) {
		int i = 1;
		String minusSpace = " ";

		while (i < operationsQty) {
			StringBuilder underscores;
			if (!firstMinus) {
				minusSpace = "";
			}
			resultAppend(minusSpace, addXtimes(" ", r.getCurrentNumeratorShiftElement(i)).toString(),
					r.getCurrentNumeratorElement(i), "\n");

			if (!firstMinus) {
				minusSpace = "";
			}
			resultAppend(minusSpace, addXtimes(" ", r.getСurrentDeductorShiftElement(i)).toString(),
					r.getСurrentDeductorElement(i), "\n");

			if (!firstMinus) {
				minusSpace = "";
			}
			int shift = r.getCurrentNumeratorShiftElement(i);
			underscores = addXtimes("-", r.getCurrentNumeratorElement(i).toString().length() + 1);
			resultAppend(minusSpace, addXtimes(" ", shift).toString(), underscores.toString(), "\n");
			i++;
		}
		lastLine(firstMinus, r, operationsQty);
	}

	private void lastLine(boolean firstMinus, Result r, int operationsQty) {
		String minusSpace = " ";
		StringBuilder spaces;

		if (!firstMinus) {
			minusSpace = "";
		}
		spaces = addXtimes(" ", r.getCurrentNumeratorShiftElement(operationsQty));
		resultAppend(minusSpace, spaces.toString(), r.getCurrentNumeratorElement(operationsQty), "\n");
	}

	private void resultAppend(String... s) {
		for (int i = 0; i < s.length; i++) {
			result.append(s[i]);
		}
	}

	public void drawStars() {
		resultAppend("\n*********************\n\n");
	}

	private StringBuilder addXtimes(String s, int times) {
		StringBuilder result = new StringBuilder();
		while (times > 0) {
			result.append(s);
			times--;
		}
		return result;
	}

}
