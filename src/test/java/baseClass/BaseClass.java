package baseClass;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.apache.logging.log4j.LogManager;//Log4j
import org.apache.logging.log4j.Logger;//Log4j


public class BaseClass {
	public static WebDriver driver;
	public Logger logger; //Log4j
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws IOException
	{
		//loading config.properties file
		FileReader file=new FileReader("./src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		//Remote execution
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities cap=new DesiredCapabilities();
			
			//we need to check OS & Browser
			//Operating System
			if(os.equalsIgnoreCase("windows"))
			{
			cap.setPlatform(Platform.WIN10); //operating system cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
			cap.setPlatform(Platform.MAC );//choose browser name cap.setBrowserName("MicrosoftEdge");
			}else
			{
				System.out.println("No matching os");
				return;
			}
			//Browser
			switch(br.toLowerCase())
			{
			case "chrome":cap.setBrowserName("Chrome");break;
			case "edge":cap.setBrowserName("MicrosoftEdge");break;
			default:System.out.println("No Matching Browser");
			return;
			}
			
			driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);

		}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		//Local host execution just execute it.
		switch(br.toLowerCase())
		{
		case"chrome" : 	driver=new ChromeDriver();break;
		case"edge" : 	driver=new EdgeDriver();break;
		case"firefox" : 	driver=new FirefoxDriver();break;
		default: System.out.println("Invalid browser name...");return;
		
		}

		
		driver.manage().deleteAllCookies(); //this will delete all the cookies
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get(p.getProperty("appURL")); //reading url from properties file
		driver.manage().window().maximize(); 
	}
	}
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	public String randomeString() //random string generation bcz every time we cannot run the test case using the same email id
	{
	String	generatedstring=RandomStringUtils.randomAlphabetic(5)  ; //this will be randomly generated only 5 will get generated u can put how much ever you want.
	return generatedstring;
		//inside this we should pass how many character should be inside string
	}
	// creating random number

	public String randomeNumber() //random string generation bcz every time we cannot run the test case using the same email id
	{
	String	generatednumber=RandomStringUtils.randomNumeric(10)  ; //this will be randomly generated only 5 will get generated u can put how much ever you want.
	return generatednumber;
		//inside this we should pass how many character should be inside string
	}
	
	//combine string and number - need random aplhanumeric combination
	public String randomeAplhaNumberic() //random string generation 
	{
	String	generatedString=RandomStringUtils.randomNumeric(3)  ; 
	String	generatednumber=RandomStringUtils.randomNumeric(10)  ;
	return (generatedString+"@"+generatednumber);
	}
	//for ExtentReport adding the captureScreen - in baseclass
	//when ever a test method fails we will execute this method
	//we will execute this from - 
	public String captureScreen(String tname) throws IOException {
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	    
	    TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	    
	    String screenshotDir = System.getProperty("user.dir") + "\\screenshots\\";
	    new File(screenshotDir).mkdirs();
	    
	    String targetFilePath = screenshotDir + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);
	    
	    FileUtils.copyFile(sourceFile, targetFile);
	    return targetFilePath;
	}

}
