package com.foxminded;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PeriodTest {

	Period period = new Period();
	
	@Test
	public void period_9342857142_correct() {
		StringBuilder s = new StringBuilder("9342857142");
		String actual = period.findPeriod(s);
		String expected = "93(428571)";
		assertEquals(expected, actual);
	}

	@Test
	public void period_3333333333_correct() {
		StringBuilder s = new StringBuilder("3333333333");
		String actual = period.findPeriod(s);
		String expected = "(3)";
		assertEquals(expected, actual);
	}

	@Test
	public void period_1234444444_correct() {
		StringBuilder s = new StringBuilder("1234444444");
		String actual = period.findPeriod(s);
		String expected = "123(4)";
		assertEquals(expected, actual);
	}

	@Test
	public void period_6818181818_correct() {
		StringBuilder s = new StringBuilder("6818181818");
		String actual = period.findPeriod(s);
		String expected = "6(81)";
		assertEquals(expected, actual);
	}

	@Test
	public void period_5233333333_correct() {
		StringBuilder s = new StringBuilder("5233333333");
		String actual = period.findPeriod(s);
		String expected = "52(3)";
		assertEquals(expected, actual);
	}

	@Test
	public void period_4285714285_correct() {
		StringBuilder s = new StringBuilder("4285714285");
		String actual = period.findPeriod(s);
		String expected = "(428571)";
		assertEquals(expected, actual);
	}

	@Test
	public void period_1234567890_correct() {
		StringBuilder s = new StringBuilder("1234567890");
		String actual = period.findPeriod(s);
		String expected = "1234567890";
		assertEquals(expected, actual);
	}

}
