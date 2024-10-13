package SeleniumUdemyLearning;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponent;

public class CheckOut extends AbstractComponent {
	
	WebDriver driver;
	public CheckOut(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//pagefactory
	@FindBy(xpath="//input[@placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css=".ta-results button span")
	List<WebElement> countries;
	
	@FindBy(css=".actions a")
	WebElement placeorder;
	
	//action classes
	public void sendCountry(String countryName)
	{
		country.sendKeys(countryName);
	}
	public void selectCountryFromDropdown()
	{
		 countries.stream()
			.filter(countrySelect -> countrySelect.getText().equalsIgnoreCase("india")).collect(Collectors.toList()).getFirst().click();
	}
	public OrderConfirmation placeOrder()
	{
		placeorder.click();
		return new OrderConfirmation(driver);
	}
	
	
}
