/*Assignments 3.Customer_Service_Options
====================================
1. Launch Salesforce application https://login.salesforce.com/
2. Login with Provided Credentials
3. Click on Learn More link in Mobile Publisher
4. Clilck on Products and Mousehover on Service 
5. Click on Customer Services
6. Get the names Of Services Available 
7. Verify the title*/

package week4.day2.assignments.mandatory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.sukgu.Shadow;

public class CustomerServiceOptions {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions option=new ChromeOptions();
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://login.salesforce.com/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		String text="";

		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[text()='Learn More']")));
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();

		//switch to second window
		Set<String> allwindows=driver.getWindowHandles();
		List<String> listofwindows=new ArrayList<String>(allwindows);
		String secondwindow=listofwindows.get(1);
		driver.switchTo().window(secondwindow);
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();

		//locate shadow root element products
		Shadow dom=new Shadow(driver);
		WebElement products=dom.findElementByXPath("//span[text()='Products']");
		products.click();

		//mouseover on service and click customer services
		WebElement service=dom.findElementByXPath("//span[text()='Service']");
		Actions builder=new Actions(driver);
		Thread.sleep(3000);
		builder.moveToElement(service).perform();
		WebElement customer = dom.findElementByXPath("//a[text()='Customer Service']");
		builder.scrollToElement(customer).perform();
		customer.click();

		//get the names of services available
		List<WebElement> listofservices=new ArrayList<WebElement>();
		listofservices=driver.findElements(By.xpath("//h2[@data-equalize=\"heading1\"]/span"));
		List<String> services=new ArrayList<String>();
		for(int i=0;i<listofservices.size();i++) {
			text=listofservices.get(i).getText();
			services.add(text);
		}
		System.out.println("The services available are");
		for (String s : services) {
			System.out.println(s);
		}

		//verify title
		String title=driver.getTitle();
		if(title.contains("Customer Service")) {
			System.out.println("Title verified");
		}
		else {
			System.out.println("Wrong title");
		}
	}
}
