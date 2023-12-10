package testsuites;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.BaseHooks;


public class S10_93CreateNewLeads extends BaseHooks {

	

	@BeforeTest(alwaysRun = true)
	public void beforetest() {
		filename = "S10_93CreateNewLeads";
	}

	
	
	By toggle = By.xpath("//div[starts-with(@class,'slds-icon-waffle')]");
	By viewall = By.xpath("//button[contains(@aria-label,'View All Applications')]");
	By sales = By.xpath("//p[(text()='Sales')]");
	By leadss = By.xpath("//span[(text()='Leads')][1]");
	By newbtn = By.xpath("//button[contains(text(),'New')]");
	By salutation = By.xpath("//button[contains(@aria-label,'Salutation')]");
	By MR = By.xpath("//span[contains(@title,'Mr.')]");
	By Firstname = By.xpath("//input[contains(@placeholder,'First Name')]");
	By Lastname = By.xpath("//input[contains(@placeholder,'Last Name')]");
	By Company = By.xpath("//input[contains(@name,'Company')]");
	By status = By.xpath("//button[contains(@aria-label,'Open - Not')]");
	By statusvalue = By.xpath("//span/span[contains(@title,'Working - Contacted')]");
	By saveedit = By.xpath("//button[contains(@name,'SaveEdit')]");
	By actual = By.xpath("//div[contains(@title,'Lead')]");

//	@Test(dataProvider = "gettestdata", dataProviderClass = TestDataProvider.class)
	
	@Test(dataProvider = "gettestdata")
	public void CreateNewLeads(String fname, String lname, String com) throws InterruptedException {
		driver.findElement(toggle).click();
		Thread.sleep(2000);

		driver.findElement(viewall).click();
		System.out.println("Click view All ");
		Thread.sleep(5000);

		driver.findElement(sales).click();
		System.out.println("Click sales ");
		Thread.sleep(5000);

		System.out.println("Click on Leads tab");
		WebElement leads = driver.findElement(leadss);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", leads);
		Thread.sleep(5000);

		driver.findElement(newbtn).click();
		Thread.sleep(5000);
		System.out.println("Click new leads");

		// click on salutation dropdown and select value
		driver.findElement(salutation).click();
		driver.findElement(MR).click();
		Thread.sleep(3000);
		// Enter first name
		driver.findElement(Firstname).sendKeys(fname);
		// Enter last time
		driver.findElement(Lastname).sendKeys(lname);
		// Enter company
		driver.findElement(Company).sendKeys(com);
		WebElement elem = driver.findElement(status);
		js.executeScript("arguments[0].scrollIntoView(true);", elem);
		// click on lead status dropdown and select value
		elem.click();
		driver.findElement(statusvalue).click();
		Thread.sleep(1000);
		// click on Save
		driver.findElement(saveedit).click();
		Thread.sleep(5000);
		System.out.println("Saved new lead");

		// verify task message created successfully
		String Actualmessage = driver.findElement(actual).getText();
		String Expectedmessage = "Lead";
		Assert.assertEquals(Actualmessage, Expectedmessage);
		System.out.println("Lead created successfully");

	}
}