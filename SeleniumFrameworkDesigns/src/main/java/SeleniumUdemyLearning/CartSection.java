package SeleniumUdemyLearning;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponent;

public class CartSection extends AbstractComponent {
	
	WebDriver driver;
	public CartSection(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//pagefactory
	@FindBy(css=".cartSection h3")
	List<WebElement> cart;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	//action classes
	public Boolean productAddedToCartVerification(String productName)
	{
		Boolean match = cart.stream().anyMatch(itemname -> itemname.getText().equals(productName));
		return match;
	}
	public CheckOut checkOut()
	{
		checkoutButton.click();
		return new CheckOut(driver);
	}
	
	
}
