/**
 * 
 */
package utility;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import domain.RatePayer;

/**
 * @author Nikhil
 *
 */
public class LoadRatePayer {
	private String filename;
	private String dir;
	private ArrayList<RatePayer> ratepayers = new ArrayList<>();

	// constructor
	public LoadRatePayer(String fn, String dir) {
		this.setFilename(fn);
		this.setDir(dir);
	}

	// read from csv file
	public void readRatePayersFromCsvFile() {
		// file path
		Path pathToFile = Paths.get(this.dir + File.separator + this.filename);

		try {
			BufferedReader br = Files.newBufferedReader(pathToFile);
			String line = br.readLine();
			// loop to extract data from file if not empty
			while (line != null) {
				// creating a string array. Spliting data with commas
				String[] attributes = line.split(",");
				// extracting boolean value of ratepayer charity status
				String name = attributes[0];
				String address = attributes[1];
				String postcode = attributes[2];
				String phone = attributes[3];
				String type = attributes[4];
				boolean charity = Boolean.parseBoolean(attributes[5]);
				// constructor
				RatePayer r = new RatePayer(name, address, postcode, phone, type, charity);
				// adding the ratepayer
				getRatepayers().add(r);
				line = br.readLine();
			}
			// exceptions
		} catch (IOException e) {
			e.printStackTrace();
		}
		// end readRatePayersFromCsvFile
	}

	// data serialization
	public void serialize_objects() {
		ArrayList<RatePayer> ratepayers = this.getRatepayers();
		for (int i = 0; i < ratepayers.size(); i++) {
			try {
				FileOutputStream fileOutput = new FileOutputStream(
						this.dir + File.separator + "\\ratepayers\\" + i + ".ser");
				// instantiating new output object
				ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput);
				// writing out objects
				objectOutput.writeObject(ratepayers.get(i));
				// closing output stream
				objectOutput.close();
				fileOutput.close();
				// print out success after data serialized
				// System.out.println("Ratepayers data has been serialized and saved to folder!
				// \n");
				// exceptions
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// data deserialization
	public void deserialize_objects() {
		System.out.println("\nRatepayers data are now deserialized. ");
		System.out.println("---------------------------- \n");
		for (int i = 0; i < getRatepayers().size(); i++) {
			try {
				FileInputStream fileInput = new FileInputStream(
						this.dir + File.separator + "\\ratepayers\\" + i + ".ser");
				ObjectInputStream objectInput = new ObjectInputStream(fileInput);
				// reading objects
				try {
					RatePayer rp = (RatePayer) objectInput.readObject();
					// catching exceptions
				} catch (ClassNotFoundException rp) {
					rp.printStackTrace();
				}
				// closing input stream
				objectInput.close();
				fileInput.close();
				// exceptions
			} catch (IOException e) {
				e.printStackTrace();
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

	public ArrayList<RatePayer> getRatepayers() {
		return ratepayers;
	}

	public void setRatepayers(ArrayList<RatePayer> ratepayers) {
		this.ratepayers = ratepayers;
	}
}
