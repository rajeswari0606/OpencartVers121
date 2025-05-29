package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	WebDriver driver;
	//this is the PArent of All the Page object classes.
	//Every Page object class a constructor is same.
	//instead of creating this part for every page object class
	//what i have done is created a new class called base page.
	//I defined the driver variable also have created one constructor
	
	public BasePage(WebDriver driver)
	{
		this.driver=driver;	
		PageFactory.initElements(driver,this);
		}

}
