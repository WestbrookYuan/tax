package nz.ac.massey.ac.sdc.taxcalculator.repo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


/**
 * @author amjed tahir
 * class to save data into different file formats (HTML, txt). 
 */
public class DataLog {

	public void saveDataCSV(String tax, String income) throws IOException
	{
		Date date = new Date();
		FileWriter csvWriter = new FileWriter("logfile.csv");
		csvWriter.append("time");
		csvWriter.append(",");
		csvWriter.append("income");
		csvWriter.append(",");
		csvWriter.append("tax");
	    csvWriter.append("\n");
	    
	    csvWriter.append(date.toString());
	    csvWriter.append(",");
		csvWriter.append(income);
	    csvWriter.append(",");
		csvWriter.append(tax);


		csvWriter.flush();
		csvWriter.close();
	}
}
