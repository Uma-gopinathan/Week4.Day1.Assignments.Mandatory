package Week4.Day1.Assignments.Mandatory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowsHandlingLeafGround {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		//we have to call WDM for the browser driver
		WebDriverManager.chromedriver().setup(); //verify the version, download,setup		
		ChromeDriver driver=new ChromeDriver();		//launch the browser -chrome
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Open browser and login
		driver.get("http://www.leafground.com/pages/Window.html");		 //load the url

		//Click the open home page button
		System.out.println("----------------------------");
		driver.findElement(By.id("home")).click();
		System.out.println("Clicked the home button");

		//Switching to the 2nd window
		Set<String> allHandles = driver.getWindowHandles();		
		//Create a list to access by id, cant do this in set as it doesnt have get method
		List<String> lstWindowHandles = new ArrayList<String>(allHandles);
		String strSecondHandle=lstWindowHandles.get(1); //gets the 2nd handle		
		driver.switchTo().window(strSecondHandle);		//moving control to 2nd window
		System.out.println("The 2nd browser title on clicking 'home' button is: "+ driver.getTitle());
		driver.close();

		//switch to 1st window
		driver.switchTo().window(lstWindowHandles.get(0));

		//Find the number of opened windows
		System.out.println("----------------------------");
		driver.findElement(By.xpath("//button[text()='Open Multiple Windows']")).click();
		//Switching to the 2nd window
		allHandles = driver.getWindowHandles();		
		//Create a list to access by id, cant do this in set as it doesnt have get method
		lstWindowHandles = new ArrayList<String>(allHandles);
		int intNoWindows=lstWindowHandles.size()-1;
		System.out.println("The no.of windows opened by clicking 'Opens multiple windows' buttons is: "+ intNoWindows); //subtract parent window
		for(int i =1;i<=intNoWindows;i++) { //starting from 1 index to avoid closing parent window - 0 index
			String strWindowHandle = lstWindowHandles.get(i);
			driver.switchTo().window(strWindowHandle);
			String title = driver.getTitle();
			driver.close();
			System.out.println("Closed the window: "+ title);
		}
        
		//switch to 1st window
		driver.switchTo().window(lstWindowHandles.get(0));

		//click do not close me button
		System.out.println("----------------------------");
		driver.findElement(By.xpath("//button[contains(text(),'Do not close me')]")).click();
		System.out.println("Clicked the button 'Do not close me'");
		//Switching to the 1st window,donot close the 2nd window
		allHandles = driver.getWindowHandles();		
		if(allHandles.size()==2) {
			System.out.println("2nd window has been opened.");
		}
		else {
			System.out.println("2nd window has not been opened.");
		}
		//Create a list to access by id, cant do this in set as it doesnt have get method
		lstWindowHandles = new ArrayList<String>(allHandles);
		String firstWindow = lstWindowHandles.get(0);
		driver.switchTo().window(firstWindow);
		System.out.println("Switching to first window, without closing the 2nd.'");

		//click the 'Wait for 5 seconds' button
		System.out.println("----------------------------");
		driver.findElement(By.xpath("//button[contains(text(),'Wait for 5 seconds')]")).click();
		Thread.sleep(1000);
		lstWindowHandles = new ArrayList<String>(allHandles);
		System.out.println("No. of windows opened by clicking the 'Wait for 5 seconds window is: '"+ (lstWindowHandles.size()-1));
		for(int j=1;j<=(lstWindowHandles.size())-1;j++) {
			String strWindowHandle = lstWindowHandles.get(j);
			driver.switchTo().window(strWindowHandle);
			String title2 = driver.getTitle();
			System.out.println("Window: "+title2+ " was opened.");
		}
		driver.quit(); //Closes all browsers
	}
}
