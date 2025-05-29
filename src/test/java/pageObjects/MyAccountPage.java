package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//h2[text()='My Account']") //MyAccount Page Headin
	WebElement msgHeading;
	
	//logout - added for step 6 (3.6 Update Page Object class MyAccountPAge add logout link element)
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnkLogout;
	
	public boolean isMyAccountPageExists()
	{
		try {
	 return(msgHeading.isDisplayed());
	}catch(Exception e)
		{
		return false;
		}
	}
	
	//creating action method for logout
	public void clickLogout()
	{
		lnkLogout.click();
	}
	
	
	

}
