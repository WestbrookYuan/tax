package nz.ac.massey.cs.sdc.taxcalculator;

public class taxCalculatorImpl {
	
	
	private double incomeTax=0;
	private double taxRate=0;
	private double gstRate = 0;
	private double VATRate=0;

	public double getTax() {
		return incomeTax;
	}

	public void setTax(double tax) {
		this.incomeTax = tax;
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getGstRate() {
		return gstRate;
	}

	public void setGstRate(double gstRate) {
		this.gstRate = gstRate;
	}

	public double getVATRate() {
		return VATRate;
	}

	public void setVATRate(double vATRate) {
		VATRate = vATRate;
	}

	
	protected double taxCalculation(double tax, double taxRate, double taxableInThisBracket) {
		tax = tax + taxableInThisBracket*taxRate/100;
		return tax;
	}
}
