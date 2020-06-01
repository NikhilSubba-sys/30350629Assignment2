package operation;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author 30350629
 * @version 1.0
 * @created 26-March-2020 10:20:00am
 */
public class AddProperty extends FunctionalDialog {
	
	private static final int RESIDENTIAL = 1, COMMERCIAL = 2, VACANT_LAND = 3, HOSPITAL = 4, INDUSTRIAL = 5,
			SCHOOL_COMMUNITY = 6, OTHER = 7, END = 0;
	private static final int MAX_PROPERTY_TYPES = 7;
	private static final String PROPERTY_TYPE_PROMPT = "What type of property would you like to add? \n" + RESIDENTIAL
			+ ". Residential \n" + COMMERCIAL + ". Commercial \n" + VACANT_LAND + ". Vacant Land \n" + HOSPITAL
			+ ". Hospital \n" + INDUSTRIAL + ". Industrial \n" + SCHOOL_COMMUNITY + ". School/Community \n" + OTHER
			+ ". Other \n" + END + ".  To exit";
	private int propertyType;
	// common property attribute - property description
	private String description;
	private static final String DESCRIPTION_PROMPT = "Enter the description? "; // ask for the description input
	private static String PROPERTY_DESCRIPTION;
	// property loccation
	private String location;
	private static final String LOCATION_PROMPT = "What is the address of the property? ";
	private static String PROPERTY_LOCATION;
	// property area	
	private double area;
	private static final String AREA_PROMPT = "What is the area per square meter of the property? ";
	private static double AREA_PER_SQM;
	// property site value
	private double siteValue;
	private static final String SITEVALUE_PROMPT = "Would is the site value of the property ?";
	private static final double MAX_SITEVALUE = 50000000.00;
	// property capital improved value
	private double capitalImprovedValue;
	private static final String CIV_VALUE_PROMPT = "What is the capital improved value of the property? ";
	private static final double MAX_CIV_VALUE = 50000000.00;
	private static final NumberFormat MYFORMAT = NumberFormat.getNumberInstance();
	// property capital improved rate
	private static final double COMMERCIAL_CIVRATE = 0.0059;
	private static final double INDUSTRIAL_CIVRATE = 0.0059;
	private static final double RESIDENTIAL_CIVRATE = 0.0039;
	private static final double HOSPITAL_CIVRATE = 0.0035;
	private static final double SCHOOLCOMMUNITY_CIVRATE = 0.0025;
	private static final double OTHER_CIVRATE = 0.0030;
	private static final double VACANTLAND_CIVRATE = 0.0020;	
	// net annual value
	private double NetAnnualValue;
	private static final String NETANNUALVALUE_PROMPT = "What is the net annual value of the property? ";
	private static final double MAX_NETANNUALVALUE = 10000000.00;	
	// valuation date
	private String valuationDate;
	private static final String VALUATIONDATE_PROMPT = "Enter the valuation date (dd-mm-yyyy) :  "; // ask for the description input
	private static String VALUATION_DATE;
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	// rartepayer - owner 
	private String ownername;
	private static final String OWNERNAME_PROMPT = "What is the owner's name? "; // ask for the owner's name input
	private static String OWNER_NAME;
	
