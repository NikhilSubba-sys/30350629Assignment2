/**
 * 
 */
package operation;

import java.util.Scanner;

import domain.RatePayer;
import utility.LoadProperty;
import utility.LoadRatePayer;

/**
 * @author 30350629
 *
 */
public class LoadRatePayers extends FunctionalDialog {

	private int loadRatePayers;
	private static final int LOAD_RATEPAYERS = 1, END = 0;
	private static final int MAX_ADD_DETAILS = 1;
	private static final String RATEPAYER_DETAILS = "Please select an option. \n" + LOAD_RATEPAYERS
			+ ". Load Ratepayers. \n" + END + ". To Exit \n";
	private int lrp;
	private static String RATEPAYER_NAME;
	private static int RATEPAYER_NAME_INDEX;
	private String dir = "C:\\Users\\Sonum\\Desktop\\ITECH2306_202007_Lab_Assignment1-30350175\\bin\\operation";
	private String ratepayer_fn = "ITECH2306_2020_Load_RatePayers.csv";
	private String properties_fn = "ITECH2306_2020_Load_Properties.csv";

	private static final int MAX_NO_USER_INPUTS = 2;

	// scanner function
	public LoadRatePayers(Scanner console) {
		super(MAX_NO_USER_INPUTS, console);
	}

	// displays ratepayers details
	public void showRatePayerDetails() {
		LoadRatePayer loadratepayer;
		loadratepayer = new LoadRatePayer(ratepayer_fn, dir);
		loadratepayer.readRatePayersFromCsvFile();
		// serialize ratepayers data
		// loadratepayer.serialize_objects();
		// deserialize ratepayers data
		loadratepayer.deserialize_objects();
		try {
			for (int i = 0; i < loadratepayer.getRatepayers().size(); i++) {
				RATEPAYER_NAME_INDEX = i + 1;
				RATEPAYER_NAME = loadratepayer.getRatepayers().get(i).getName();
				System.out.println(RATEPAYER_NAME_INDEX + ". " + RATEPAYER_NAME);
			}
		} catch (IndexOutOfBoundsException iobe) {
			iobe.printStackTrace();
		}
	}

	@Override
	protected void obtainInput(int i) {
		// input here
		switch (i) {
		case 0:
			loadRatePayers = obtainIntInput(MAX_ADD_DETAILS, RATEPAYER_DETAILS);
			if (loadRatePayers == END)
				setStillRunning(false);
			break;
		case 1:
			this.showRatePayerDetails();
			try {
				System.out.println(END + ". To Exit");
				lrp = obtainIntInput(RATEPAYER_NAME_INDEX, RATEPAYER_NAME);
				if (lrp == END)
					setStillRunning(false);
				break;
			} catch (NullPointerException npe) {
				npe.printStackTrace();
			}
			break;
		}
	}

	// obtain int input from user
	private int obtainIntInput(int max, String prompt) {
		System.out.println(prompt);
		return validateInt(0, max);
	}

	// validate int input from user
	private int validateInt(int min, int max) {
		int userInput;
		do {
			System.out.print("Enter a selection (" + min + "-" + max + "):");
			if (!getScanner().hasNextInt())
				userInput = max + 1;
			else
				userInput = getScanner().nextInt(); // obtain the input
			getScanner().nextLine(); // gets rid of the newline after the number we just read
			if (userInput < min || userInput > max)
				System.out.println("Invalid choice.");
		} while (userInput < min || userInput > max);
		System.out.println(); // put a space before the next output
		return userInput;
	}

	@Override
	protected void respondToInput() {
		RatePayer ratePayer = null;
		LoadRatePayer loadratepayer = null;
		LoadProperty loadproperty = null;
		switch (loadRatePayers) {
		case (LOAD_RATEPAYERS):

			loadproperty = new LoadProperty(properties_fn, dir);
			loadproperty.readPropertyFromCsvFile();
			loadratepayer = new LoadRatePayer(ratepayer_fn, dir);
			loadratepayer.readRatePayersFromCsvFile();
			int index = lrp - 1;

			String name = loadratepayer.getRatepayers().get(index).getName();
			String address = loadratepayer.getRatepayers().get(index).getAddress();
			String postcode = loadratepayer.getRatepayers().get(index).getPostcode();
			String phone = loadratepayer.getRatepayers().get(index).getPhone();
			String type = loadratepayer.getRatepayers().get(index).getType();
			boolean charity = loadratepayer.getRatepayers().get(index).isCharity();
			ratePayer = new RatePayer(name, address, postcode, phone, type, charity);
			if (ratePayer != null)

				System.out.println("You have selected: " + ratePayer.getName());
				System.out.println("======================================= \n");
				// loop to extract data from file if not empty
				
				System.out.println("Properties owned: ");
				break;
		case (END):
			break;

		}
	}

	/**
	 * @return the loadRatePayers
	 */
	public int getLoadRatePayers() {
		return loadRatePayers;
	}

	/**
	 * @param loadRatePayers the loadRatePayers to set
	 */
	public void setLoadRatePayers(int loadRatePayers) {
		this.loadRatePayers = loadRatePayers;
	}

	public int getLrp() {
		return lrp;
	}

	public void setLrp(int lrp) {
		this.lrp = lrp;
	}

	/**
	 * @return the loadRatepayers
	 */
	public static int getLoadRatepayers() {
		return LOAD_RATEPAYERS;
	}

	/**
	 * @return the end
	 */
	public static int getEnd() {
		return END;
	}

	/**
	 * @return the maxAddDetails
	 */
	public static int getMaxAddDetails() {
		return MAX_ADD_DETAILS;
	}

	/**
	 * @return the ratepayerDetails
	 */
	public static String getRatepayerDetails() {
		return RATEPAYER_DETAILS;
	}

	/**
	 * @return the maxNoUserInputs
	 */
	public static int getMaxNoUserInputs() {
		return MAX_NO_USER_INPUTS;
	}

}
