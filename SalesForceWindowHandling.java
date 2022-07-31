package Week4.Day1.Assignments.Mandatory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SalesForceWindowHandling {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//we have to call WDM for the browser driver
		WebDriverManager.chromedriver().setup(); //verify the version, download,setup	
		//Handling the browser notification
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");			
		ChromeDriver driver=new ChromeDriver(options);		//launch the browser -chrome
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Open browser and login
		driver.get("https://login.salesforce.com/");		 //load the url
		driver.manage().window().maximize(); //maximize the browser

		//Login to salesforce
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		
		//Click learn more in the Mobile Publisher page
		driver.findElement(By.xpath("//span[text()='Learn More']")).click();
		System.out.println("Clicked Learn More button in the Mobile Publisher section");
		
		//Switching to the 2nd window
		Set<String> allHandles = driver.getWindowHandles();		
		//Create a list to access by id, cant do this in set as it doesnt have get method
		List<String> lstWindowHandles = new ArrayList<String>(allHandles);
		String strSecondHandle=lstWindowHandles.get(1); //gets the 2nd handle		
		driver.switchTo().window(strSecondHandle);		//moving control to 2nd window
		
		//Clicking the Confirm button in the 2nd window
		driver.findElement(By.xpath("//button[text()='Confirm']")).click();
		System.out.println("Clicked the confirm button");
		String title = driver.getTitle();
		System.out.println("The browser title is: "+title);
		driver.close();
		
		//switching back to parent window
		driver.switchTo().window(lstWindowHandles.get(0));
		driver.close();
		

		



	}

}
