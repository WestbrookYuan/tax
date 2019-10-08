package nz.ac.massey.cs.sdc.taxcalculator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * A simple tax calculator implementing the NZ tax rules accourding to 
 * http://www.ird.govt.nz/how-to/taxrates-codes/itaxsalaryandwage-incometaxrates.html
 * accessed on 9 August 12
 * @author jens dietrich
 * @ amjed tahir
 */
public class IncomeTaxCalculator extends GeneralTaxCalculator {
	
	Date date = new Date();
	double[] brackets = {0,14000,48000,70000};
	double[] taxRates = {10.5,17.5,30,33};
	
	public double calculateIncomeTax(double income) {
		// disable the next line to introduce an artificial delay - this will cause test cases with timeouts to fail
		// try {Thread.sleep(200);} catch (Exception x){}
		if (checkTax(income)) throw new IllegalArgumentException("the income must be positive");
		double tax = 0.0;
		for (int i=brackets.length-1;i>=0;i--) {
			double bracket = brackets[i];
			double taxRate = taxRates[i];
			if (income>bracket) {
				double taxableInThisBracket = income-bracket;
				income = income - taxableInThisBracket;
				
				taxCalculatorImpl txi = new taxCalculatorImpl();
				tax = txi.taxCalculation(tax, taxRate, taxableInThisBracket);
				
				if(checkTax(tax))
				{
					tax = txi.taxCalculation(tax, taxRate, taxableInThisBracket);
					
					System.out.println("To check that tax can't be a negative number");
					System.out.println("correct value! "+tax + " tax is positive number");
					try{
						printDetails(income, tax);
					}
					catch(FileNotFoundException e){
						e.printStackTrace();
					}
				}
				
			}
		}
		System.out.println(date);
		System.out.println("the tax for "+ income+ " is " + tax);

		return tax;
	}
	
	//method to check tax is positive 
	protected boolean checkTax(double tax) {
		return tax<0;
	}
	
	public void printDetails(double income, double tax) throws FileNotFoundException{
		try (PrintWriter out = new PrintWriter("details.txt")) {
		    out.println(income + " " + tax );
		}		
	}
	

}
