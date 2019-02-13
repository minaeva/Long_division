package com.foxminded;

public class Period {

	public String findPeriod(StringBuilder sb) {
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
}
