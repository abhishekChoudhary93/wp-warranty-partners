package com.diakomio.util;

public class StringUtil {
	public static final String mailFooter = "\n\nThanks\nTeam WP";
	public static final String adminEmail = "warrantypartners@gmail.com";
	public static final String newUserSubject = "Welcome to Warranty Partners, {user}";
	public static final String newCustomerBody = "Welcome Partner!\n\nYou have taken the first step towards having peace about your new purchase…for years. \n\nOur goal is to give you worry-free and hassle-free service for the duration of your new purchase’s life. You are now registered with us and can purchase the warranty best suited for you."
			+ mailFooter;
	public static final String activateDealerSubjectToAdmin = "User activation required for {user}";
	public static final String activateDealerBody = "Welcome Partner!\n\nYour Registration should be activated soon and we will eMail you when it is complete with next steps. \n\nThanks for working with Warranty Partners to provide the best in Extended Warranties and service!"
			+ mailFooter;
	public static final String newWarrantyAddedSubject = "New Warranty Added";
	public static final String newClaimAddedSubject = "New Claim Filed";
	public static final String newWarrantyAddedBody = "Your Warranty is now complete! \n\nPlease file this email for future use as it contains all necessary information to file a claim. Be assured that if you do need to make a claim we stand ready to make it easy to activate your warranty.\n\nWarranty ID: {warrantyId}\nDealer: {dealer}\nProduct Model Number: {productModelNumber}\nProduct Serial Number: {productSerialNumber}"
			+ mailFooter;
	public static final String newClaimAddedBody = "Your warranty claim is now filed on the name of {name} and you are now in the competent hands of our Claims Department. You are able to check on your claim status at any time by Logging In with your registration information. We’re closer to a resolution already!"
			+ mailFooter;
	public static final String activateDealerBodyAdmin = "Activation required for dealer. Please click the link below to activate! \n http://162.217.249.119:8080/WarrantyPartnersComplete/validateDealer?username={username}";
	public static final String activatedDealerSubject = "Account is Activated";
	public static final String activatedDealerBody = "Your account with Username: {username} is now activated!"
			+ mailFooter;
	public static final String newWarrantyAddedBodyForDealer = "A warranty has been registered. Details: \n\nWarranty ID: {warrantyId}\nDealer: {dealer}\nProduct Model Number: {productModelNumber}\nProduct Serial Number: {productSerialNumber}"
			+ mailFooter;

}
