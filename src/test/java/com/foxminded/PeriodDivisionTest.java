package com.foxminded;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PeriodDivisionTest {

	private PeriodDivision periodDivision = new PeriodDivision();

	@Test
	public void divideDecimal_1_1_correct() {
		int dividend = 1;
		int divider = 1;
		String expected = "1 | 1\n" + 
		"1 |---\n" + 
		"- | 1\n" + 
		"0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_20_4_correct() {
		int dividend = 20;
		int divider = 4;
		String expected = "20 | 4\n" + 
		"20 |---\n" + 
		"-- | 5\n" + 
		" 0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_10_4_correct() {
		int dividend = 10;
		int divider = 4;
		String expected = "10 | 4\n" + 
		" 8 |-----\n" + 
		"-- | 2.5\n" + 
		" 20\n" + 
		" 20\n" + 
		" ---\n" + 
		"  0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_0_1_correct() {
		int dividend = 0;
		int divider = 1;
		String expected = "0 | 1\n" + 
		"0 |---\n" + 
		"- | 0\n" + 
		"0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_0_minus1_correct() {
		int dividend = 0;
		int divider = -1;
		String expected = "0 | -1\n" + 
		"0 |----\n" + 
		"- | 0\n" + 
		"0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_minus1_1_correct() {
		int dividend = -1;
		int divider = 1;
		String expected = "-1 | 1\n" + 
		" 1 |----\n" + 
		" - | -1\n" + 
		" 0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_1_minus1_correct() {
		int dividend = 1;
		int divider = -1;
		String expected = "1 | -1\n" + 
		"1 |----\n" + 
		"- | -1\n" + 
		"0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test(expected = ArithmeticException.class)
	public void divideDecimal_1_0_returnsZero() {
		int dividend = 1;
		int divider = 0;
		periodDivision.divideDecimal(dividend, divider);
	}

	@Test
	public void divideDecimal_minus1_minus1_correct() {
		int dividend = -1;
		int divider = -1;
		String expected = "-1 | -1\n" + 
		" 1 |----\n" + 
		" - | 1\n" + 
		" 0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_minus20992_minus1_correct() {
		int dividend = -20992;
		int divider = -1;
		String expected = "-20992 | -1\n" + 
		" 2     |-------\n" + 
		" ----- | 20992\n" + 
		"   9\n" + 
		"   9\n" + 
		"   --\n" + 
		"    9\n" + 
		"    9\n" + 
		"    --\n" + 
		"     2\n" + 
		"     2\n" + 
		"     --\n" + 
		"     0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_123456_123456_correct() {
		int dividend = 123456;
		int divider = 123456;
		String expected = "123456 | 123456\n" + 
		"123456 |--------\n" + 
		"------ | 1\n" + 
		"     0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_123456_1_correct() {
		int dividend = 123456;
		int divider = 1;
		String expected = "123456 | 1\n" + 
		"1      |--------\n" + 
		"------ | 123456\n" + 
		" 2\n" + 
		" 2\n" + 
		" --\n" + 
		"  3\n" + 
		"  3\n" + 
		"  --\n" + 
		"   4\n" + 
		"   4\n" + 
		"   --\n" + 
		"    5\n" + 
		"    5\n" + 
		"    --\n" + 
		"     6\n" + 
		"     6\n" + 
		"     --\n" + 
		"     0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_10000_100_correct() {
		int dividend = 10000;
		int divider = 100;
		String expected = "10000 | 100\n" + 
		"100   |-----\n" + 
		"----- | 100\n" + 
		"    0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_100_minus99_correct() {
		int dividend = 100;
		int divider = -99;
		String expected = "100 | -99\n" + 
		" 99 |---------\n" + 
		"--- | -1.(01)\n" + 
		"  100\n" + 
		"   99\n" + 
		"  ----\n" + 
		"    100\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_12000000_100_correct() {
		int dividend = 12000000;
		int divider = 100;
		String expected = "12000000 | 100\n" + 
		"100      |--------\n" + 
		"-------- | 120000\n" + 
		" 200\n" + 
		" 200\n" + 
		" ----\n" + 
		"       0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_200100_100_correct() {
		int dividend = 200100;
		int divider = 100;
		String expected = "200100 | 100\n" + 
		"200    |------\n" + 
		"------ | 2001\n" + 
		"   100\n" + 
		"   100\n" + 
		"   ----\n" + 
		"     0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_1200600_1200_correct() {
		int dividend = 1200600;
		int divider = 1200;
		String expected = "1200600 | 1200\n" + 
		"1200    |--------\n" + 
		"------- | 1000.5\n" + 
		"    6000\n" + 
		"    6000\n" + 
		"    -----\n" + 
		"       0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_12060_70_correct() {
		int dividend = 12060;
		int divider = 70;
		String expected = "12060 | 70\n" + 
		" 70   |--------------\n" + 
		"----- | 172.(285714)\n" + 
		" 506\n" + 
		" 490\n" + 
		" ----\n" + 
		"  160\n" + 
		"  140\n" + 
		"  ----\n" + 
		"   200\n" + 
		"   140\n" + 
		"   ----\n" + 
		"    600\n" + 
		"    560\n" + 
		"    ----\n" + 
		"     400\n" + 
		"     350\n" + 
		"     ----\n" + 
		"      500\n" + 
		"      490\n" + 
		"      ----\n" + 
		"       100\n" + 
		"        70\n" + 
		"       ----\n" + 
		"        300\n" + 
		"        280\n" + 
		"        ----\n" + 
		"         200\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_65655_13131_correct() {
		int dividend = 65655;
		int divider = 13131;
		String expected = "65655 | 13131\n" + 
		"65655 |-------\n" + 
		"----- | 5\n" + 
		"    0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_10_3_correct() {
		int dividend = 10;
		int divider = 3;
		String expected = "10 | 3\n" + 
		" 9 |-------\n" + 
		"-- | 3.(3)\n" + 
		" 10\n" + 
		"  9\n" + 
		" ---\n" + 
		"  10\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_1_4_correct() {
		int dividend = 1;
		int divider = 4;
		String expected = "1 | 4\n" + 
		"0 |------\n" + 
		"- | 0.25\n" + 
		"10\n" + 
		" 8\n" + 
		"---\n" + 
		" 20\n" + 
		" 20\n" + 
		" ---\n" + 
		"  0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_1_40_correct() {
		int dividend = 1;
		int divider = 40;
		String expected = "1 | 40\n" + 
		"0 |-------\n" + 
		"- | 0.025\n" + 
		"100\n" + 
		" 80\n" + 
		"----\n" + 
		" 200\n" + 
		" 200\n" + 
		" ----\n" + 
		"   0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_1_400_correct() {
		int dividend = 1;
		int divider = 400;
		String expected = "1 | 400\n" + 
		"0 |--------\n" + 
		"- | 0.0025\n" + 
		"1000\n" + 
		" 800\n" + 
		"-----\n" + 
		" 2000\n" + 
		" 2000\n" + 
		" -----\n" + 
		"    0\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_4_7_correct() {
		int dividend = 4;
		int divider = 7;
		String expected = "4 | 7\n" + 
		"0 |------------\n" + 
		"- | 0.(571428)\n" + 
		"40\n" + 
		"35\n" + 
		"---\n" + 
		" 50\n" + 
		" 49\n" + 
		" ---\n" + 
		"  10\n" + 
		"   7\n" + 
		"  ---\n" + 
		"   30\n" + 
		"   28\n" + 
		"   ---\n" + 
		"    20\n" + 
		"    14\n" + 
		"    ---\n" + 
		"     60\n" + 
		"     56\n" + 
		"     ---\n" + 
		"      40\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_1000_77_correct() {
		int dividend = 1000;
		int divider = 77;
		String expected = "1000 | 77\n" + 
		" 77  |-------------\n" + 
		"---- | 12.(987012)\n" + 
		" 230\n" + 
		" 154\n" + 
		" ----\n" + 
		"  760\n" + 
		"  693\n" + 
		"  ----\n" + 
		"   670\n" + 
		"   616\n" + 
		"   ----\n" + 
		"    540\n" + 
		"    539\n" + 
		"    ----\n" + 
		"      100\n" + 
		"       77\n" + 
		"      ----\n" + 
		"       230\n" + 
		"       154\n" + 
		"       ----\n" + 
		"        760\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_minus985_minus1_correct() {
		int dividend = -985;
		int divider = -65;
		String expected = "-985 | -65\n" + 
		" 65  |-------------\n" + 
		" --- | 15.(153846)\n" + 
		" 335\n" + 
		" 325\n" + 
		" ----\n" + 
		"  100\n" + 
		"   65\n" + 
		"  ----\n" + 
		"   350\n" + 
		"   325\n" + 
		"   ----\n" + 
		"    250\n" + 
		"    195\n" + 
		"    ----\n" + 
		"     550\n" + 
		"     520\n" + 
		"     ----\n" + 
		"      300\n" + 
		"      260\n" + 
		"      ----\n" + 
		"       400\n" + 
		"       390\n" + 
		"       ----\n" + 
		"        100\n" + 
		"\n" + 
		"*********************\n" + 
		"\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}
	
	@Test
	public void divideDecimal_2001_200010_correct() {
		int dividend = 2001;
		int divider = 200010;
		String expected = "2001 | 200010\n"+
				   "   0 |--------------\n"+
				   "---- | 0.0100044997\n"+
				   "200100\n"+
				   "200010\n"+
				   "-------\n"+
				   "    900000\n"+
				   "    800040\n"+
				   "    -------\n"+
				   "     999600\n"+
				   "     800040\n"+
				   "     -------\n"+
				   "     1995600\n"+
				   "     1800090\n"+
				   "     --------\n"+
				   "      1955100\n"+
				   "      1800090\n"+
				   "      --------\n"+
				   "       1550100\n"+
				   "       1400070\n"+
				   "       --------\n"+
				   "        1500300\n"+
				   "\n"+
				   "*********************\n"+
				   "\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_1100_313_correct() {
		int dividend = 1100;
		int divider = 313;
		String expected = 
				 "1100 | 313\n"+
				 " 939 |--------------\n" +
				 "---- | 3.5143769968\n" +
				 " 1610\n" +
				 " 1565\n" +
				 " -----\n" +
				 "   450\n" +
				 "   313\n" +
				 "   ----\n" +
				 "   1370\n" +
				 "   1252\n" +
				 "   -----\n" +
				 "    1180\n" +
				 "     939\n" +
				 "    -----\n" +
				 "     2410\n" +
				 "     2191\n" +
				 "     -----\n" +
				 "      2190\n" +
				 "      1878\n" +
				 "      -----\n" +
				 "       3120\n" +
				 "       2817\n" +
				 "       -----\n" +
				 "        3030\n" +
				 "        2817\n" +
				 "        -----\n" +
				 "         2130\n" +
				 "         1878\n" +
				 "         -----\n" +
				 "          2520\n" +
				 "          2504\n" +
				 "          -----\n" +
				 "            16\n" +
				 "\n"+
				 "*********************\n" +
				 "\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}
	
	@Test
	public void divideDecimal_1_313_correct() {
		int dividend = 1;
		int divider = 313;
		String expected = 
					 "1 | 313\n" +
					 "0 |--------------\n" +
					 "- | 0.0031948881\n" +
					 "1000\n" +
					 " 939\n" +
					 "-----\n" +
					 "  610\n" +
					 "  313\n" +
					 "  ----\n" +
					 "  2970\n" +
					 "  2817\n" +
					 "  -----\n" +
					 "   1530\n" +
					 "   1252\n" +
					 "   -----\n" +
					 "    2780\n" +
					 "    2504\n" +
					 "    -----\n" +
					 "     2760\n" +
					 "     2504\n" +
					 "     -----\n" +
					 "      2560\n" +
					 "      2504\n" +
					 "      -----\n" +
					 "        560\n" +
					 "        313\n" +
					 "        ----\n" +
					 "        2470\n" +
			         "\n"+
			         "*********************\n" +
			         "\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_2_222_correct() {
		int dividend = 2;
		int divider = 222;
		String expected = 
					 "2 | 222\n" +
					 "0 |---------\n" +
					 "- | 0.(009)\n" +
					 "2000\n" +
					 "1998\n" +
					 "-----\n" +
					 "   2000\n" +
			         "\n"+
			         "*********************\n" +
			         "\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

	@Test
	public void divideDecimal_7_12_correct() {
		int dividend = 7;
		int divider = 12;
		String expected = 
					 "7 | 12\n" +
					 "0 |---------\n" +
					 "- | 0.58(3)\n" +
					 "70\n" +
					 "60\n" +
					 "---\n" +
					 "100\n" +
			         " 96\n"+
					 "----\n" +
					 "  40\n" +
			         "  36\n"+
					 "  ---\n" +
					 "   40\n" +
					 "\n" +
			         "*********************\n" +
			         "\n";
		String actual = periodDivision.divideDecimal(dividend, divider);
		assertEquals(expected, actual);
	}

}
