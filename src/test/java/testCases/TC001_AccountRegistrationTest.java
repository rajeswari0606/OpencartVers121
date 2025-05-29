package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class TC001_AccountRegistrationTest extends BaseClass{
	//every class is 1 test case
	//we are going to access in multiple package so keep it public
	

	
	@Test (groups={"Regression","Master"})//this is actual test method
	public void verify_account_registration ()
	{
		logger.info("***** Starting TC001_AccountRegistrationTest  *****");
		try {
		HomePage hp = new HomePage(driver); //we are creating obj for the HomePage
		
		hp.clickMyAccount();
		logger.info("Clicked on My Account Link ");
		
		hp.clickRegister();
		logger.info("Clicked on My Register Link ");
		
		AccountRegistrationPage regpage=new AccountRegistrationPage(driver);
		logger.info("Providing Customer Details... ");
		regpage.setFirstName(randomeString().toUpperCase()); //name should starts in uppercase character
		regpage.setLastName(randomeString().toUpperCase());
		//randomly generated the email
		regpage.setEmail(randomeString()+"@gmail.com");//same email id can't be passed all the time
		regpage.setTelephone(randomeNumber());
		
	String password =randomeAplhaNumberic(); //calling only once randomealphanumberic and store the value as password 
		
		regpage.setPassword(password); //same value we are passing it.
		regpage.setConfirmPassword(password);
		
		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		//Validation
		logger.info("Validating Excepted Message...");
		String confmsg=regpage.getConfirmationMsg();
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
	catch(Exception e)
	{
	logger.error("Test failed...");
	logger.debug("Debug logs....");
	Assert.fail();
	}
		logger.info("*****Finish TC001_AccountRegistrationTest  *****");
	}	
}
	

