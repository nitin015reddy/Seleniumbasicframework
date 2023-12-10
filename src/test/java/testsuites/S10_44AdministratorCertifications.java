package testsuites;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import base.BaseHooks;
import io.github.sukgu.Shadow;

//no need for dataprovider as not passing any value in this test case from excel
		
public class S10_44AdministratorCertifications extends BaseHooks {
	
	By scrolltiles = By.xpath("//article//h2/span");
	By learnmorelink = By.xpath("//span[contains(text(),'Learn More')]");	
	By confirmnewwindow = By.xpath("//button[contains(text(),'Confirm')]");
	By windowtitle = By.xpath("//span[contains(text(),'Create and publish')]");	
	By salesadminicon = By.xpath("//a[contains(@href,'administratoroverview')]");
	By certifylist = By.xpath("//div[contains(@class,'credentials-card_title')]/a");
	
	
	@Test 
	public void AdminCert() throws InterruptedException {
    
    List<WebElement> list =driver.findElements(scrolltiles);
    
    for(WebElement element:list ){	        	
    if(element.getText().equals("Mobile Publisher"))
    {	    
    System.out.println("Match Found");
    Thread.sleep(2000);
    break;
    }  }
    
    Thread.sleep(5000);
    //Store the ID of the original window
    String originalWindow = driver.getWindowHandle();
    //Check we don't have other windows open already
    assert driver.getWindowHandles().size() == 1;
    //Click the link which opens in a new window
    driver.findElement(learnmorelink).click();   
    //Loop through until we find a new window handle
    for (String windowHandle : driver.getWindowHandles()) {
        if(!originalWindow.contentEquals(windowHandle)) {
            driver.switchTo().window(windowHandle);
            System.out.println(driver.getTitle());
            break;
        }
    }
    //click on confirm on new window
    driver.findElement(confirmnewwindow).click();
    Thread.sleep(5000);
    
   String value= driver.findElement(windowtitle).getText();  
   System.out.println(value); 
    
   //click on Learning hidden link
   Shadow dom= new Shadow(driver);
  WebElement learng= dom.findElementByXPath("//span[contains(text(),'Learning')]");
  learng.click();
  Thread.sleep(3000);
   //move to learning on trailhead 
  WebElement ele= dom.findElementByXPath("//span[contains(text(),'Learning on Trailhead')]");
  //Creating object of an Actions class
   Actions action = new Actions(driver);
   //Performing the mouse hover action on the target element.
   action.moveToElement(ele).perform(); 
   //and then click salesforce certifications link
  WebElement cert= dom.findElementByXPath("//a[contains(text(),'Salesforce Certification')]");
  cert.click();
  Thread.sleep(5000);  
  
   //click on salesforce administator icon
   driver.findElement(salesadminicon).click();

   //scroll into view until 1st certification displayed on page
   JavascriptExecutor js = (JavascriptExecutor)driver;	
   WebElement elem = driver.findElement(certifylist);
   js.executeScript("arguments[0].scrollIntoView(true);", elem);
   
   
   //verify 6 ceritifcations details displayed
 
   List<WebElement> list1 =driver.findElements(certifylist);        	
   	if(list1.size()==6)
   	{	
   		System.out.println("count match");
   		
   	}
   	else {
   		System.out.println("count mismatch");
   	}
    
    driver.switchTo().window(originalWindow);
}
}