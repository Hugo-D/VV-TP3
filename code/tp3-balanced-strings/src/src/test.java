import static org.junit.Assert.*;

import org.junit.Test;

public class test {

	@Test
	public void testQuestion1() {
		float troisfoiszeroquatre = (float)3 * (float).4;
		System.err.println(troisfoiszeroquatre);
		System.err.println((float)(3 * .4));
		assertTrue(3 * .4 == 1.2);
	}
	
	@Test
	public void testQuestion2() {
		int a = 1;
		int b = 1;
		@SuppressWarnings("deprecation")
		Integer aComplex = new Integer(1);
		@SuppressWarnings("deprecation")
		Integer bComplex = new Integer(1);
		
		assertEquals(a, b); // PASS
		System.err.println("Equals1 PASS");
		assertSame(a, b); // PASS
		System.err.println("Same1 PASS");
		
		assertEquals(aComplex, bComplex); // PASS
		System.err.println("Equals2 PASS");
		assertSame(aComplex, bComplex); // FAIL
		System.err.println("Same2 PASS");
	}
	
}
