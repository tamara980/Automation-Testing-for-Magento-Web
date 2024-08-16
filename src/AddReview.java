import java.time.Duration;
import java.time.temporal.TemporalAmount;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddReview {

	WebDriver driver= new ChromeDriver();
	Random rand = new Random();
	String password = "Tamara12345#12111";
	String Email=" ";
	
	@BeforeTest
	public void setup() {
		driver.get("https://magento.softwaretestingboard.com/");
		//driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	@Test (priority=1)
			//, enabled=false)
	public void CreateAccount() {

		WebElement createAccount3 = driver.findElement(By.linkText("Create an Account"));
		createAccount3.click();

		String[] firstNames = { "John", "Jane", "Michael", "Emily", "David", "Sarah", "Robert", "Jessica", "Daniel","Laura" };
		String[] LastNames = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore","Taylor" };

		//rand.nextInt(7);
		//System.out.println(rand.nextInt(7));
		int firstNameNO= rand.nextInt(firstNames.length);
		int lastNameNO= rand.nextInt(LastNames.length);
		System.out.println(firstNameNO);
		System.out.println(lastNameNO);
		
		WebElement fillFirstName= driver.findElement(By.id("firstname"));
		fillFirstName.sendKeys(firstNames[firstNameNO]);
		WebElement fillLastName= driver.findElement(By.name("lastname"));
		fillLastName.sendKeys(LastNames[lastNameNO]);
		WebElement fillEmail= driver.findElement(By.id("email_address"));
		//int randoomNumber= rand.nextInt(7532);
		String Domain2= "@gmail.com";
		int randomNumburForEmail= rand.nextInt(7532);
		fillEmail.sendKeys(firstNames[firstNameNO]+LastNames[lastNameNO]+randomNumburForEmail+Domain2);
		Email= firstNames[firstNameNO]+LastNames[lastNameNO]+randomNumburForEmail+Domain2; 
	
		
		WebElement passwordField= driver.findElement(By.id("password"));
		WebElement confirmPasswordField= driver.findElement(By.id("password-confirmation"));
		WebElement submitButton= driver.findElement(By.xpath("//button[@title='Create an Account']"));
		
		passwordField.sendKeys(password);
		confirmPasswordField.sendKeys(password);
		submitButton.click();
		
		
		//WebElement assertForSuccessMessage= driver.findElement(By.className("message-success"));
		//Another way
		WebElement assertForSuccessMessage= driver.findElement(By.cssSelector(".message-success.success.message"));
		Assert.assertEquals(assertForSuccessMessage.getText().contains("Thank you for registering with Main Website Store."), true);
		
		//Another Way
		
		String ActualResul=assertForSuccessMessage.getText();
		String ExpectedResult= "Thank you for registering with Main Website Store.";
		Assert.assertEquals(ActualResul, ExpectedResult);
	}
	
	@Test(priority= 2)
	public void WomanList() {
		WebElement womanListClicking = driver.findElement(By.cssSelector("#ui-id-4"));
		womanListClicking.click();

		
	}
	
	@Test(priority= 3)
	public void ListOfItems() {
		WebElement itemsList = driver.findElement(By.className("product-items"));
		//WebElement itemsList = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List <WebElement> item= itemsList.findElements(By.tagName("li"));
		int sumOfItems= item.size();
		System.out.println("Sum of items ="+sumOfItems);
		int RandindexItem= rand.nextInt(sumOfItems);
		System.out.println("indexOfItem="+RandindexItem);
		WebElement RandomItem= item.get(RandindexItem);
		RandomItem.click();
		
	}
	@Test(priority= 4)
	public void SelectSize() {
		WebElement SizeContainer= driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));
		
		List <WebElement> ListOfSizes= SizeContainer.findElements(By.tagName("div"));
		int sumOfSizes= ListOfSizes.size();
		System.out.println("Sum of sizes ="+sumOfSizes);
		int RandindexSize= rand.nextInt(sumOfSizes);
		System.out.println("Random index of size= "+RandindexSize);
		WebElement RandomSize =ListOfSizes.get(RandindexSize);
		RandomSize.click();
		
	}
	@Test(priority= 5)
	public void SelectColor() {
		WebElement ColorContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));
		List<WebElement> ListOfColors= ColorContainer.findElements(By.tagName("div"));
		int sumOfColors= ListOfColors.size();
		System.out.println("Sum of Colors ="+sumOfColors);
		int RandindexColor= rand.nextInt(sumOfColors);
		System.out.println("Random index of color= "+RandindexColor);
		WebElement RandomColor= ListOfColors.get(RandindexColor);
		RandomColor.click();
	}
	@Test(priority= 6)
	public void SubmitButton() {
		WebElement AddToChart= driver.findElement(By.cssSelector("#product-addtocart-button"));
		AddToChart.click();
		
		WebElement addedMessage= driver.findElement(By.className("message-success"));
		Assert.assertEquals(addedMessage.getText().contains("You added"), true);
		}
	

	@Test (priority= 7, enabled=false)
	public void MoreInformation() throws InterruptedException{	
		WebElement moreInformation =driver.findElement(By.linkText("More Information"));
		moreInformation.click();
		
	}
	
	
	@Test (priority= 8)
	public void review() throws InterruptedException{	
		JavascriptExecutor JS= (JavascriptExecutor) driver;
		JS.executeScript("window.scrollTo(0,1000)");
		Thread.sleep(5000);
		WebElement reviewTab= driver.findElement(By.cssSelector("#tab-label-reviews-title"));
		//WebElement reviewTab= driver.findElement(By.partialLinkText("Reviews"));
		reviewTab.click();
		
		//Wrong way that all the container has the same className
		//WebElement reviewTab= driver.findElement(By.className("data"));
		WebElement ratingStars = driver.findElement(By.cssSelector(".control.review-control-vote"));
		System.out.println(ratingStars.findElements(By.tagName("Label")).size()+"***************");
		String[]ids= {"Rating_1","Rating_2","Rating_3","Rating_4","Rating_5"};
		int randomIDS= rand.nextInt(ids.length);
		System.out.println(ids[randomIDS]);
		WebElement element= driver.findElement(By.id(ids[randomIDS]));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()",element);
		
		WebElement Summary= driver.findElement(By.id("summary_field"));
		Summary.sendKeys("Test                         ,,,");
		WebElement Review= driver.findElement(By.id("review_field"));
		Review.sendKeys("Test Test Review");
		
		WebElement submitButton = driver.findElement(By.cssSelector("button[class='action submit primary']"));
		submitButton.click();
		WebElement submitMessage= driver.findElement(By.cssSelector(".message-success.success.message"));
		Assert.assertEquals(submitMessage.getText().contains("You submitted your review"), true);
		
		
		String ActualResult= driver.findElement(By.cssSelector(".message-success.success.message")).getText();
		String expectedResult=	"You submitted your review for moderation.";
		Assert.assertEquals(ActualResult, expectedResult);
  }	
	




}
