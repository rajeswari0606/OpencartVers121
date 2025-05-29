package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"sanity","Master"})
	public void verify_login()
	{
		logger.info("****Starting TC_002_LoginTest****");
		
		try{
		//Homepage
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();	
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email")); //we should no hard code anything we are calling property file config .property which we already created.
		lp.setPassword(p.getProperty("password"));
		
		lp.clickLogin();
		
		//Myaccount
		MyAccountPage mac=new MyAccountPage(driver);
		boolean targetPage=mac.isMyAccountPageExists();
		
		//Validation
		Assert.assertEquals(targetPage, true,"Login failed");
		//Assert.assertTrue(targetPage);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****Finished TC_002_LoginTest****");
		
	}
		
	
	
	
	

}
