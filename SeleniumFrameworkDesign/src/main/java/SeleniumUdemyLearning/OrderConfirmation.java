package SeleniumUdemyLearning;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponents.AbstractComponent;

public class OrderConfirmation extends AbstractComponent {
	
	WebDriver driver;
	public OrderConfirmation(WebDriver driver) {
		
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//pagefactory
	@FindBy(css="tbody label[class*='ng-star-inserted']")
	List<WebElement> orderids;
	
	@FindBy(css="h1")
	WebElement message;
	
	
	//action classes
	public void orderID()
	{
		for(int i=0;i<orderids.size();i++)
		{
			System.out.println(orderids.get(i).getText());
		}
	}
	public String getConfirmationMessage()
	{
		String confirmationMessage =message.getText();
		return confirmationMessage;
	}
	
	
	
}
