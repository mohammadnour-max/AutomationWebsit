package automationexerciseWebsit;

import static org.testng.Assert.assertEquals;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCasesSecendPro {
	
	Random rand=new Random();
	
	WebDriver driver=new ChromeDriver();
	String Websit="https://automationexercise.com/";
	String []name= {"Mohmmadnour","Ahmad","Abed","Sara","jana","mera","tamer"};
	String []lastName={"Marie","Rami","Emad","raaid","foaad","faris"};
	String country="Jordan";
	String address="Amman";
	String []city={"Amman","Alzarqaa","Irbid","Aqaba","Ajloun","Jarsh","Alsalt"};
	int ZipCode=1234;
	String state="S";
	String mobileNumber="0787427811";
	
	String email="@gmail.com";
	
	int numberForName=rand.nextInt(name.length);
	int numberForEmail =rand.nextInt();
	int password=rand.nextInt();

	
	@BeforeTest
	public void setup() {
		driver.get(Websit);
		driver.manage().window().maximize();
		
	}
	
	@Test(priority = 1)
	public void signup() throws InterruptedException {
		
		
		driver.navigate().to("https://automationexercise.com/login");//to login and signup page
		
		WebElement nameFaild=driver.findElement(By.name("name"));
		WebElement emailFaild=driver.findElement(By.cssSelector("[data-qa='signup-email']"));
		WebElement signupButton=driver.findElement(By.cssSelector("[data-qa='signup-button']"));
		
		//name is Randomly
		
		nameFaild.sendKeys(name[numberForName]);
		
		//email is Randomly
		emailFaild.sendKeys(name[numberForName]+numberForEmail+email);
		
		signupButton.click();
		Thread.sleep(3000);
		assertEquals(driver.getCurrentUrl(), "https://automationexercise.com/signup");
		
	}
	
	
	@Test(priority = 2)
	public void enterAccInfo() throws InterruptedException {
		WebElement title=driver.findElement(By.id("id_gender1"));
		WebElement passwordCreate=driver.findElement(By.id("password"));
		WebElement birthDateDay=driver.findElement(By.id("days"));
		WebElement birthDateMonth=driver.findElement(By.id("months"));
		WebElement birthDateYear=driver.findElement(By.id("years"));
		WebElement checkingBox =driver.findElement(By.id("newsletter"));

		title.click();
		
		passwordCreate.sendKeys(Integer.toString(password));
		
		Select selectDay=new Select(birthDateDay);
		selectDay.selectByIndex(rand.nextInt(1,32));
		
		Select selectMonth=new Select(birthDateMonth);
		selectMonth.selectByIndex(rand.nextInt(1,13));
		
		Select selectYear=new Select(birthDateYear);
		selectYear.selectByValue(Integer.toString(rand.nextInt(1980,2021)));
		
		checkingBox.click();
		
	assertEquals(title.isSelected(), true);
	

	}

	@Test(priority = 3)
	public void addressInfoAndCreateAcc() throws InterruptedException {
		WebElement firstName=driver.findElement(By.id("first_name"));
		WebElement lastNameDri=driver.findElement(By.id("last_name"));
		WebElement addressDri=driver.findElement(By.id("address1"));
		WebElement countryDri=driver.findElement(By.id("country"));
		WebElement stateDri=driver.findElement(By.id("state"));
		WebElement cityDri=driver.findElement(By.id("city"));
		WebElement zipCodeDri=driver.findElement(By.id("zipcode"));
		WebElement mobileNumDri=driver.findElement(By.id("mobile_number"));
		WebElement createButton=driver.findElement(By.cssSelector("[data-qa='create-account']"));
		
		
		firstName.sendKeys(name[numberForName]);
		
		int numberOfLast=rand.nextInt(lastName.length);
		lastNameDri.sendKeys(lastName[numberOfLast]);
		
		addressDri.sendKeys(address);
		
		Select selectCounty= new Select(countryDri);
		selectCounty.selectByIndex(3);
		
		stateDri.sendKeys(state);
		
		int numberOfCity=rand.nextInt(city.length);
		cityDri.sendKeys(city[numberOfCity]);
		
		zipCodeDri.sendKeys(Integer.toString(rand.nextInt()));
		
		mobileNumDri.sendKeys(Integer.toString(rand.nextInt()));
		createButton.click();
		
		WebElement accountCreated=driver.findElement(By.cssSelector("[data-qa='account-created']"));
		assertEquals(accountCreated.isDisplayed(),true)	;
		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/account_created");
		
	}
	
	@Test(priority = 4,enabled=true)
	public void logout() {
		
		WebElement continueButton =driver.findElement(By.cssSelector("[data-qa='continue-button']"));
		continueButton.click();
		
		driver.navigate().to("https://automationexercise.com/logout");
	
		assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/login");
	}
	
	
	@Test(priority = 5)
	public void login() throws InterruptedException {
		 
		WebElement emailLogin=driver.findElement(By.cssSelector("[data-qa='login-email']"));
		WebElement passwordLogin=driver.findElement(By.cssSelector("[data-qa='login-password']"));
		WebElement loginButton =driver.findElement(By.cssSelector("[data-qa='login-button']"));
	
		emailLogin.sendKeys(name[numberForName]+numberForEmail+email);
		passwordLogin.sendKeys(Integer.toString(password));
		
		loginButton.click();
		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/");
		
		WebElement user=driver.findElement(By.cssSelector("#header > div > div > div > div.col-sm-8 > div > ul > li:nth-child(10) > a"));
		assertEquals(user.getText(),"Logged in as "+name[numberForName] );

	}
			
	@Test(priority = 6)
	public void deleteAcc() throws InterruptedException {
		driver.navigate().to(Websit+"/delete_account");
		
		Thread.sleep(3000);

		WebElement continueButton =driver.findElement(By.cssSelector("[data-qa='continue-button']"));
		WebElement ShowingTextDelet=driver.findElement(By.cssSelector("#form > div > div > div > h2 > b"));

		assertEquals(ShowingTextDelet.getText(),"ACCOUNT DELETED!");

		continueButton.click();
		
	    Thread.sleep(3000);
        assertEquals(driver.getCurrentUrl(),"https://automationexercise.com/");
		
	}
	
	
	@AfterTest
	public void AfterMyTest() {
		driver.close();
	}
	
	
	
	

}
