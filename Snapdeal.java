/*Assignment 5:SnapDeal
============
1. Launch https://www.snapdeal.com/
2. Go to Mens Fashion
3. Go to Sports Shoes
4. Get the count of the sports shoes
5. Click Training shoes
6. Sort by Low to High
7. Check if the items displayed are sorted correctly
8.Select the price range (900-1200)
9.Filter with color Navy 
10 verify the all applied filters 
11. Mouse Hover on first resulting Training shoes
12. click QuickView button
13. Print the cost and the discount percentage
14. Take the snapshot of the shoes.
15. Close the current window
16. Close the main window*/

package week4.day2.assignments.mandatory;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.analysis.function.Exp;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Snapdeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		ChromeOptions option = new ChromeOptions();
		ChromeDriver driver=new ChromeDriver(option);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));

		//go to men--sports shoes and get count
		WebElement men=driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		Actions builder=new Actions(driver);
		builder.moveToElement(men).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='category-name category-count']")));
		String count=driver.findElement(By.xpath("//span[@class='category-name category-count']")).getText().replaceAll("[()]", "");
		System.out.println("The count of sports shoes is "+count);

		//click training shoes,sort

		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("sort-selected")));
		driver.findElement(By.className("sort-selected")).click();
		driver.findElement(By.xpath("(//ul[@class='sort-value']/li)[2]")).click();

		//select price range and color
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[1]")).sendKeys("500");
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).clear();
		driver.findElement(By.xpath("(//input[@class='input-filter'])[2]")).sendKeys("1000");
		driver.findElement(By.xpath("//div[@class=\"price-go-arrow btn btn-line btn-theme-secondary\"]")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@id='Color_s-Navy']/following-sibling::label[1]")).click();
		Thread.sleep(3000);

		//verify the filters
		System.out.println("The filters used are");	
		System.out.println(driver.findElement(By.xpath("(//div[@class=\"navFiltersPill\"])[1]")).getText());
		System.out.println(driver.findElement(By.xpath("(//div[@class='navFiltersPill'])[2]")).getText());

		//mouseover and click on quickview
		WebElement item=driver.findElement(By.xpath("//source[@class='product-image']/following::img"));
		builder.moveToElement(item).perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Quick View')]")));
		driver.findElement(By.xpath("//div[contains(text(),'Quick View')]")).click();

		//print cost and discount		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='payBlkBig']")));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@class='percent-desc ']")));
		System.out.println("The cost and discount provided are");
		System.out.println(driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText());
		System.out.println(driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText());

		//screenshot
		File Source =driver.getScreenshotAs(OutputType.FILE);
		File dest=new File("D:\\TestLeaf\\screenshots\\snapdeal screenshot.png");
		FileUtils.copyFile(Source, dest);
		System.out.println(FileUtils.getFile(Source,args));
		driver.close();
	}

}
