package operation;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import domain.RatePayer;

/**
 * @author 30320407
 * @version 1.0
 * @created 25/05/2020 12:20am
 */
public class AddRatePayer extends FunctionalDialog {

	private int ratePayerDetails;
	private static final int ADD_DETAILS = 1, 
							 END = 0;
	private static final int MAX_ADD_DETAILS = 1;
	private static final String RATEPAYER_DETAILS = "Please select an option. \n" + 
							 ADD_DETAILS + ". Add Ratepayer's details. \n" + 
							 END + ". To Exit \n";
	// ratepayer option
	private String ratePayerName;
	private static final String NAME_PROMPT = "What is ratepayer's name? "; // ask for the name input
	private static String RATEPAYER_NAME;
	// address input
	private String ratePayerAddress;
	private static final String ADDRESS_PROMPT = "What is ratepayer's address? ";
	private static String RATEPAYER_ADDRESS;
	// ratepayer's postcode
	private String ratePayerPostode;
	private static final String POSTCODE_PROMPT = "What is ratepayer's postcode? ";
	private static String RATEPAYER_POSTCODE;
	// phone number (optional)
	private boolean optionalratePayerPhone;
	private static final String PHONE_PROMPT = "Would you like to enter a phone number ? \n";
	// if yes then gets user input
	private String ratePayerPhone;
	private static final String NEW_PHONE_PROMPT = "Please enter a phone number: ";
	private static String RATEPAYER_PHONE;
	// type of organisation
	private String organisationType;
	private static final String TYPE_PROMPT = "What type of organisation is it? \n";
	private static String ORGANISATION_TYPE;
	// add the ratepayer's charity status
	private boolean addCharity;
	private static final String CHARITY_STATUS_PROMPT = "Does the ratepayer donate to charity? \n";
	private static final int MAX_NO_USER_INPUTS = 7;

	public AddRatePayer(Scanner console) {
		super(MAX_NO_USER_INPUTS, console);
	}

	@Override
	public void obtainInput(int i) {
		// input here
		switch (i) {
		case 0:
			ratePayerDetails = obtainIntInput(MAX_ADD_DETAILS, RATEPAYER_DETAILS);
			if (ratePayerDetails == END)
				setStillRunning(false);
			break;
		case 1:
			setRatePayerName(obtainStringInput(RATEPAYER_NAME, NAME_PROMPT));
			break;
		case 2:
			ratePayerAddress = obtainStringInput(RATEPAYER_ADDRESS, ADDRESS_PROMPT);
			break;
		case 3:
			ratePayerPostode = obtainStringInput(RATEPAYER_POSTCODE, POSTCODE_PROMPT);
			break;
		case 4:
			optionalratePayerPhone = obtainBooleanInput(PHONE_PROMPT);
			if (optionalratePayerPhone == true) {
				ratePayerPhone = obtainStringInput(RATEPAYER_PHONE, NEW_PHONE_PROMPT);
			}
			break;
		case 5:
			organisationType = obtainStringInput(ORGANISATION_TYPE, TYPE_PROMPT);
			break;
		case 6:
			addCharity = obtainBooleanInput(CHARITY_STATUS_PROMPT);
			break;
		}
	}

	// user string input
	private String obtainStringInput(String s, String prompt) {
		System.out.println(prompt);
		String userInput;
		if (!getScanner().hasNext()) {
			userInput = "Details cannot be blank.";
			System.out.println(userInput);
		} else {
			userInput = getScanner().nextLine(); // obtain the input
		}
		System.out.println(); // put a space before the next output
		return userInput;
	}

	private int obtainIntInput(int max, String prompt) {
		System.out.println(prompt);
		return validateInt(0, max);
	}

	private boolean obtainBooleanInput(String prompt) {
		System.out.println(prompt);
		return validateBoolean();
	}

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

	private boolean validateBoolean() {
		boolean userInput;
		System.out.print("Enter a selection -(true or false)");

		if (!getScanner().hasNextBoolean()) {
			userInput = false;
			System.out.println("Invalid choice. Assuming false.");
		} else
			userInput = getScanner().nextBoolean(); // obtain the input
		getScanner().nextLine(); // gets rid of the newline after the input we just read
		System.out.println(); // put a space before the next output
		return userInput;
	}

