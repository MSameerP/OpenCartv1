package testBase;

import java.io.File;
import java.io.FileReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws Exception {
		
		//loading config.properties
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);
	
		//log setup
		logger = LogManager.getLogger(this.getClass());
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabalities = new DesiredCapabilities();
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				capabalities.setPlatform(Platform.WIN10);
			}
			else if(os.equalsIgnoreCase("mac")){
				capabalities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("linux")){
				capabalities.setPlatform(Platform.LINUX);
			}else {
				System.out.println("No matching OS");
			}
			
			//browser
			switch(br.toLowerCase()) {
			case "chrome": capabalities.setBrowserName("chrome");break;
			case "edge": capabalities.setBrowserName("MicrosoftEdge");break;
			case "firefox": capabalities.setBrowserName("firefox"); break;
			default: System.out.println("No matching OS...");return;
			}
			
			driver = new RemoteWebDriver(new URL("http://192.168.72.98:4444/wd/hub"),capabalities);
		}else {
			switch(br.toLowerCase())
			{
			case "chrome": driver = new ChromeDriver(); break;
			case "edge": driver = new EdgeDriver(); break;
			case "firefox": driver = new FirefoxDriver(); break;
			default: System.out.println("Invalid browser name..."); return;
			}
		}
		 
		
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); //reading url from property file
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown() {
		driver.quit();
	}
	
	public String randomString() {
		String generateString= RandomStringUtils.randomAlphabetic(5);
		return generateString;
	}
	
	public String randomNumber() {
		String generateNumber= RandomStringUtils.randomNumeric(10);
		return generateNumber;
	}
	
	public String randomAlphaNumeric() {
		String generateString= RandomStringUtils.randomAlphabetic(3);
		String generateNumber= RandomStringUtils.randomNumeric(3);
		return (generateString+"@"+generateNumber);
	}
	
	public String captureScreen(String tname) {
		
		String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timestamp+".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}

}
