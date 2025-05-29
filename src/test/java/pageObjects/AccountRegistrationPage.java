package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountRegistrationPage extends BasePage{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstname;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastname;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtTelephone;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtConfirmPassword;


@FindBy(xpath="//input[@name='agree']")
WebElement chkdPolicy;

@FindBy(xpath="//input[@value='Continue']")
WebElement btnContinue;


@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
WebElement msgConfirmation;

public void setFirstName(String fname) 
{
	txtFirstname.sendKeys(fname);
}
public void setLastName(String lname) 
{
	txtLastname.sendKeys(lname);
}
public void setEmail(String email) 
{
	txtEmail.sendKeys(email);
}
public void setTelephone(String Tel) 
{
	txtTelephone.sendKeys(Tel);
}
public void setPassword(String pwd) 
{
	txtPassword.sendKeys(pwd);
}
public void setConfirmPassword(String pwd) 
{
	txtConfirmPassword.sendKeys(pwd);
}
public void setPrivacyPolicy() 
{
	chkdPolicy.click();
}
public void clickContinue()
{//solution 1:
	btnContinue.click(); //if click is not working then try the below alternative solutions.

//solution 2:
//btnContinue.submit();
//solution 3:
//Actions act=new Actions(driver);
//act.moveToElements(btnContinue.click().perform();
//solution 4 :
//btnContinue.sendKeys(Keys.RETURN);
//Solution 5:
//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
//mywait.util(ExceptedConditions.elementToBeClickable(btnContinue)).click();
}

public String getConfirmationMsg()
{
	try {
		return(msgConfirmation.getText());	//this will not do any validation it will just capture the text value of the confirmation message and returning the message.
	}catch(Exception e) {
		return(e.getMessage()); 
	}
}
}