	// residential property
	private String propertyStyle;
	private static final String PROPERTYSTYLE_PROMPT = "What type of property are we dealing with? (house, townhouse...) "; // ask for the description input
	private static String PROPERTY_STYLE;
	private String architecturalStyle;
	private static final String ARCHITECTURAL_PROMPT = "What type of architectual style has the property? (Modern, Federation...) "; // ask for the description input
	private static String ARCHITECTURAL_STYLE;
	// commercial property
	private String businessName;
	private static final String BUSINESSNAME_PROMPT = "What is the name of the business? "; // ask for the business name input
	private static String BUSINESS_NAME;
	private String businessABN;
	private static final String BUSINESSABN_PROMPT = "Please enter the Businness ABN number: "; // ask for the ABN input
	private static String BUSINESS_ABN;
	// industrial property
	private String hazardStatus;
	private static final String HAZARDSTATUS_PROMPT = "Please enter the hazard level of the property:  "; // ask for the ABN input
	private static String HAZARD_STATUS;
	private static final String HEAVYVEHICLESTATUS_PROMPT = "Does the rate payer have a charity status? ";
	private boolean heavyVehicleStatus;
	// hospital
	private static final String PUBLIC_PRIVATE_STATUS_PROMPT = "Is this a public hospital? ";
	private boolean publicPrivateStatus;
	private String facilities;
	private static final String FACILITIES_PROMPT = "Please enter the hazard level of the property:  "; // ask for the ABN input
	private static String FACILITIES;
	private int numFloors;
	private static final int MAX_NUMFLOORS = 3;
	private static final String NUMFLOORS_PROMPT = "How many floors does the hospital have? \n";
	// for school community categories
	private String classification;
	private static final String CLASSIFICATION_PROMPT = "Please enter the hazard level of the property:  "; // ask for the ABN input
	private static String CLASSIFICATION;
	private String categorySize;
	private static final String CATEGORYSIZE_PROMPT = "Please enter the hazard level of the property:  "; // ask for the ABN input
	private static String CATEGORY_SIZE;
	// category size selection
	private static final int SMALL = 1, MEDIUM = 2, LARGE = 3;
	private static final int MAX_COMMUNITY_CATEGORIES = 3;
	private static final String COMMUNITY_CATEGORY_PROMPT = "What type of category is the school or community building? \n"
			+ SMALL + ". Small (1-20 members) \n" + MEDIUM + ". Medium (21-100) \n" + LARGE
			+ ". Large (101 upwards) \n";
	private int communityCategory;
	// other property
	private String specialDescription;
	private static final String SPECIALDESCRIPTION_PROMPT = "Please enter the hazard level of the property:  "; // ask for the ABN input
	private static String SPECIAL_DESCRIPTION;
	// vacant land 
	private String[] overlays;
	private static final String OVERLAYS_PROMPT = "Please enter the hazard level of the property:  "; // ask for the ABN input
	private static String OVERLAYS;
	// maximum number of user inputs
	private static final int MAX_NO_USER_INPUTS = 7;
	
	// scanner function
	public AddProperty(Scanner console) {
		super(MAX_NO_USER_INPUTS, console);
	}

	@Override
	public void obtainInput(int i) {
		// add property logic and layout here
		switch (i) {
		case 0:
			propertyType = obtainIntInput(MAX_PROPERTY_TYPES, PROPERTY_TYPE_PROMPT);
			if (propertyType == END)
				setStillRunning(false);
			break;
		case 1:
			description = obtainStringInput(PROPERTY_DESCRIPTION, DESCRIPTION_PROMPT);
			break;
		case 2:
			location = obtainStringInput(PROPERTY_LOCATION, LOCATION_PROMPT);		
			break;
		case 3:
			area = obtainDoubleInput(AREA_PER_SQM, AREA_PROMPT);
			break;
		case 4:
			siteValue = obtainDoubleInput(MAX_SITEVALUE, SITEVALUE_PROMPT);
			break;
		case 5: 
			capitalImprovedValue = obtainDoubleInput(MAX_CIV_VALUE, CIV_VALUE_PROMPT);
			break;
		case 6: 
			NetAnnualValue = obtainDoubleInput(MAX_NETANNUALVALUE, NETANNUALVALUE_PROMPT);
			break;
		case 7: 
			valuationDate = obtainStringInput(VALUATION_DATE, VALUATIONDATE_PROMPT); 
			break;
		case 8: 
			ownername = obtainStringInput(OWNER_NAME, OWNERNAME_PROMPT); 
			break;
		case 9: 
			// residential property
			if (propertyType == RESIDENTIAL)
			propertyStyle = obtainStringInput(PROPERTY_STYLE, PROPERTYSTYLE_PROMPT);
			architecturalStyle = obtainStringInput(ARCHITECTURAL_STYLE, ARCHITECTURAL_PROMPT);
			break;
		case 10: 
			// commercial property
			if (propertyType == COMMERCIAL)
			businessName = obtainStringInput(BUSINESS_NAME, BUSINESSNAME_PROMPT);
			businessABN = obtainStringInput(BUSINESS_ABN, BUSINESSABN_PROMPT);
			break;
			// vacant land
		case 15: 
			if (propertyType == SCHOOL_COMMUNITY)
				setCommunityCategory(obtainIntInput(MAX_COMMUNITY_CATEGORIES, COMMUNITY_CATEGORY_PROMPT));
			break;
		}
	}	
	
