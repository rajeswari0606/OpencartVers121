package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT  extends BaseClass{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")//getting data provide from different class
	
	public void verify_loginDDT(String email,String pwd,String exp) {
	
		logger.info("****Starting TC_003_LoginDDT****");
		try
		{
	//Homepage
	HomePage hp=new HomePage(driver);
	hp.clickMyAccount();
	hp.clickLogin();	
	
	//LoginPage
	LoginPage lp=new LoginPage(driver);
	lp.setEmail(email); //email & password we willg et from data provider
	lp.setPassword(pwd);
	lp.clickLogin();
	
	//Myaccount
	MyAccountPage mac=new MyAccountPage(driver);
	boolean targetPage=mac.isMyAccountPageExists();
		//Validation
	/*
	 * 	//we are going to check with both valid and invalid data
		Data is valid - login successful -- test  pass---logout
						login unsuccessful --fail
		*/
	if(exp.equalsIgnoreCase("Valid")) 
	{
		if(targetPage==true) {
			mac.clickLogout();
			Assert.assertTrue(true);
				}
		else
		{
			Assert.assertTrue(false);	
		}
	}
	//Data is invalid --login successful---fail----logout
 //						login unsuccessful ---test pass
	if(exp.equalsIgnoreCase("Invalid"))
	{
		if(targetPage==true) {
			mac.clickLogout();
			Assert.assertTrue(false);
				}
		else
		{
			Assert.assertTrue(true);	
		}
	}}
	catch(Exception e)
	{
		Assert.fail();
	}
	logger.info("****Finished TC_003_LoginDDT****");
		
	}
	

}
