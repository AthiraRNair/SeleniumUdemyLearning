package SeleniumUdemyLearning;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseClass {
	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" })

	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		Productcatalogue productcatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productcatalogue.getProductList();

		CartSection cartsection = productcatalogue.addToCart(input.get("productName"));
		productcatalogue.goToCart();

		Boolean match = cartsection.productAddedToCartVerification(input.get("productName"));
		Assert.assertTrue(match);
		CheckOut checkout = cartsection.checkOut();

		checkout.sendCountry("ind");
		checkout.selectCountryFromDropdown();
		OrderConfirmation orderconfirmation = checkout.placeOrder();

		String confirmationMessage = orderconfirmation.getConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");

		orderconfirmation.orderID();

	}

	@Test(dependsOnMethods = { "submitOrder" })

	public void orderHistoryTest() {
		Productcatalogue productcatalogue = landingPage.loginApplication("athusaru@gmail.com", "Athira@22");
		OrderPage orderpage = productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderpage.verifyOrderDisplay(productName));

	}
	


	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "\\src\\test\\java\\Data\\Data.json");

		return new Object[][] { { data.get(0) }, { data.get(1) } };

//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "athusaru@gmail.com")	;
//		map.put("password", "Athira@22");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map.put("email", "rahulshetty@gmail.com")	;
//		map.put("password", "Iamking@000");
//		map.put("productName", "ADIDAS ORIGINAL");
//		
		// return new Object[][] {{"athusaru@gmail.com", "Athira@22","ZARA COAT
		// 3"},{"rahulshetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};

	}

}
