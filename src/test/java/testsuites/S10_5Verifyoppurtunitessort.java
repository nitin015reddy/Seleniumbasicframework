package testsuites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import base.BaseHooks;


public class S10_5Verifyoppurtunitessort extends BaseHooks {

	
	

	@BeforeTest(alwaysRun = true)
	public void beforetest() {
		filename = "S10_5Verifyoppurtunitessort";
	}

	
	By toggle = By.xpath("//div[starts-with(@class,'slds-icon-waffle')]");
	By viewall = By.xpath("//button[contains(@aria-label,'View All Applications')]");
	By sales = By.xpath("//p[(text()='Sales')]");
	By opport = By.xpath("//span[(text()='Opportunities')][1]");
	By lview = By.xpath("//button[contains(@title,'Select list display')]");
	By drpdwn = By.xpath("//a/span[text()='Table']");
	By before = By.xpath("//table/tbody/tr/td[6]//span/span[contains(@class,'uiOutputDate')]");
	By srt = By.xpath("//span[contains(@title,'Close Date')]");
	By after = By.xpath("//table/tbody/tr/td[6]//span/span[contains(@class,'uiOutputDate')]");

	@Test(dataProvider = "gettestdata")
	public void VerifyOpportunities() throws InterruptedException {

		driver.findElement(toggle).click();
		System.out.println("Clicked on toggle menu button from the left corner");
		Thread.sleep(3000);

		driver.findElement(viewall).click();
		System.out.println("Click view All ");
		Thread.sleep(8000);

		driver.findElement(sales).click();
		System.out.println("Click sales ");
		Thread.sleep(5000);

		System.out.println("Click on Opportunity tab");
		WebElement opportunity = driver.findElement(opport);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", opportunity);
		Thread.sleep(5000);

		System.out.println("Click on Table view");
		driver.findElement(lview).click();
		driver.findElement(drpdwn).click();
		Thread.sleep(2000);
		// store table values of close date before sort
		ArrayList<String> obtainedList1 = new ArrayList<String>();
		List<WebElement> list1 = driver.findElements(before);
		for (WebElement we : list1) {
			obtainedList1.add(we.getText());
			System.out.println("success1");
		}
		ArrayList<String> sortedList = new ArrayList<String>();
		for (String s : obtainedList1) {
			sortedList.add(s);
		}
		Collections.sort(sortedList);
		// sorting by close date
		WebElement sort = driver.findElement(srt);
		js.executeScript("arguments[0].click();", sort);
		Thread.sleep(3000);
		js.executeScript("arguments[0].click();", sort);
		System.out.println("Sort the Opportunities by Close Date in ascending order");
		Thread.sleep(3000);
		Actions builder = new Actions(driver);
		Action click = builder.doubleClick().build();
		click.perform();
		Thread.sleep(3000);

		// store table values of close date after sort
		ArrayList<String> obtainedList2 = new ArrayList<String>();
		List<WebElement> list2 = driver.findElements(after);
		for (WebElement we : list2) {
			obtainedList2.add(we.getText());
			System.out.println("success2");
		}

		// compare two lists
		Assert.assertTrue(obtainedList2.equals(sortedList));
		System.out.println("Verify the Opportunities displayed in ascending order by Close date");

	}

}
