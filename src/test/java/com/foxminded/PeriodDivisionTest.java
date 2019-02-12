package com.foxminded;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PeriodDivisionTest {

	PeriodDivision periodDivision = new PeriodDivision();

	@Test
	public void divideDecimal_1_1_correct() {
		int toDivide = 1;
		int divider = 1;
		String e1 = "1 | 1\n";
		String e2 = "1 |---\n";
		String e3 = "- | 1\n";
		String e4 = "0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_20_4_correct() {
		int toDivide = 20;
		int divider = 4;
		String e1 = "20 | 4\n";
		String e2 = "20 |---\n";
		String e3 = "-- | 5\n";
		String e4 = " 0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_10_4_correct() {
		int toDivide = 10;
		int divider = 4;
		String e1 = "10 | 4\n";
		String e2 = " 8 |-----\n";
		String e3 = "-- | 2.5\n";
		String e4 = " 20\n";
		String e5 = " 20\n";
		String e6 = " ---\n";
		String e7 = "  0\n";
		String e8 = "\n";
		String e9 = "*********************\n";
		String e10 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9 + e10;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_0_1_correct() {
		int toDivide = 0;
		int divider = 1;
		String e1 = "0 | 1\n";
		String e2 = "0 |---\n";
		String e3 = "- | 0\n";
		String e4 = "0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_0_minus1_correct() {
		int toDivide = 0;
		int divider = -1;
		String e1 = "0 | -1\n";
		String e2 = "0 |----\n";
		String e3 = "- | 0\n";
		String e4 = "0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_minus1_1_correct() {
		int toDivide = -1;
		int divider = 1;
		String e1 = "-1 | 1\n";
		String e2 = " 1 |----\n";
		String e3 = " - | -1\n";
		String e4 = " 0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_1_minus1_correct() {
		int toDivide = 1;
		int divider = -1;
		String e1 = "1 | -1\n";
		String e2 = "1 |----\n";
		String e3 = "- | -1\n";
		String e4 = "0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test(expected = ArithmeticException.class)
	public void divideDecimal_1_0_returnsZero() {
		int toDivide = 1;
		int divider = 0;
		String actual = periodDivision.divideDecimal(toDivide, divider);
	}

	@Test
	public void divideDecimal_minus1_minus1_correct() {
		int toDivide = -1;
		int divider = -1;
		String e1 = "-1 | -1\n";
		String e2 = " 1 |----\n";
		String e3 = " - | 1\n";
		String e4 = " 0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_minus20992_minus1_correct() {
		int toDivide = -20992;
		int divider = -1;
		String e01 = "-20992 | -1\n";
		String e02 = " 2     |-------\n";
		String e03 = " ----- | 20992\n";
		String e04 = "   9\n";
		String e05 = "   9\n";
		String e06 = "   --\n";
		String e07 = "    9\n";
		String e08 = "    9\n";
		String e09 = "    --\n";
		String e10 = "     2\n";
		String e11 = "     2\n";
		String e12 = "     --\n";
		String e13 = "     0\n";
		String e14 = "\n";
		String e15 = "*********************\n";
		String e16 = "\n";
		String expected = e01 + e02 + e03 + e04 + e05 + e06 + e07 + e08 + e09 + e10 + e11 + e12 + e13 + e14 + e15 + e16;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_123456_123456_correct() {
		int toDivide = 123456;
		int divider = 123456;
		String e1 = "123456 | 123456\n";
		String e2 = "123456 |--------\n";
		String e3 = "------ | 1\n";
		String e4 = "     0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_123456_1_correct() {
		int toDivide = 123456;
		int divider = 1;
		String e01 = "123456 | 1\n";
		String e02 = "1      |--------\n";
		String e03 = "------ | 123456\n";
		String e04 = " 2\n";
		String e05 = " 2\n";
		String e06 = " --\n";
		String e07 = "  3\n";
		String e08 = "  3\n";
		String e09 = "  --\n";
		String e10 = "   4\n";
		String e11 = "   4\n";
		String e12 = "   --\n";
		String e13 = "    5\n";
		String e14 = "    5\n";
		String e15 = "    --\n";
		String e16 = "     6\n";
		String e17 = "     6\n";
		String e18 = "     --\n";
		String e19 = "     0\n";
		String e20 = "\n";
		String e21 = "*********************\n";
		String e22 = "\n";
		String expected = e01 + e02 + e03 + e04 + e05 + e06 + e07 + e08 + e09 + e10 + e11 + e12 + e13 + e14 + e15 + e16
				+ e17 + e18 + e19 + e20 + e21 + e22;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_10000_100_correct() {
		int toDivide = 10000;
		int divider = 100;
		String e1 = "10000 | 100\n";
		String e2 = "100   |-----\n";
		String e3 = "----- | 100\n";
		String e4 = "    0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_100_minus99_correct() {
		int toDivide = 100;
		int divider = -99;
		String e01 = "100 | -99\n";
		String e02 = " 99 |---------\n";
		String e03 = "--- | -1.(01)\n";
		String e04 = "  100\n";
		String e05 = "   99\n";
		String e06 = "  ----\n";
		String e07 = "   100\n";
		String e08 = "    99\n";
		String e09 = "   ----\n";
		String e10 = "    100\n";
		String e11 = "\n";
		String e12 = "*********************\n";
		String e13 = "\n";
		String expected = e01 + e02 + e03 + e04 + e05 + e06 + e07 + e08 + e09 + e10 + e11 + e12 + e13;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_12000000_100_correct() {
		int toDivide = 12000000;
		int divider = 100;
		String e01 = "12000000 | 100\n";
		String e02 = "100      |--------\n";
		String e03 = "-------- | 120000\n";
		String e04 = " 200\n";
		String e05 = " 200\n";
		String e06 = " ----\n";
		String e07 = "       0\n";
		String e08 = "\n";
		String e09 = "*********************\n";
		String e10 = "\n";
		String expected = e01 + e02 + e03 + e04 + e05 + e06 + e07 + e08 + e09 + e10;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_200100_100_correct() {
		int toDivide = 200100;
		int divider = 100;
		String e1 = "200100 | 100\n";
		String e2 = "200    |------\n";
		String e3 = "------ | 2001\n";
		String e4 = "   100\n";
		String e5 = "   100\n";
		String e6 = "   ----\n";
		String e7 = "     0\n";
		String e8 = "\n";
		String e9 = "*********************\n";
		String e10 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9 + e10;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_1200600_1200_correct() {
		int toDivide = 1200600;
		int divider = 1200;
		String e1 = "1200600 | 1200\n";
		String e2 = "1200    |--------\n";
		String e3 = "------- | 1000.5\n";
		String e4 = "    6000\n";
		String e5 = "    6000\n";
		String e6 = "    -----\n";
		String e7 = "       0\n";
		String e8 = "\n";
		String e9 = "*********************\n";
		String e10 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9 + e10;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_12060_70_correct() {
		int toDivide = 12060;
		int divider = 70;
		String e01 = "12060 | 70\n";
		String e02 = " 70   |--------------\n";
		String e03 = "----- | 172.(285714)\n";
		String e04 = " 506\n";
		String e05 = " 490\n";
		String e06 = " ----\n";
		String e07 = "  160\n";
		String e08 = "  140\n";
		String e09 = "  ----\n";
		String e10 = "   200\n";
		String e11 = "   140\n";
		String e12 = "   ----\n";
		String e13 = "    600\n";
		String e14 = "    560\n";
		String e15 = "    ----\n";
		String e16 = "     400\n";
		String e17 = "     350\n";
		String e18 = "     ----\n";
		String e19 = "      500\n";
		String e20 = "      490\n";
		String e21 = "      ----\n";
		String e22 = "       100\n";
		String e23 = "        70\n";
		String e24 = "       ----\n";
		String e25 = "        300\n";
		String e26 = "        280\n";
		String e27 = "        ----\n";
		String e28 = "         200\n";
		String e29 = "\n";
		String e30 = "*********************\n";
		String e31 = "\n";
		String expected = e01 + e02 + e03 + e04 + e05 + e06 + e07 + e08 + e09 + e10 + e11 + e12 + e13 + e14 + e15 + e16
				+ e17 + e18 + e19 + e20 + e21 + e22 + e23 + e24 + e25 + e26 + e27 + e28 + e29 + e30 + e31;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_65655_13131_correct() {
		int toDivide = 65655;
		int divider = 13131;
		String e1 = "65655 | 13131\n";
		String e2 = "65655 |-------\n";
		String e3 = "----- | 5\n";
		String e4 = "    0\n";
		String e5 = "\n";
		String e6 = "*********************\n";
		String e7 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_10_3_correct() {
		int toDivide = 10;
		int divider = 3;
		String e1 = "10 | 3\n";
		String e2 = " 9 |-------\n";
		String e3 = "-- | 3.(3)\n";
		String e4 = " 10\n";
		String e5 = "  9\n";
		String e6 = " ---\n";
		String e7 = "  10\n";
		String e8 = "\n";
		String e9 = "*********************\n";
		String e10 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9 + e10;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_1_4_correct() {
		int toDivide = 1;
		int divider = 4;
		String e1 = "1 | 4\n";
		String e2 = "0 |------\n";
		String e3 = "- | 0.25\n";
		String e4 = "10\n";
		String e5 = " 8\n";
		String e6 = "---\n";
		String e7 = " 20\n";
		String e8 = " 20\n";
		String e9 = " ---\n";
		String e10 = "  0\n";
		String e11 = "\n";
		String e12 = "*********************\n";
		String e13 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9 + e10 + e11 + e12 + e13;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_1_40_correct() {
		int toDivide = 1;
		int divider = 40;
		String e1 = "1 | 40\n";
		String e2 = "0 |-------\n";
		String e3 = "- | 0.025\n";
		String e4 = "100\n";
		String e5 = " 80\n";
		String e6 = "----\n";
		String e7 = " 200\n";
		String e8 = " 200\n";
		String e9 = " ----\n";
		String e10 = "   0\n";
		String e11 = "\n";
		String e12 = "*********************\n";
		String e13 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9 + e10 + e11 + e12 + e13;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_1_400_correct() {
		int toDivide = 1;
		int divider = 400;
		String e1 = "1 | 400\n";
		String e2 = "0 |--------\n";
		String e3 = "- | 0.0025\n";
		String e4 = "1000\n";
		String e5 = " 800\n";
		String e6 = "-----\n";
		String e7 = " 2000\n";
		String e8 = " 2000\n";
		String e9 = " -----\n";
		String e10 = "    0\n";
		String e11 = "\n";
		String e12 = "*********************\n";
		String e13 = "\n";
		String expected = e1 + e2 + e3 + e4 + e5 + e6 + e7 + e8 + e9 + e10 + e11 + e12 + e13;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_4_7_correct() {
		int toDivide = 4;
		int divider = 7;
		String e01 = "4 | 7\n";
		String e02 = "0 |------------\n";
		String e03 = "- | 0.(571428)\n";
		String e04 = "40\n";
		String e05 = "35\n";
		String e06 = "---\n";
		String e07 = " 50\n";
		String e08 = " 49\n";
		String e09 = " ---\n";
		String e10 = "  10\n";
		String e11 = "   7\n";
		String e12 = "  ---\n";
		String e13 = "   30\n";
		String e14 = "   28\n";
		String e15 = "   ---\n";
		String e16 = "    20\n";
		String e17 = "    14\n";
		String e18 = "    ---\n";
		String e19 = "     60\n";
		String e20 = "     56\n";
		String e21 = "     ---\n";
		String e22 = "      40\n";
		String e23 = "\n";
		String e24 = "*********************\n";
		String e25 = "\n";
		String expected = e01 + e02 + e03 + e04 + e05 + e06 + e07 + e08 + e09 + e10 + e11 + e12 + e13 + e14 + e15 + e16
				+ e17 + e18 + e19 + e20 + e21 + e22 + e23 + e24 + e25;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}

	@Test
	public void divideDecimal_1000_77_correct() {
		int toDivide = 1000;
		int divider = 77;
		String e01 = "1000 | 77\n";
		String e02 = " 77  |-------------\n";
		String e03 = "---- | 12.(987012)\n";
		String e04 = " 230\n";
		String e05 = " 154\n";
		String e06 = " ----\n";
		String e07 = "  760\n";
		String e08 = "  693\n";
		String e09 = "  ----\n";
		String e10 = "   670\n";
		String e11 = "   616\n";
		String e12 = "   ----\n";
		String e13 = "    540\n";
		String e14 = "    539\n";
		String e15 = "    ----\n";
		String e16 = "     100\n";
		String e17 = "      77\n";
		String e18 = "     ----\n";
		String e19 = "      230\n";
		String e20 = "      154\n";
		String e21 = "      ----\n";
		String e22 = "       760\n";
		String e23 = "\n";
		String e24 = "*********************\n";
		String e25 = "\n";
		String expected = e01 + e02 + e03 + e04 + e05 + e06 + e07 + e08 + e09 + e10 + e11 + e12 + e13 + e14 + e15 + e16
				+ e17 + e18 + e19 + e20 + e21 + e22 + e23 + e24 + e25;
		String actual = periodDivision.divideDecimal(toDivide, divider);
		assertTrue(expected.equals(actual));
	}
}
