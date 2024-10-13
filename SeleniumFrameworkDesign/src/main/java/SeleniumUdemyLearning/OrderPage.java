package SeleniumUdemyLearning;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {
	
	WebDriver driver;
	public OrderPage(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//pagefactory
	
	@FindBy(css="tbody td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	//action classes
	public Boolean verifyOrderDisplay(String productName)
	{
		Boolean match = productNames.stream().anyMatch(itemname -> itemname.getText().equals(productName));
		return match;
	}

	
	
}
