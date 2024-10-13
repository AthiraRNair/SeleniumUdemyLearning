package SeleniumUdemyLearning;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public WebDriver driver;
	LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");
		
		if (browserName.contains("chrome")) {
			ChromeOptions options =new ChromeOptions();
			WebDriverManager.chromedriver().setup();
//			System.setProperty("webdriver.chrome.driver", "C:\\Selenium Webdriver\\chromedriver-win64\\chromedriver.exe");
			if(browserName.contains("headless")){
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			//driver = new ChromeDriver();

		} else if (browserName.equalsIgnoreCase("firefox")) {
			// firefox
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;

	}
	
	public String getScreenshot(String testName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File fildest =new File(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\"+testName+".png");
		FileUtils.copyFile(source, fildest);
		return System.getProperty("user.dir")+"\\src\\test\\java\\Data\\"+testName+".png";
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String Filepath) throws IOException {
//		read json to string
		String jsonContent = FileUtils.readFileToString(new File (Filepath), StandardCharsets.UTF_8);
//		String to HashMap - JacksonBind
		ObjectMapper mapper =new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
			
		});
		return data;

	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
		
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}

}
