package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage //created parent class constructor in base page.
{
	
	//this is child class invoke parent class constructor (inheritance)
	public HomePage(WebDriver driver)
	{
		super(driver);
	}//instead of writing constructor in all the page object class I separated the constructor in another class that is extended into this constructor.
	//Pageobject class always have 3 parts Constructor,locator and action methods
	
@FindBy(xpath="//span[normalize-space()='My Account']")
WebElement lnkMyaccount;

@FindBy(xpath="//a[normalize-space()='Register']")
WebElement lnkRegister;

@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']") //Login link added in steps
WebElement lnkLogin;

public void clickMyAccount()
{
	lnkMyaccount.click();
}
public void clickRegister()
{
	lnkRegister.click();
}
public void clickLogin()
{
	lnkLogin.click();
}
}
