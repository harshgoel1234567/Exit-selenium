package resources;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeOptions;
import java.io.*;
import java.time.Duration;
import java.util.*;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


import io.github.bonigarcia.wdm.WebDriverManager;
public class Base {
	WebDriver d;
	public Properties prop;
	
	public WebDriver initializedriver() throws FileNotFoundException,IOException
	{
		 prop = new Properties();
	        String proPath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\data.properties";
	        FileInputStream fis = new FileInputStream(proPath);
	        prop.load(fis);
		
		  String browserName = prop.getProperty("browser");
	        boolean headless = Boolean.parseBoolean(prop.getProperty("headless"));
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();

			String userProfilePath = "C:\\Users\\harshgoel\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1";
			 ChromeOptions options = new ChromeOptions();
	            if (headless) {
	                options.addArguments("--headless"); // Enable headless mode
	                options.addArguments("--no-sandbox");
	                options.addArguments("--window-size=1920,1080"); // Set window size for headless mode
	                options.addArguments("--disable-gpu"); // Disable GPU acceleration

	                options.addArguments("--disable-dev-shm-usage");
	            }

	      
	         d = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 FirefoxOptions options = new FirefoxOptions();
	            if (headless) {
	                options.addArguments("--headless"); // Enable headless mode
	            }
			 d= new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			WebDriverManager.edgedriver().setup();
		    d = new InternetExplorerDriver();
		    
		}
		
		
		
		d.manage().window().maximize();
		d.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return d;
	}
	
	public  String takeScreenShot(String Name,WebDriver driver) throws IOException
	{
		File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		
		String destinationpath=System.getProperty("user.dir")+"\\screenshots\\"+Name+".png";
        FileUtils.copyFile(screenshotFile, new File(destinationpath));
        
        return destinationpath;
	}

}
