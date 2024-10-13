package SeleniumUdemyLearning;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumUdemyLearning.LandingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class StepDefinitionImplementation extends BaseClass {
	public LandingPage landingPage;
	Productcatalogue productcatalogue;
	CartSection cartsection;
	CheckOut checkout;
	OrderConfirmation orderconfirmation;
	@Given("Launch e-commerce application")
	public void launch_ecommerce_application() throws IOException
	{
		landingPage = launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void loggin_with_username_password(String username,String password)
	{
		productcatalogue = landingPage.loginApplication(username, password);
	}
	@When("^Add product to cart with productname (.+)$")
	public void add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = productcatalogue.getProductList();
		cartsection = productcatalogue.addToCart(productName);
		productcatalogue.goToCart();
	}
	@When("^checkout (.+) and submit the order$")
	public void checkout_and_submitOrder(String productName)
	{
		Boolean match = cartsection.productAddedToCartVerification(productName);
		Assert.assertTrue(match);
		checkout = cartsection.checkOut();
		checkout.sendCountry("ind");
		checkout.selectCountryFromDropdown();
		orderconfirmation = checkout.placeOrder();
	}
	@Then("{string} message is displayed on confirmationPage")
	public void message_confirmation(String string)
	{
		String confirmationMessage = orderconfirmation.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, string);

		orderconfirmation.orderID();
	}

}