	@Override
	public void respondToInput() {
		RatePayer ratePayer = null;
		switch (ratePayerDetails) {
		case (ADD_DETAILS):
			ratePayer = new RatePayer();
			break;
		case (END):
			break;
		}
		if (ratePayer != null) {
			// sets new ratepayer's details from input
			ratePayer.setName(ratePayerName);
			ratePayer.setAddress(ratePayerAddress);
			ratePayer.setPostcode(ratePayerPostode);
			if (optionalratePayerPhone == true)
				ratePayer.setPhone(ratePayerPhone);
			else
				ratePayer.setPhone("Not Provided");
			ratePayer.setType(organisationType);
			if ((addCharity == true) || (addCharity == false))
				ratePayer.setCharity(addCharity);

			try {
				// Save to fil
				String newRatePayer = (ratePayer.getName() + ", " + ratePayer.getAddress() + ", "
						+ ratePayer.getPostcode() + ", " + ratePayer.getPhone() + ", " + ratePayer.getType() + ", "
						+ ratePayer.isCharity());
				FileOutputStream fileOutput = new FileOutputStream(
						"C:\\Users\\Sonum\\Desktop\\ITECH2306_202007_Lab_Assignment1-30350175\\bin\\operation"
								+ "\\ITECH2306_2020_Load_RatePayers.csv",
						true);
				// writing out objects
				PrintStream outFile = new PrintStream(fileOutput);
				outFile.println(newRatePayer);
				// closing output stream
				fileOutput.close();
				outFile.close();
				// success message
				System.out.println("New rate payer details saved to file!");
				// exceptions
			} catch (IOException e) {
				e.printStackTrace();
			}
			// print new entry details
			System.out.println("NEW ENTRY DETAILS");
			System.out.println("================= \n");
			System.out.println("New ratepayer's details: \nName=" + ratePayer.getName() + ", Address="
					+ ratePayer.getAddress() + ", Postcode=" + ratePayer.getPostcode().toString() + ", Phone="
					+ ratePayer.getPhone().replaceFirst("(\\d{2})(\\d{4})(\\d{4})", "($1) $2-$3").toString() + ", Type="
					+ ratePayer.getType() + ", Charity=" + ratePayer.isCharity() + "\n");
		}
	}

	/**
	 * @return the newPhonePrompt
	 */
	public static String getNewPhonePrompt() {
		return NEW_PHONE_PROMPT;
	}

	/**
	 * @return the optionalratePayerPhone
	 */
	public boolean isOptionalratePayerPhone() {
		return optionalratePayerPhone;
	}

	/**
	 * @param optionalratePayerPhone the optionalratePayerPhone to set
	 */
	public void setOptionalratePayerPhone(boolean optionalratePayerPhone) {
		this.optionalratePayerPhone = optionalratePayerPhone;
	}

	/**
	 * @return the ratePayerPhone
	 */
	public String getRatePayerPhone() {
		return ratePayerPhone;
	}

	/**
	 * @param ratePayerPhone the ratePayerPhone to set
	 */
	public void setRatePayerPhone(String ratePayerPhone) {
		this.ratePayerPhone = ratePayerPhone;
	}

	/**
	 * @return the organisationType
	 */
	public String getOrganisationType() {
		return organisationType;
	}

	/**
	 * @param organisationType the organisationType to set
	 */
	public void setOrganisationType(String organisationType) {
		this.organisationType = organisationType;
	}

	/**
	 * @return the addCharity
	 */
	public boolean isAddCharity() {
		return addCharity;
	}

	/**
	 * @param addCharity the addCharity to set
	 */
	public void setAddCharity(boolean addCharity) {
		this.addCharity = addCharity;
	}

	/**
	 * @return the ratepayerDetails
	 */
	public static String getRatepayerDetails() {
		return RATEPAYER_DETAILS;
	}

	/**
	 * @return the ratePayerAddress
	 */
	public String getRatePayerAddress() {
		return ratePayerAddress;
	}

	/**
	 * @param ratePayerAddress the ratePayerAddress to set
	 */
	public void setRatePayerAddress(String ratePayerAddress) {
		this.ratePayerAddress = ratePayerAddress;
	}

	/**
	 * @return the ratePayerPostode
	 */
	public String getRatePayerPostode() {
		return ratePayerPostode;
	}

	/**
	 * @param ratePayerPostode the ratePayerPostode to set
	 */
	public void setRatePayerPostode(String ratePayerPostode) {
		this.ratePayerPostode = ratePayerPostode;
	}

	/**
	 * @return the rATEPAYER_NAME
	 */
	public static String getRATEPAYER_NAME() {
		return RATEPAYER_NAME;
	}

	/**
	 * @param rATEPAYER_NAME the rATEPAYER_NAME to set
	 */
	public static void setRATEPAYER_NAME(String rATEPAYER_NAME) {
		RATEPAYER_NAME = rATEPAYER_NAME;
	}

	/**
	 * @return the maxAddDetails
	 */
	public static int getMaxAddDetails() {
		return MAX_ADD_DETAILS;
	}

	/**
	 * @return the ratePayerDetails
	 */
	public int getRatePayerDetails() {
		return ratePayerDetails;
	}

	/**
	 * @param ratePayerDetails the ratePayerDetails to set
	 */
	public void setRatePayerDetails(int ratePayerDetails) {
		this.ratePayerDetails = ratePayerDetails;
	}

	/**
	 * @return the namePrompt
	 */
	public static String getNamePrompt() {
		return NAME_PROMPT;
	}

	/**
	 * @return the ratePayerName
	 */
	public String getRatePayerName() {
		return ratePayerName;
	}

	/**
	 * @param ratePayerName the ratePayerName to set
	 */
	public void setRatePayerName(String ratePayerName) {
		this.ratePayerName = ratePayerName;
	}
}
