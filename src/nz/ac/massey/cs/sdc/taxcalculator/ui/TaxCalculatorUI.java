
package nz.ac.massey.cs.sdc.taxcalculator.ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.*;

import nz.ac.massey.ac.sdc.taxcalculator.repo.DataLog;
import nz.ac.massey.cs.sdc.taxcalculator.IncomeTaxCalculator;
import nz.ac.massey.cs.sdc.taxcalculator.GeneralTaxCalculator;

/**
 * Swing based-user interface for the tax calculator.
 * This class is executable, i.e. it has a main method.
 * @author Jens Dietrich
 * @author amjed tahir
 * @version 1.3
 * @since 1.2
 */ 

public class TaxCalculatorUI  extends JFrame  {

	private static final long serialVersionUID = -249195317281900474L;
	// model
    private IncomeTaxCalculator taxCalculator = new IncomeTaxCalculator(); 
    // components
    NumberFormat numberFormat = NumberFormat.getInstance(Locale.UK);
    JFormattedTextField incomeField = new JFormattedTextField(numberFormat);
    JFormattedTextField taxField = new JFormattedTextField(numberFormat);
	JLabel incomeLabel = new JLabel("Income:");
	JLabel taxLabel = new JLabel("Income Tax:");
	JButton computeButton = new JButton("Compute Income Tax");
	JButton exitButton = new JButton("Exit Application");
	JButton saveButton = new JButton("log results ");

	
	/**
	 * Main method - launch the UI.
	 */
	public static void main(String[] args) {
		TaxCalculatorUI application = new TaxCalculatorUI();
		application.setSize(500,125);
		application.setLocation(400,300);
		application.setResizable(false);
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		application.setVisible(true);
	}


    public GeneralTaxCalculator getTaxCalculator() {
        return taxCalculator;
    }
    public void setTaxCalculator(IncomeTaxCalculator taxCalculator) {
        this.taxCalculator = taxCalculator;
    }
    
    /**
     * Constructor.
     */
    public TaxCalculatorUI() {
    	super("Tax Calculator");
    	init();
    }
    /**
     * Initialize the user interface.
     */
    private void init() {
    	JPanel contentPane = new JPanel(new BorderLayout(5,5));
    	this.setContentPane(contentPane);
    	
    	taxField.setEditable(false);
    	
    	JPanel main = new JPanel();
    	main.setLayout(new GridLayout(2,3,5,5));
    	main.add(incomeLabel);
    	main.add(taxLabel);
    	main.add(incomeField);
    	main.add(taxField);
    	main.setBorder(BorderFactory.createEmptyBorder(5,25,5,25));
    	contentPane.add(main,BorderLayout.CENTER);
    	
    	JPanel toolbar = new JPanel(new FlowLayout(FlowLayout.CENTER));
    	toolbar.add(computeButton);
		computeButton.setMnemonic('c');
		toolbar.add(exitButton);
		toolbar.add(saveButton);
		exitButton.setMnemonic('x');
		contentPane.add(toolbar,BorderLayout.SOUTH);
    	
    	computeButton.addActionListener(
    		new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				computeIncomeTax();
    			}
    		}	
    	);
		exitButton.addActionListener(
			new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			}	
		);
		
		// compute the income tax and saves the record into an CSV file through class DataLog
		saveButton.addActionListener(
				new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String tax = taxField.getText();
					
					computeIncomeTax();
					
					String income = incomeField.getText();
					DataLog datalog = new DataLog();
					
					try {
						datalog.saveDataCSV(tax, income);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
			}	
		);
		
		this.incomeField.addKeyListener(
			new KeyListener() {

				public void keyPressed(KeyEvent e) {
					taxField.setText("");					
				}
				public void keyReleased(KeyEvent e) {
					taxField.setText("");					
				}
				public void keyTyped(KeyEvent e) {
					taxField.setText("");					
				}		
			}	
		);
    }
    
    /**
     * Compute the income tax.
     */
	private void computeIncomeTax() {
		try {
			String incomeAsText = this.incomeField.getText();
			double income = Double.valueOf(""+numberFormat.parse(incomeAsText));
			double tax = this.taxCalculator.calculateIncomeTax(income);
			this.taxField.setValue(tax);
		}
		catch (Exception x) {
			handleException(x);
		}			
	}
	
	/**
     * Compute gst  tax.
     */
	private void computeGSTTax() {
		try {
			//testing using dummy data - form not implemented yet
			String incomeAsText = this.incomeField.getText();
			double amount = Double.valueOf(""+numberFormat.parse(incomeAsText));
			double tax = this.taxCalculator.calculateIncomeTax(amount);
			double totalAmount = amount+tax;
		}
		catch (Exception x) {
			handleException(x);
		}			
	}
	
	/**
	 * Handle exceptions.
	 * @param x an exception
	 */
	private void handleException(Throwable x) {
		x.printStackTrace();
		JOptionPane.showMessageDialog(this,"<html>An error has occured.<br>Details have been logged</html>","Error",JOptionPane.ERROR_MESSAGE);
		this.taxField.setText("");
	}

    
 } 



