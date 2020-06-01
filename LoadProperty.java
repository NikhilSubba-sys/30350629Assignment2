/**
 * 
 */
package utility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import domain.Commercial;
import domain.Hospital;
import domain.Industrial;
import domain.Other;
import domain.Property;
import domain.RatePayer;
import domain.Residential;
import domain.SchoolCommunity;
import domain.VacantLand;

/**
 * @author Nikhil
 *
 */
public class LoadProperty {
	private String filename;
	private String dir;
	ArrayList<Property> properties = new ArrayList<>();

	// constructor
	public LoadProperty(String fn, String dir) {
		this.setFilename(fn);
		this.setDir(dir);
	}

	// display properties

	// read from property csv file
	public void readPropertyFromCsvFile() {
		// file path
		Path pathToFile = Paths.get(this.dir + File.separator + this.filename);

		try {
			BufferedReader br = Files.newBufferedReader(pathToFile);
			String line = br.readLine();
			// loop to extract data from file if not empty
			while (line != null) {
				// creating a string array. Spliting data with commas
				String[] attributes = line.split(",");
				// instantiating new property objects (cannot instantatiate from abstract class)
				Property property = null;
				// common attributes
				String typesOfProperties = attributes[0];
				String description = attributes[1];
				String location = attributes[2];
				double area = Double.parseDouble(attributes[3]);
				double siteValue = Double.parseDouble(attributes[4]);
				double capitalImprovedValue = Double.parseDouble(attributes[5]);
				double capitalImprovedRate = Double.parseDouble(attributes[6]) / 100;
				double netAnnualValue = Double.parseDouble(attributes[7]);
				String valuationDate = attributes[8];
				String ratePayerName = attributes[9];
				RatePayer rp;
				int i = 0;

				LoadRatePayer loadratepayer;
				String dir = "C:\\Users\\Nikhil Subba\\Desktop\\ITECH2306_202007_Lab_Assignment1";
				String ratepayer_fn = "ITECH2306_2020_Load_RatePayers.csv";
				loadratepayer = new LoadRatePayer(ratepayer_fn, dir);
				loadratepayer.readRatePayersFromCsvFile();
				// iterration over ratepayers
				for (i = 0; i < loadratepayer.getRatepayers().size(); i++) {
					String name = loadratepayer.getRatepayers().get(i).getName();
					String address = loadratepayer.getRatepayers().get(i).getAddress();
					String postcode = loadratepayer.getRatepayers().get(i).getPostcode();
					String phone = loadratepayer.getRatepayers().get(i).getPhone();
					String type = loadratepayer.getRatepayers().get(i).getType();
					boolean charity = loadratepayer.getRatepayers().get(i).isCharity();
					// new ratepayer
					rp = new RatePayer(name, address, postcode, phone, type, charity);
					// match owner
					if (rp.getName().equals(ratePayerName)) {

						// switching properties
						switch (typesOfProperties) {
						case ("Commercial"):
							// attributes
							String businessName = attributes[10];
							String businessABN = (attributes[11]).toString();
							// iteration over property objects
							property = new Commercial(description, location, area, siteValue, capitalImprovedValue,
									capitalImprovedRate, netAnnualValue, valuationDate, rp, businessName, businessABN);
							// adding to array list
							properties.add(property);
							break;
						case ("Industrial"):
							// attributes
							String hazardStatus = attributes[10];
							boolean heavyVehicleStatus = Boolean.parseBoolean(attributes[11]);
							// industrial property constructor
							property = new Industrial(description, location, area, siteValue, capitalImprovedValue,
									capitalImprovedRate, netAnnualValue, valuationDate, rp, hazardStatus,
									heavyVehicleStatus);
							// adding to array list
							properties.add(property);
							break;
						case ("Residential"):
							// attributes
							String propertyType = attributes[10];
							String architecturalStyle = attributes[11];
							// residential property constructor
							property = new Residential(description, location, area, siteValue, capitalImprovedValue,
									capitalImprovedRate, netAnnualValue, valuationDate, rp, propertyType,
									architecturalStyle);
							// adding to array list
							properties.add(property);
							break;
						case ("Hospital"):
							// attributes
							boolean status = Boolean.parseBoolean(attributes[10]);
							String facilities = attributes[11];
							int numFloors = Integer.parseInt(attributes[12]);
							// hospital property constructor
							property = new Hospital(description, location, area, siteValue, capitalImprovedValue,
									capitalImprovedRate, netAnnualValue, valuationDate, rp, status, facilities,
									numFloors);
							// adding to array list
							properties.add(property);
							break;
						case ("SchoolCommunity"):
							// attributes
							String classification = attributes[10];
							String categorySize = attributes[11];
							// schoolcommunity property constructor
							property = new SchoolCommunity(description, location, area, siteValue, capitalImprovedValue,
									capitalImprovedRate, netAnnualValue, valuationDate, rp, classification,
									categorySize);
							// adding to array list
							properties.add(property);
							break;
						case ("Other"):
							// attributes
							String specialDescription = attributes[10];
							// other property constructor
							property = new Other(description, location, area, siteValue, capitalImprovedValue,
									capitalImprovedRate, netAnnualValue, valuationDate, rp, specialDescription);
							// adding to array list
							properties.add(property);
							break;
						case ("VacantLand"):
							// attributes
							String[] overlays = new String[] { attributes[10] };
							// vacantland property constructor
							property = new VacantLand(description, location, area, siteValue, capitalImprovedValue,
									capitalImprovedRate, netAnnualValue, valuationDate, rp, overlays);
							// adding to array list
							properties.add(property);
							break;
						}
						// prints out values from file for all types of properties
						line = br.readLine();
					}
				}
			}
			// exceptions
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		// end readRatePayersFromCsvFile
	}

	// serialize property data
	public void serialize_objects() {
		ArrayList<Property> properties = this.properties;
		for (int i = 0; i < properties.size(); i++) {
			try {
				FileOutputStream fileOutput = new FileOutputStream(
						this.dir + File.separator + "\\properties\\" + i + ".ser");
				// instantiating new output object
				ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
				// writing out objects
				objectOutput.writeObject(properties.get(i));
				// closing output stream
				objectOutput.close();
				fileOutput.close();
				// print out success after data serialized
				System.out.println();
				System.out.println("Properties data has been serialized and saved to folder! \n");
				// exceptions
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// deserialize properties
	public void deserialize_objects() {
		System.out.println("\nProperties data are now deserialized. ");
		System.out.println("---------------------------- \n");
		for (int i = 0; i < properties.size(); i++) {
			try {
				FileInputStream fileInput = new FileInputStream(
						this.dir + File.separator + "\\properties\\" + i + ".ser");
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				// reading objects
				try {
					Property property = (Property) objectInput.readObject();
					// exceptions
				} catch (ClassNotFoundException cnf) {
					cnf.printStackTrace();
				}
				// closing input stream
				objectInput.close();
				fileInput.close();
				// exceptions
			} catch (IOException ioe) {
				ioe.printStackTrace();
				return;
			}
		}
	}

	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * @return the dir
	 */
	public String getDir() {
		return dir;
	}

	/**
	 * @param dir the dir to set
	 */
	public void setDir(String dir) {
		this.dir = dir;
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void setProperties(ArrayList<Property> properties) {
		this.properties = properties;
	}

}
