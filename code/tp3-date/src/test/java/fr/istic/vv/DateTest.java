package fr.istic.vv;

import org.junit.jupiter.api.Test;

import fr.istic.vv.Date.DateNotValidException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.time.Year;
import java.util.Calendar;

class DateTest {

	@Test
	public void Test_constructeur_date_valide() {
		try {
			Date date1 = new Date(1, 1, 1);
		} catch (DateNotValidException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void Test_constructeur_date_invalide() {
		try {
			Date date1 = new Date(1, 0, 1);
			fail();
		} catch (DateNotValidException e) {

		}
	}

	@Test
	public void Test_isLeapYear() {
		for (int y = -400; y <= 800; y++) {
			boolean y_verify = Year.of(y).isLeap();
			boolean res = Date.isLeapYear(y);
			assertEquals(y_verify, res);
		}
	}

	@Test
	public void Test_isValid_months() {
		for (int i = -12; i <= 24; i++) {
			boolean res = Date.isValidDate(1, i, 1);
			if (i < 1 || i > 12) {
				assertFalse(res);
			} else {
				assertTrue(res);
			}
		}
	}

	@Test
	public void Test_isValid_days() {
		for (int y = 1; y <= 800; y++) {
			for (int m = 1; m <= 12; m++) {
				for (int d = -30; d <= 60; d++) {
					boolean res = Date.isValidDate(d, m, y);
					
					Calendar cal = Calendar.getInstance();
					cal.clear();
					cal.setLenient(false);
					cal.set(y, m-1, d);
					String str =cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR);
					System.err.println(str);
					try {
						cal.getTime();
						assertTrue("TRUE  "+d+"-"+(m-1)+"-"+y+"\nCAL :"+cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR),res);
					} catch (Exception e) {
						assertFalse("FALSE  "+d+"-"+(m-1)+"-"+y+"\nCAL :"+cal.get(Calendar.DAY_OF_MONTH)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.YEAR),res);
					}
				}

			}

		}
	}
	
	@Test
	public void Test_29() {
		boolean res = Date.isValidDate(29, 2, 100);
		Calendar cal = Calendar.getInstance();
		cal.setLenient(false);
		cal.clear();
		cal.set(100, 1, 29);
		System.err.println((cal.get(cal.YEAR)));
		//cal.getTime();
		assertFalse(29+"-"+2+"-"+100+"\nCAL :"+cal.get(cal.DAY_OF_MONTH)+"-"+cal.get(cal.MONTH)+"-"+cal.get(cal.YEAR),res);
	}
	
	@Test
	public void Test_leapyear100() {
		assertFalse(Date.isLeapYear(100));
	}
}