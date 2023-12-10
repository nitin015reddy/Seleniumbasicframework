package testsuites;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.BaseHooks;

public class S10_34CreateTask extends BaseHooks {
	/*
	 * @DataProvider(name="GetTestData") public String [][] getExcelData(){ return
	 * ExcelUtility.readExcelValue("S10_34CreateTask"); }
	 */

	@BeforeTest(alwaysRun = true)
	public void beforetest() {
		filename = "S10_34CreateTask";
	}
	By toggle = By.xpath("//div[starts-with(@class,'slds-icon-waffle')]");
	By viewall = By.xpath("//button[contains(@aria-label,'View All Applications')]");
	By searchdata = By.xpath("//input[contains(@placeholder,'Search apps or items...')]");
	By tasks = By.xpath("//a[contains(@data-label,'Tasks')]");
	By downarrow = By.xpath("//a[contains(@title,'Show one more action')]");
	By newtask = By.xpath("//a[contains(@title,'New Task')]");
	By subject = By.xpath("//input[contains(@class,'slds-combobox__input slds-input')]");
	By status = By.xpath("//div/a[contains(text(),'Not Started')]");
	By statusvalue = By.xpath("//a[contains(@title,'Waiting on someone else')]");
	By save = By.xpath("//div/button[3][contains(@title,'Save')]");
	By actual = By.xpath("//div[contains(@title,'Bootcamp')]");
     
	@Test(dataProvider = "gettestdata")
	public void CreateTask(String field, String sub) throws InterruptedException {
		driver.findElement(toggle).click();
		Thread.sleep(2000);
		driver.findElement(viewall).click();
		Thread.sleep(5000);
		// enter Tasks in search bar
		driver.findElement(searchdata).sendKeys(field);
		Thread.sleep(5000);
		// click on Tasks link
		WebElement task = driver.findElement(tasks);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", task);
		Thread.sleep(4000);
		// click on drop down and click on new task
		driver.findElement(downarrow).click();
		driver.findElement(newtask).click();
		Thread.sleep(3000);
		// Enter subject as "Bootcamp"
		driver.findElement(subject).sendKeys(sub);
		Thread.sleep(2000);
		// Select status as 'Waiting on someone else'
		driver.findElement(status).click();
		driver.findElement(statusvalue).click();
		// click on Save
		driver.findElement(save).click();
		Thread.sleep(4000);
		// verify task message created successfully
		// String expectedMessage = "Bootcamp";
		String expectedMessage = "Testleaf";
		String actualMessage = driver.findElement(actual).getText();
		System.out.println(actualMessage);

		Assert.assertTrue(expectedMessage.equalsIgnoreCase(actualMessage), "Task creation failed");
		System.out.println("Task created successfully");
		

	}
}
