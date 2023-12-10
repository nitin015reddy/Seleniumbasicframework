package base;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

//import testdata.TestDataProvider;
import utils.ExcelUtility;
import utils.ReadProperties;

public class BaseHooks {
	public static WebDriver driver;
	public String filename;
	@DataProvider(name = "gettestdata")
	public String[][] getExcelData() {
		System.out.print(filename);
		return ExcelUtility.readExcelValue(filename);
	}
     @BeforeMethod
   //	@BeforeClass
	public void launchApplication() throws IOException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		driver = new ChromeDriver(options);
	//	driver.get("https://login.salesforce.com/");
		driver.get(ReadProperties.readfile("url"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	//	driver.findElement(By.id("username")).sendKeys("Nitink@testleaf.com");	
	//	driver.findElement(By.id("password")).sendKeys("Chicago@1518");
		driver.findElement(By.id("username")).sendKeys(ReadProperties.readfile("uname"));	
		driver.findElement(By.id("password")).sendKeys(ReadProperties.readfile("pwd"));
		driver.findElement(By.id("Login")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		System.out.println(driver.getTitle());	}

/*	@AfterClass
	public void closeApplication() {
		driver.close();
	} */

	@AfterMethod
	public void screenshot(ITestResult result) throws IOException {
		if(!result.isSuccess()) {
			TakesScreenshot ts = (TakesScreenshot) driver;
			File srcFile = ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(srcFile, new File("./images/"+result.getName()+".png"));
		}
		driver.close();
	}
	
}