	// string input
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
	
	// integer input 
	private int obtainIntInput(int max, String prompt) {
		System.out.println(prompt);
		return validateInt(0, max);
	}

	// double input
	private double obtainDoubleInput(double max, String prompt) {
		System.out.println(prompt);
		return validateDouble(100.00, max);
	}

	// boolean input
	private boolean obtainBooleanInput(String prompt) {
		System.out.println(prompt);
		return validateBoolean();
	}

	// validate user input
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

	// validate user input is between min zero (0) and max
	private double validateDouble(double min, double max) {
		double userInput;
		do {
			MYFORMAT.setMinimumFractionDigits(2);
			MYFORMAT.setMaximumFractionDigits(2);
			System.out.print("Enter a selection (" + MYFORMAT.format(min) + "-" + MYFORMAT.format(max) + "):");

			if (!getScanner().hasNextDouble())
				userInput = max + 0.01;
			else
				userInput = getScanner().nextDouble(); // obtain the input
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
		getScanner().nextLine(); // add another input line after enter kkey pressed
		System.out.println(); // put a space before the next output
		return userInput;

	}


	@Override
	public void respondToInput() {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the propertyType
	 */
	public int getPropertyType() {
		return propertyType;
	}

	/**
	 * @param propertyType the propertyType to set
	 */
	public void setPropertyType(int propertyType) {
		this.propertyType = propertyType;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the pROPERTY_DESCRIPTION
	 */
	public static String getPROPERTY_DESCRIPTION() {
		return PROPERTY_DESCRIPTION;
	}

	/**
	 * @param pROPERTY_DESCRIPTION the pROPERTY_DESCRIPTION to set
	 */
	public static void setPROPERTY_DESCRIPTION(String pROPERTY_DESCRIPTION) {
		PROPERTY_DESCRIPTION = pROPERTY_DESCRIPTION;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the pROPERTY_LOCATION
	 */
	public static String getPROPERTY_LOCATION() {
		return PROPERTY_LOCATION;
	}

	/**
	 * @param pROPERTY_LOCATION the pROPERTY_LOCATION to set
	 */
	public static void setPROPERTY_LOCATION(String pROPERTY_LOCATION) {
		PROPERTY_LOCATION = pROPERTY_LOCATION;
	}

	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(double area) {
		this.area = area;
	}

	/**
	 * @return the aREA_PER_SQM
	 */
	public static double getAREA_PER_SQM() {
		return AREA_PER_SQM;
	}

	/**
	 * @param aREA_PER_SQM the aREA_PER_SQM to set
	 */
	public static void setAREA_PER_SQM(double aREA_PER_SQM) {
		AREA_PER_SQM = aREA_PER_SQM;
	}

	/**
	 * @return the siteValue
	 */
	public double getSiteValue() {
		return siteValue;
	}

	/**
	 * @param siteValue the siteValue to set
	 */
	public void setSiteValue(double siteValue) {
		this.siteValue = siteValue;
	}

	/**
	 * @return the capitalImprovedValue
	 */
	public double getCapitalImprovedValue() {
		return capitalImprovedValue;
	}

	/**
	 * @param capitalImprovedValue the capitalImprovedValue to set
	 */
	public void setCapitalImprovedValue(double capitalImprovedValue) {
		this.capitalImprovedValue = capitalImprovedValue;
	}

	/**
	 * @return the netAnnualValue
	 */
	public double getNetAnnualValue() {
		return NetAnnualValue;
	}

	/**
	 * @param netAnnualValue the netAnnualValue to set
	 */
	public void setNetAnnualValue(double netAnnualValue) {
		NetAnnualValue = netAnnualValue;
	}

	/**
	 * @return the valuationDate
	 */
	public String getValuationDate() {
		return valuationDate;
	}

	/**
	 * @param valuationDate the valuationDate to set
	 */
	public void setValuationDate(String valuationDate) {
		this.valuationDate = valuationDate;
	}

	/**
	 * @return the vALUATION_DATE
	 */
	public static String getVALUATION_DATE() {
		return VALUATION_DATE;
	}

	/**
	 * @param vALUATION_DATE the vALUATION_DATE to set
	 */
	public static void setVALUATION_DATE(String vALUATION_DATE) {
		VALUATION_DATE = vALUATION_DATE;
	}

	/**
	 * @return the ownername
	 */
	public String getOwnername() {
		return ownername;
	}

	/**
	 * @param ownername the ownername to set
	 */
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	/**
	 * @return the oWNER_NAME
	 */
	public static String getOWNER_NAME() {
		return OWNER_NAME;
	}

	/**
	 * @param oWNER_NAME the oWNER_NAME to set
	 */
	public static void setOWNER_NAME(String oWNER_NAME) {
		OWNER_NAME = oWNER_NAME;
	}

	/**
	 * @return the propertyStyle
	 */
	public String getPropertyStyle() {
		return propertyStyle;
	}

	/**
	 * @param propertyStyle the propertyStyle to set
	 */
	public void setPropertyStyle(String propertyStyle) {
		this.propertyStyle = propertyStyle;
	}

	public static String getPROPERTY_STYLE() {
		return PROPERTY_STYLE;
	}

	public static void setPROPERTY_STYLE(String pROPERTY_STYLE) {
		PROPERTY_STYLE = pROPERTY_STYLE;
	}

	/**
	 * @return the architecturalStyle
	 */
	public String getArchitecturalStyle() {
		return architecturalStyle;
	}

	/**
	 * @param architecturalStyle the architecturalStyle to set
	 */
	public void setArchitecturalStyle(String architecturalStyle) {
		this.architecturalStyle = architecturalStyle;
	}

	/**
	 * @return the aRCHITECTURAL_STYLE
	 */
	public static String getARCHITECTURAL_STYLE() {
		return ARCHITECTURAL_STYLE;
	}

	/**
	 * @param aRCHITECTURAL_STYLE the aRCHITECTURAL_STYLE to set
	 */
	public static void setARCHITECTURAL_STYLE(String aRCHITECTURAL_STYLE) {
		ARCHITECTURAL_STYLE = aRCHITECTURAL_STYLE;
	}

	/**
	 * @return the businessName
	 */
	public String getBusinessName() {
		return businessName;
	}

	/**
	 * @param businessName the businessName to set
	 */
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	/**
	 * @return the bUSINESS_NAME
	 */
	public static String getBUSINESS_NAME() {
		return BUSINESS_NAME;
	}

	/**
	 * @param bUSINESS_NAME the bUSINESS_NAME to set
	 */
	public static void setBUSINESS_NAME(String bUSINESS_NAME) {
		BUSINESS_NAME = bUSINESS_NAME;
	}

	/**
	 * @return the businessABN
	 */
	public String getBusinessABN() {
		return businessABN;
	}

	/**
	 * @param businessABN the businessABN to set
	 */
	public void setBusinessABN(String businessABN) {
		this.businessABN = businessABN;
	}

	/**
	 * @return the bUSINESS_ABN
	 */
	public static String getBUSINESS_ABN() {
		return BUSINESS_ABN;
	}

	/**
	 * @param bUSINESS_ABN the bUSINESS_ABN to set
	 */
	public static void setBUSINESS_ABN(String bUSINESS_ABN) {
		BUSINESS_ABN = bUSINESS_ABN;
	}

	/**
	 * @return the hazardStatus
	 */
	public String getHazardStatus() {
		return hazardStatus;
	}

	/**
	 * @param hazardStatus the hazardStatus to set
	 */
	public void setHazardStatus(String hazardStatus) {
		this.hazardStatus = hazardStatus;
	}

	/**
	 * @return the hAZARD_STATUS
	 */
	public static String getHAZARD_STATUS() {
		return HAZARD_STATUS;
	}

	/**
	 * @param hAZARD_STATUS the hAZARD_STATUS to set
	 */
	public static void setHAZARD_STATUS(String hAZARD_STATUS) {
		HAZARD_STATUS = hAZARD_STATUS;
	}

	/**
	 * @return the heavyVehicleStatus
	 */
	public boolean isHeavyVehicleStatus() {
		return heavyVehicleStatus;
	}

	/**
	 * @param heavyVehicleStatus the heavyVehicleStatus to set
	 */
	public void setHeavyVehicleStatus(boolean heavyVehicleStatus) {
		this.heavyVehicleStatus = heavyVehicleStatus;
	}

	/**
	 * @return the publicPrivateStatus
	 */
	public boolean isPublicPrivateStatus() {
		return publicPrivateStatus;
	}

	/**
	 * @param publicPrivateStatus the publicPrivateStatus to set
	 */
	public void setPublicPrivateStatus(boolean publicPrivateStatus) {
		this.publicPrivateStatus = publicPrivateStatus;
	}

	/**
	 * @return the facilities
	 */
	public String getFacilities() {
		return facilities;
	}

	/**
	 * @param facilities the facilities to set
	 */
	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	/**
	 * @return the fACILITIES
	 */
	public static String getFACILITIES() {
		return FACILITIES;
	}

	/**
	 * @param fACILITIES the fACILITIES to set
	 */
	public static void setFACILITIES(String fACILITIES) {
		FACILITIES = fACILITIES;
	}

	/**
	 * @return the numFloors
	 */
	public int getNumFloors() {
		return numFloors;
	}

	/**
	 * @param numFloors the numFloors to set
	 */
	public void setNumFloors(int numFloors) {
		this.numFloors = numFloors;
	}

	/**
	 * @return the classification
	 */
	public String getClassification() {
		return classification;
	}

	/**
	 * @param classification the classification to set
	 */
	public void setClassification(String classification) {
		this.classification = classification;
	}

	/**
	 * @return the cLASSIFICATION
	 */
	public static String getCLASSIFICATION() {
		return CLASSIFICATION;
	}

	/**
	 * @param cLASSIFICATION the cLASSIFICATION to set
	 */
	public static void setCLASSIFICATION(String cLASSIFICATION) {
		CLASSIFICATION = cLASSIFICATION;
	}

	/**
	 * @return the categorySize
	 */
	public String getCategorySize() {
		return categorySize;
	}

	/**
	 * @param categorySize the categorySize to set
	 */
	public void setCategorySize(String categorySize) {
		this.categorySize = categorySize;
	}

	/**
	 * @return the cATEGORY_SIZE
	 */
	public static String getCATEGORY_SIZE() {
		return CATEGORY_SIZE;
	}

	/**
	 * @param cATEGORY_SIZE the cATEGORY_SIZE to set
	 */
	public static void setCATEGORY_SIZE(String cATEGORY_SIZE) {
		CATEGORY_SIZE = cATEGORY_SIZE;
	}

	/**
	 * @return the communityCategory
	 */
	public int getCommunityCategory() {
		return communityCategory;
	}

	/**
	 * @param communityCategory the communityCategory to set
	 */
	public void setCommunityCategory(int communityCategory) {
		this.communityCategory = communityCategory;
	}

	/**
	 * @return the specialDescription
	 */
	public String getSpecialDescription() {
		return specialDescription;
	}

	/**
	 * @param specialDescription the specialDescription to set
	 */
	public void setSpecialDescription(String specialDescription) {
		this.specialDescription = specialDescription;
	}

	/**
	 * @return the sPECIAL_DESCRIPTION
	 */
	public static String getSPECIAL_DESCRIPTION() {
		return SPECIAL_DESCRIPTION;
	}

	/**
	 * @param sPECIAL_DESCRIPTION the sPECIAL_DESCRIPTION to set
	 */
	public static void setSPECIAL_DESCRIPTION(String sPECIAL_DESCRIPTION) {
		SPECIAL_DESCRIPTION = sPECIAL_DESCRIPTION;
	}

	/**
	 * @return the overlays
	 */
	public String[] getOverlays() {
		return overlays;
	}

	/**
	 * @param overlays the overlays to set
	 */
	public void setOverlays(String[] overlays) {
		this.overlays = overlays;
	}

	/**
	 * @return the oVERLAYS
	 */
	public static String getOVERLAYS() {
		return OVERLAYS;
	}

	/**
	 * @param oVERLAYS the oVERLAYS to set
	 */
	public static void setOVERLAYS(String oVERLAYS) {
		OVERLAYS = oVERLAYS;
	}

	/**
	 * @return the residential
	 */
	public static int getResidential() {
		return RESIDENTIAL;
	}

	/**
	 * @return the commercial
	 */
	public static int getCommercial() {
		return COMMERCIAL;
	}

	/**
	 * @return the vacantLand
	 */
	public static int getVacantLand() {
		return VACANT_LAND;
	}

	/**
	 * @return the hospital
	 */
	public static int getHospital() {
		return HOSPITAL;
	}

	/**
	 * @return the industrial
	 */
	public static int getIndustrial() {
		return INDUSTRIAL;
	}

	/**
	 * @return the schoolCommunity
	 */
	public static int getSchoolCommunity() {
		return SCHOOL_COMMUNITY;
	}

	/**
	 * @return the other
	 */
	public static int getOther() {
		return OTHER;
	}

	/**
	 * @return the end
	 */
	public static int getEnd() {
		return END;
	}

	/**
	 * @return the maxPropertyTypes
	 */
	public static int getMaxPropertyTypes() {
		return MAX_PROPERTY_TYPES;
	}

	/**
	 * @return the propertyTypePrompt
	 */
	public static String getPropertyTypePrompt() {
		return PROPERTY_TYPE_PROMPT;
	}

	/**
	 * @return the descriptionPrompt
	 */
	public static String getDescriptionPrompt() {
		return DESCRIPTION_PROMPT;
	}

	/**
	 * @return the locationPrompt
	 */
	public static String getLocationPrompt() {
		return LOCATION_PROMPT;
	}

	/**
	 * @return the areaPrompt
	 */
	public static String getAreaPrompt() {
		return AREA_PROMPT;
	}

	/**
	 * @return the sitevaluePrompt
	 */
	public static String getSitevaluePrompt() {
		return SITEVALUE_PROMPT;
	}

	/**
	 * @return the maxSitevalue
	 */
	public static double getMaxSitevalue() {
		return MAX_SITEVALUE;
	}

	/**
	 * @return the civValuePrompt
	 */
	public static String getCivValuePrompt() {
		return CIV_VALUE_PROMPT;
	}

	/**
	 * @return the maxCivValue
	 */
	public static double getMaxCivValue() {
		return MAX_CIV_VALUE;
	}

	/**
	 * @return the myformat
	 */
	public static NumberFormat getMyformat() {
		return MYFORMAT;
	}

	/**
	 * @return the commercialCivrate
	 */
	public static double getCommercialCivrate() {
		return COMMERCIAL_CIVRATE;
	}

	/**
	 * @return the industrialCivrate
	 */
	public static double getIndustrialCivrate() {
		return INDUSTRIAL_CIVRATE;
	}

	/**
	 * @return the residentialCivrate
	 */
	public static double getResidentialCivrate() {
		return RESIDENTIAL_CIVRATE;
	}

	/**
	 * @return the hospitalCivrate
	 */
	public static double getHospitalCivrate() {
		return HOSPITAL_CIVRATE;
	}

	/**
	 * @return the schoolcommunityCivrate
	 */
	public static double getSchoolcommunityCivrate() {
		return SCHOOLCOMMUNITY_CIVRATE;
	}

	/**
	 * @return the otherCivrate
	 */
	public static double getOtherCivrate() {
		return OTHER_CIVRATE;
	}

	/**
	 * @return the vacantlandCivrate
	 */
	public static double getVacantlandCivrate() {
		return VACANTLAND_CIVRATE;
	}

	/**
	 * @return the netannualvaluePrompt
	 */
	public static String getNetannualvaluePrompt() {
		return NETANNUALVALUE_PROMPT;
	}

	/**
	 * @return the maxNetannualvalue
	 */
	public static double getMaxNetannualvalue() {
		return MAX_NETANNUALVALUE;
	}

	/**
	 * @return the valuationdatePrompt
	 */
	public static String getValuationdatePrompt() {
		return VALUATIONDATE_PROMPT;
	}

	/**
	 * @return the formatter
	 */
	public static DateTimeFormatter getFormatter() {
		return FORMATTER;
	}

	/**
	 * @return the ownernamePrompt
	 */
	public static String getOwnernamePrompt() {
		return OWNERNAME_PROMPT;
	}

	/**
	 * @return the propertystylePrompt
	 */
	public static String getPropertystylePrompt() {
		return PROPERTYSTYLE_PROMPT;
	}

	/**
	 * @return the architecturalPrompt
	 */
	public static String getArchitecturalPrompt() {
		return ARCHITECTURAL_PROMPT;
	}

	/**
	 * @return the businessnamePrompt
	 */
	public static String getBusinessnamePrompt() {
		return BUSINESSNAME_PROMPT;
	}

	/**
	 * @return the businessabnPrompt
	 */
	public static String getBusinessabnPrompt() {
		return BUSINESSABN_PROMPT;
	}

	/**
	 * @return the hazardstatusPrompt
	 */
	public static String getHazardstatusPrompt() {
		return HAZARDSTATUS_PROMPT;
	}

	/**
	 * @return the heavyvehiclestatusPrompt
	 */
	public static String getHeavyvehiclestatusPrompt() {
		return HEAVYVEHICLESTATUS_PROMPT;
	}

	/**
	 * @return the publicPrivateStatusPrompt
	 */
	public static String getPublicPrivateStatusPrompt() {
		return PUBLIC_PRIVATE_STATUS_PROMPT;
	}

	/**
	 * @return the facilitiesPrompt
	 */
	public static String getFacilitiesPrompt() {
		return FACILITIES_PROMPT;
	}

	/**
	 * @return the maxNumfloors
	 */
	public static int getMaxNumfloors() {
		return MAX_NUMFLOORS;
	}

	/**
	 * @return the numfloorsPrompt
	 */
	public static String getNumfloorsPrompt() {
		return NUMFLOORS_PROMPT;
	}

	/**
	 * @return the classificationPrompt
	 */
	public static String getClassificationPrompt() {
		return CLASSIFICATION_PROMPT;
	}

	/**
	 * @return the categorysizePrompt
	 */
	public static String getCategorysizePrompt() {
		return CATEGORYSIZE_PROMPT;
	}

	/**
	 * @return the small
	 */
	public static int getSmall() {
		return SMALL;
	}

	/**
	 * @return the medium
	 */
	public static int getMedium() {
		return MEDIUM;
	}

	/**
	 * @return the large
	 */
	public static int getLarge() {
		return LARGE;
	}

	/**
	 * @return the maxCommunityCategories
	 */
	public static int getMaxCommunityCategories() {
		return MAX_COMMUNITY_CATEGORIES;
	}

	/**
	 * @return the communityCategoryPrompt
	 */
	public static String getCommunityCategoryPrompt() {
		return COMMUNITY_CATEGORY_PROMPT;
	}

	/**
	 * @return the specialdescriptionPrompt
	 */
	public static String getSpecialdescriptionPrompt() {
		return SPECIALDESCRIPTION_PROMPT;
	}

	/**
	 * @return the overlaysPrompt
	 */
	public static String getOverlaysPrompt() {
		return OVERLAYS_PROMPT;
	}

	/**
	 * @return the maxNoUserInputs
	 */
	public static int getMaxNoUserInputs() {
		return MAX_NO_USER_INPUTS;
	}

	
	
}
