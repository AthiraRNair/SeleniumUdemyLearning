package SeleniumUdemyLearning;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//pagefactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	//action classes
	public Productcatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		return new Productcatalogue(driver);
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public String getErrorMessage()
	{
		waitForElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
