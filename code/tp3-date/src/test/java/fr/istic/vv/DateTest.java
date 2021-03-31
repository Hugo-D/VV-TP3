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
					if(!Year.of(y).isLeap() && m==2 && d>=29) { // getTime doesn't take account of the leapYears
						assertFalse(res);
						break;
					}					
					Calendar cal = Calendar.getInstance();
					cal.setLenient(false);
					cal.set(y, m-1, d);
					try {
						cal.getTime();
						assertTrue(res);
					} catch (Exception e) {
						assertFalse(res);
					}
				}
			}
		}
	}
}