package testsuites;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import base.BaseHooks;

public class S10_31EditCase extends BaseHooks {
 
	

	@BeforeTest(alwaysRun = true)
	public void beforetest() {
		filename = "S10_31EditCase";
	}

	
	By toggle = By.xpath("//div[starts-with(@class,'slds-icon-waffle')]");
	By viewall = By.xpath("//button[contains(@aria-label,'View All Applications')]");
	By sales = By.xpath("//p[(text()='Sales')]");
	By mre = By.xpath("//span[contains(text(),'More')]");
	By Casess = By.xpath("//a/span/span[contains(text(),'Cases')]");
	By viewcas = By.xpath("//table/tbody/tr/td[6]");
	By dwnar = By.xpath("//div[contains(@class,'forceVirtualActionMarker forceVirtualAction')]");
	By editbtn = By.xpath("//a[contains(@title,'Edit')]");
	By stat = By.xpath("//button[contains(@aria-label,'Status')]");
	By twrk = By.xpath("//span[contains(@title,'Working')]");
	By pty = By.xpath("//button[contains(@aria-label,'Priority')]");
	By ptl = By.xpath("//span[contains(@title,'Low')]");
	By cas = By.xpath("//button[contains(@aria-label,'Case Origin')]");
	By phon = By.xpath("//span[contains(@title,'Phone')]");
	By sla = By.xpath("//button[contains(@aria-label,'SLA Violation')]");
	By tno = By.xpath("//span[(@title='No')]");
	By can = By.xpath("//button[contains(@name,'Cancel')]");

	@Test(dataProvider = "gettestdata")
	public void EditTask(String caseowner) throws InterruptedException {

		driver.findElement(toggle).click();
		System.out.println("Clicked on toggle menu button from the left corner");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.findElement(viewall).click();
		System.out.println("Click view All ");
		Thread.sleep(5000);

		driver.findElement(sales).click();
		System.out.println("Click sales ");
		Thread.sleep(5000);

		System.out.println("Click on cases under more");
		driver.findElement(mre).click();

		WebElement cases = driver.findElement(Casess);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", cases);
		Thread.sleep(8000);

		System.out.println("view cases");
		List<WebElement> list1 = driver.findElements(viewcas);

		for (WebElement element : list1) {
			//String s1 =element.getText();
			if (element.getText().equals(caseowner)) {
				break;
			}
		}
		driver.findElement(dwnar).click();
		Thread.sleep(5000);
		System.out.println("clickondownarrow");
		driver.findElement(editbtn).click();
		Thread.sleep(3000);
		System.out.println("Edit fields in case");
		driver.findElement(stat).click();
		driver.findElement(twrk).click();
		System.out.println(" Update Status as Working");
		driver.findElement(pty).click();
		driver.findElement(ptl).click();
		System.out.println("Update Priority to low");
		driver.findElement(cas).click();
		driver.findElement(phon).click();
		System.out.println("Update Case Origin as Phone");
		Thread.sleep(3000);

		WebElement elem = driver.findElement(sla);
		js.executeScript("arguments[0].scrollIntoView(true);", elem);

		driver.findElement(sla).click();
		driver.findElement(tno).click();
		System.out.println("Update SLA violation to No");
		Thread.sleep(5000);
		// driver.findElement(By.xpath("//button[contains(@name,'SaveEdit')]")).click();
		driver.findElement(can).click();
		Thread.sleep(5000);
		// System.out.println("clicked Save");
		String sColValue = "NKarr";
		String Actualvalue = null;
		// First loop will find Nkarr value
		for (int i = 1; i < 10; i++) {
			String sValue = null;
			sValue = driver.findElement(By.xpath("//table/tbody/tr/td[" + i + "]")).getText();
			if (sValue.equals(sColValue)) {
				Actualvalue = driver.findElement(By.xpath("//table/tbody/tr/td[" + (i - 2) + "]")).getText();
				System.out.println(Actualvalue);
				break;
			}
		}   

		String Expectedvalue = "Working";
	Assert.assertEquals(Actualvalue, Expectedvalue);
		System.out.println("Case is Edited Successfully  and Status is working");

	}

}
