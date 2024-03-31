package OrangeHRMLive.ActionClasses;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import OrangeHRMLive.DataProvider.OrangeHRM_Login;
import OrangeHRMLive.PageClasses.LoginPage;
import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;

public class LoginAction extends OrangeHRM_BaseClass  {
	
	LoginPage lp;
	
	@BeforeMethod
	public void setup() throws IOException, InterruptedException
	{
		setUp();
	}
	
	@Test(dataProvider="LoginDataProvider", dataProviderClass=OrangeHRM_Login.class)
	public void LoginTest(String uname, String pass, String testType)
	{
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		lp= new LoginPage(driver);
		System.out.println(uname +" "+pass);
		
		lp.username.sendKeys(uname);
		lp.password.sendKeys(pass);
		lp.submit.click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		//wait.until(ExpectedConditions.titleIs("OrangeHRM"));
		
		if(testType.equalsIgnoreCase("positive"))
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h6[text()= \"Dashboard\"]"))));
		
		else
			wait.until(ExpectedConditions.visibilityOf(lp.wrongCreds));
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.close();
	}

}
