package nz.ac.massey.cs.sdc.taxcalculator;

public class GeneralTaxCalculator {

	// a simple gst calculator - fixed rate
	public double gstCalculator(double amount) {
		double gst = 0.15;
		double value = (amount*gst)+amount;
		
		return value;
	}
	
	// a simple VAT calculator - rate to be added, depends on the country
	public double VATCalculator(double amount, double rate) {
		
		double value = (amount*rate) + amount;
	
		return (amount*rate) + amount;
	}
	

}