package SeleniumUdemyLearning;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class Productcatalogue extends AbstractComponent {
	
	WebDriver driver;
	public Productcatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
	}
	
	//pagefactory
	@FindBy(css=".mb-3")
	List <WebElement> products;
	
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	
	
	By productsby = By.cssSelector(".mb-3");
	By addtocart = By.cssSelector(".card-body button:last-of-type");
	By toastmessage = By.cssSelector(".toast-container");
	
	
	public List<WebElement> getProductList()
	{
		waitForElementToAppear(productsby);
		return products;
	}
	
	public WebElement getProductByName(String productName)
	{

		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		return prod;
	}
	
	public CartSection addToCart(String productName) throws InterruptedException
	{
		WebElement prod = getProductByName(productName).findElement(addtocart);
		prod.click();
		waitForElementToAppear(toastmessage);
		waitForElementToDisappear(spinner);
		return new CartSection(driver);
		
	}
	

}
