package com.foxminded;

import java.util.ArrayList;
import java.util.List;

public class Result {

	private String numerator;
	private String denominator;
	private List<String> currentNumeratorList = new ArrayList<>();
	private List<Integer> currentNumeratorShiftList = new ArrayList<>();
	private List<String> currentDeductorList = new ArrayList<>();
	private List<Integer> currentDeductorShiftList = new ArrayList<>();
	private StringBuilder quotient = new StringBuilder();
	private boolean isNumeratorNegative;
	private boolean isDenominatorNegative;
	private int iterationQuantity;

	public void init(int numerator, int denominator) {
		setNumerator(String.valueOf(numerator));
		setDenominator(String.valueOf(denominator));
		setIsNumeratorNegative(numerator < 0);
		setIsDenominatorNegative(denominator < 0);
	}
	
	public void initWithAbsoluteValues(int numerator, int denominator) {
		setNumerator(String.valueOf(Math.abs(numerator)));
		setDenominator(String.valueOf(Math.abs(denominator)));
	}
	
	public boolean calculateResultSign() {
		return isNumeratorNegative ^ isDenominatorNegative;
	}

	public Result addCurrentNumerator(int index, String value) {
		currentNumeratorList.add(index, value);
		return this;
	}

	public Result addCurrentNumeratorShift(int index, int value) {
		currentNumeratorShiftList.add(index, value);
		return this;
	}

	public Result addСurrentDeductorValue(int index, String value) {
		currentDeductorList.add(index, value);
		return this;
	}

	public Result addСurrentDeductorShift(int index, int value) {
		currentDeductorShiftList.add(index, value);
		return this;
	}

	public Result addQuotient(String value) {
		quotient.append(value);
		return this;
	}

	public Result addQuotient(int value) {
		quotient.append(String.valueOf(value));
		return this;
	}

	public Result addZeroToQuotient() {
		quotient.append("0");
		return this;
	}

	public void setNumerator(String numerator) {
		this.numerator = numerator;
	}

	public void setQuotient(StringBuilder quotient) {
			this.quotient = quotient;
	}

	public void setDenominator(String denominator) {
		this.denominator = denominator;
	}

	public void setIsNumeratorNegative(boolean firstNegative) {
		this.isNumeratorNegative = firstNegative;
	}

	public void setIsDenominatorNegative(boolean secondNegative) {
		this.isDenominatorNegative = secondNegative;
	}

	public void setOperationQuantity(int operationsQuantity) {
		this.iterationQuantity = operationsQuantity;
	}

	public String getCurrentNumeratorElement(int index) {
		return currentNumeratorList.get(index);
	}

	public Integer getCurrentNumeratorShiftElement(int index) {
		return currentNumeratorShiftList.get(index);
	}

	public String getСurrentDeductorElement(int index) {
		return currentDeductorList.get(index);
	}

	public Integer getСurrentDeductorShiftElement(int index) {
		return currentDeductorShiftList.get(index);
	}

	public StringBuilder getQuotient() {
		return quotient;
	}

	public String getNumerator() {
		return numerator;
	}

	public String getDenominator() {
		return denominator;
	}

	public boolean getIsNumeratorNegative() {
		return isNumeratorNegative;
	}

	public boolean getIsDenominatorNegative() {
		return isDenominatorNegative;
	}

	public int getOperationQuantity() {
		return iterationQuantity;
	}
	
	public void removeCurrentNumeratorElement(int index) {
		currentNumeratorList.remove(index);
	}

	public void removeCurrentNumeratorShift(int index) {
		currentNumeratorShiftList.remove(index);
	}

}
