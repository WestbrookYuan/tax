package test.nz.ac.massey.cs.sdc.taxcalculator;

import static org.junit.Assert.*;

import nz.ac.massey.cs.sdc.taxcalculator.GeneralTaxCalculator;
import nz.ac.massey.cs.sdc.taxcalculator.IncomeTaxCalculator;

import org.junit.*;

/**
 * Tests cases for the income tax calculator based on the examples on the IRD web site:
 * http://www.ird.govt.nz/how-to/taxrates-codes/itaxsalaryandwage-incometaxrates.html
 * accessed on 9 August 12.
 * @author jens dietrich
 * @author amjed
 */
public class IncomeTaxCalculatorTests {
	
	private IncomeTaxCalculator calculator = null;

	private double income = 0.0;
	private double expectedIncomeTax = 0.0;
	
	@Before 
	public void setup() {
		calculator = new IncomeTaxCalculator();
	}
	@After 
	public void tearDown() {
		calculator = null;
	}

	@Test
	public void test1() {
		double tax = calculator.calculateIncomeTax(65238);
		assertEquals(12591.40,tax,0.01);
	}

	@Test
	public void test2() {
		double tax = calculator.calculateIncomeTax(45000);
		assertEquals(6895.0,tax,0.01);
	}
	
	// boundary tests
	@Test
	public void testZero() {
		double tax = calculator.calculateIncomeTax(0);
		assertEquals(0,tax,0.01);
	}
	
	@Test
	public void testSmallIncome() {
		double tax = calculator.calculateIncomeTax(10);
		assertEquals(1.05,tax,0.01);
	}
	
	@Test
	public void testLargeIncome() {
		double tax = calculator.calculateIncomeTax(Integer.MAX_VALUE);
		// should be very close to the max tax rate
		assertEquals(0.33*Integer.MAX_VALUE,tax,Integer.MAX_VALUE*0.001); 
	}
	
	@Test
	public void testNegativeIncome1() {
		try {
//			@SuppressWarnings("unused")
			double tax = calculator.calculateIncomeTax(-42);
			assertTrue(false);
		}
		catch (IllegalArgumentException x) {
			assertTrue(true);	
		}
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNegativeIncome2() {
		calculator.calculateIncomeTax(-42);
	}
	
	// income tax of 65238 is  12591.40
	@Test(timeout=100)
	public void test1InclPerformance() {
		double tax = calculator.calculateIncomeTax(65238);
		assertEquals(12591.40,tax,0.01);
	}

	// income tax of 45000 is  6895.0
	@Test(timeout=100)
	public void test2InclPerformance() {
		double tax = calculator.calculateIncomeTax(45000);
		assertEquals(6895.0,tax,0.01);
	}
	
	// test set of numbers
		@Test
		public void test3() {
			double tax = calculator.calculateIncomeTax(this.income);
			assertEquals(this.expectedIncomeTax, tax, 0.01);
			assertEquals(this.expectedIncomeTax, tax, 0.01);
			assertEquals(this.expectedIncomeTax, tax, 0.01);

		}

		// test zero 
		@Test
		public void test4() {
			try{ calculator.calculateIncomeTax(0);
			assertTrue(true);
			} catch (IllegalArgumentException x) {
				assertTrue(true);	
			}
		}

		// test the GST and VAT calculator (class (GeneralTaxCalculator))
		@Test
		public void test5() {
			double tax = -10;
			calculator.calculateIncomeTax(1000);
			GeneralTaxCalculator gtc = new GeneralTaxCalculator();
			gtc.gstCalculator(100);
			gtc.VATCalculator(100, 0.15);
		}

		// test GST and income tax together (classes GeneralTaxCalculator and IncomeTaxCalculator)
		@Test
		public void test6() {
			IncomeTaxCalculator calculator= new IncomeTaxCalculator();
			double tax = calculator.calculateIncomeTax(50000);
			assertEquals(8020, tax, 0.010);
			
			GeneralTaxCalculator gtc = new GeneralTaxCalculator();

			double gstTax= gtc.gstCalculator(100);
			assertEquals(115, gstTax, 0.010);
		}

		
		public void test7() {
			
		}

	
}
