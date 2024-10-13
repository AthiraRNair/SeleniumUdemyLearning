package SeleniumUdemyLearning;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");

		driver.findElement(By.id("userEmail")).sendKeys("athusaru@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Athira@22");
		driver.findElement(By.id("login")).click();
		String item_to_add = "ZARA COAT 3";
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		WebElement productCart = items.stream()
				.filter(product -> product.findElement(By.tagName("b")).getText().equals(item_to_add)).findFirst()
				.orElse(null);
		productCart.findElement(By.cssSelector(".card-body button:last-of-type")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List<WebElement> cart = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean match = cart.stream().anyMatch(itemname -> itemname.getText().equals(item_to_add));
		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button span"));
		List<WebElement> countryMatch = countries.stream()
				.filter(country -> country.getText().equalsIgnoreCase("india")).collect(Collectors.toList());

		countryMatch.getFirst().click();

		driver.findElement(By.cssSelector(".actions a")).click();
		List<WebElement> orderIDs = driver.findElements(By.cssSelector("tbody label[class*='ng-star-inserted']"));
		for(int i=0;i<orderIDs.size();i++)
		{
			System.out.println(orderIDs.get(i).getText());
		}
		
	}

}
