package SeleniumUdemyLearning;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidations extends BaseClass{
	
//	@Test(retryAnalyzer=Retry.class)

	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("athusaru@gmail.com", "Athira22");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());	

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";
		Productcatalogue productcatalogue = landingPage.loginApplication("shetty@gmail.com", "Iamking@000");		
		List<WebElement> products = productcatalogue.getProductList();

		CartSection cartsection = productcatalogue.addToCart(productName);
		productcatalogue.goToCart();
		 
		Boolean match = cartsection.productAddedToCartVerification(productName);
		Assert.assertTrue(match);
	}
	


}
