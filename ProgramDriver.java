/**
 * 
 */
package utility;

/**
 * @author Nikhil
 *11
 */
public class ProgramDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		

		
		LoadProperty loadproperty ;	
		// dir and file link
		String dir = "C:\\Users\\Nikhil Subba\\Desktop\\ITECH2306_202007_Lab_Assignment1";	
		String properties_fn = "ITECH2306_2020_Load_Properties.csv";

		// instantiating new LoadProperty object to read from file
		loadproperty = new LoadProperty(properties_fn, dir);
		// reading from excel file
		loadproperty.readPropertyFromCsvFile();
		// serialize properties data
		loadproperty.serialize_objects();	
		
		loadproperty.deserialize_objects();	
	}

}
