package testsuites;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.BaseHooks;


public class S10_42VerifyAlertForWorkType extends BaseHooks {
	
	

	@BeforeTest(alwaysRun = true)
	public void beforetest() {
		filename = "S10_42VerifyAlertForWorkType";
	}


	By toggle = By.xpath("//div[starts-with(@class,'slds-icon-waffle')]");
	By viewall = By.xpath("//button[contains(@aria-label,'View All Applications')]");
	By searchdata = By.xpath("//input[contains(@placeholder,'Search apps or items...')]");
	By worktypes = By.xpath("//a[contains(@data-label,'Work Types')]");
	By newbutton = By.xpath("//div[(text()='New')]");
	By subject = By.xpath("//input[contains(@maxlength,'255')]");
	By save = By.xpath("//div/button[3][contains(@title,'Save')]");
	By actual = By.xpath("//li[contains(text(),'Complete this field.')]");

	@Test(dataProvider = "gettestdata")
	public void MandatoryFieldForWorkType(String field, String sub) throws InterruptedException {

		driver.findElement(toggle).click();
		Thread.sleep(2000);
		driver.findElement(viewall).click();
		Thread.sleep(5000);
		// enter Work types in search bar
		driver.findElement(searchdata).sendKeys(field);
		Thread.sleep(1000);
		// click on work types link
		WebElement worktype = driver.findElement(worktypes);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", worktype);
		Thread.sleep(4000);
		// click on new button
		driver.findElement(newbutton).click();
		Thread.sleep(4000);
		// Enter subject as "Bootcamp"
		driver.findElement(subject).sendKeys(sub);
		// click on save
		driver.findElement(save).click();
		Thread.sleep(5000);
		System.out.println("Saved new worktype");
		// verify error for mandatory fields
		String Actualmessage = driver.findElement(actual).getText();
		System.out.println(Actualmessage);
		String Expectedmessage = "Complete this field.";

		Assert.assertEquals(Actualmessage, Expectedmessage);

	}
}