package Week4.Day1.Assignments.Mandatory;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		String strUsername="demosalesmanager";
		String strPassword="crmsfa";
		//we have to call WDM for the browser driver
		WebDriverManager.chromedriver().setup(); //verify the version, download,setup		
		ChromeDriver driver=new ChromeDriver();		//launch the browser -chrome
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Open browser and login
		driver.get("http://leaftaps.com/opentaps");		 //load the url
		driver.manage().window().maximize(); //maximize the browser		
		driver.findElement(By.id("username")).sendKeys(strUsername);	//enter the username in the username field	
		driver.findElement(By.id("password")).sendKeys(strPassword);		//enter the password in the password field
		driver.findElement(By.className("decorativeSubmit")).click();//Click the login button
		//verify if login is successful
		WebElement logout= driver.findElement(By.className("decorativeSubmit")); //Check if we are in the right page
		String attribute=logout.getAttribute("value"); //Get the attribute and print
		System.out.println(attribute);		
		if(attribute.equals("Logout")){
			System.out.println("Successfully logged in to leaftaps as: " +strUsername);
		}	
		driver.findElement(By.linkText("CRM/SFA")).click(); //click CRM/SFA button

        //=============Merge 
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();    //clicks the accounts link
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();  //click on Merge Contacts
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[1]")).click(); 

		Set<String> allHandles = driver.getWindowHandles();		
		//Create a list to access by id, cant do this in set as it doesnt have get method
		List<String> lstWindowHandles = new ArrayList<String>(allHandles);
		String strSecondHandle=lstWindowHandles.get(1); //gets the 2nd handle		
		driver.switchTo().window(strSecondHandle);		//moving control to 2nd window
		driver.findElement(By.xpath("(//td[contains(@class,'x-grid3-cell-first')]/div/a)[1]")).click();  //click the first contact id link

		driver.switchTo().window(lstWindowHandles.get(0)); //Switch to 1st window		
		String strLinkText1 = driver.findElement(By.id("ComboBox_partyIdFrom")).getAttribute("value");
		driver.findElement(By.xpath("(//img[@src='/images/fieldlookup.gif'])[2]")).click(); //click the lookup next to 'to contact'
		allHandles = driver.getWindowHandles();		
		//Create a list to access by id, cant do this in set as it doesnt have get method
		lstWindowHandles = new ArrayList<String>(allHandles);
	    strSecondHandle=lstWindowHandles.get(1); //gets the 2nd handle
	    driver.switchTo().window(strSecondHandle);	
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("(//td[contains(@class,'x-grid3-cell-first')]/div/a)[2]")).click();  //click the second contact id link
		driver.switchTo().window(lstWindowHandles.get(0)); //Switch to 1st window	
		String strLinkText2 = driver.findElement(By.id("ComboBox_partyIdTo")).getAttribute("value");
		driver.findElement(By.xpath("//a[text()='Merge']")).click(); //Click the merge button

		//Handle the alert
		Alert alert=driver.switchTo().alert();
		String text2 = alert.getText();
		alert.accept(); //clicks ok on the prompt box
		System.out.println("Handled the alert: " +text2);
		String title = driver.getTitle();
		System.out.println("The title after merging the contacts: "+strLinkText1 +" & " +strLinkText2 + " is: " + title);
	}
}