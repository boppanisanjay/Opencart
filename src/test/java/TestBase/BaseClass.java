package TestBase;

import java.io.FileReader;
import java.io.File;
import java.nio.file.Files;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.nio.file.StandardCopyOption;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;

public class BaseClass {
	public static WebDriver driver;
	public Logger logger;  //log4j
	public Properties p;

	@BeforeClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
	@Parameters({"os","browser" })
	public void openbrowser(String os, String br) throws IOException {

		//loading config.properties file
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(file);

		logger = LogManager.getLogger(this.getClass());

		logger.info("Test execution started on OS: " + os + " with Browser: " + br);
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			/*
			 * below code is added to avoid (Apr 03, 2025 5:28:52 PM org.openqa.selenium.remote.tracing.opentelemetry.OpenTelemetryTracer createTracer
				INFO: Using OpenTelemetry for tracing)
			 * System.setProperty("seleniumManagerTracing", "false");
			 * */
			
			 //System.setProperty("seleniumManagerTracing", "false");
			 
			DesiredCapabilities capabilities = new DesiredCapabilities();
			//capabilities.setCapability("se:tracing", "OFF");
			if(os.equalsIgnoreCase("windows")) {
				capabilities.setPlatform(Platform.WIN11);
			}
			/*else if(os.equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			}
			else {
				logger.info("***No matching os***");
				System.out.println("No matching os");
			}*/
			
			if(br.equalsIgnoreCase("chrome")) {
				capabilities.setBrowserName("chrome");
			}
			else if(br.equalsIgnoreCase("edge")) {
				capabilities.setBrowserName("MicrosoftEdge");
				//capabilities.setBrowserName("edge");
			}
			else if(br.equalsIgnoreCase("firefox")) {
				capabilities.setBrowserName("Firefox");
			}
			else {
				System.out.println("No matching browser");
				logger.info("******No matching browser******");
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
			
		}
			
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) 
		{
			switch(br.toLowerCase()) {
			case "chrome" : driver = new ChromeDriver();  
			logger.info("Chrome browser launched successfully.");
			break;

			case "edge" : driver = new EdgeDriver();  
			logger.info("Edge browser launched successfully.");
			break;

			case "firefox" : driver = new FirefoxDriver(); 
			logger.info("Firefox browser launched successfully.");
			break;

			default : System.out.println("invalid browser name"); 
			logger.info("Invalid browser name."); 
			return;
			}
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.navigate().to(p.getProperty("appurl1"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@AfterClass(groups= {"Sanity", "Regression", "Master", "DataDriven"})
	public void closebrowser() {
		driver.quit();
	}

	public String randomString() {

		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomtelephonennumber() {
		String generatednumber = RandomStringUtils.randomNumeric(5);
		return generatednumber;
	}

	public String randompassword() {
		String generatedpassword = RandomStringUtils.randomAlphanumeric(5);
		return generatedpassword;
	}

	public String captureScreen(String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);

		try {
			Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return targetFilePath;
	}

}
