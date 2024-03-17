package OrangeHRMLive.ActionClasses;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import OrangeHRMLive.PageClasses.LoginPage;
import orangeHRMLive.BaseClass.OrangeHRM_BaseClass;

public class LoginAction extends OrangeHRM_BaseClass  {
	
	LoginPage lp;
	
	@BeforeTest
	public void setup() throws IOException, InterruptedException
	{
		setUp();
	}
	
	@Test
	public void LoginTest()
	{
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		lp= new LoginPage(driver);
		lp.username.sendKeys("Admin");
		lp.password.sendKeys("admin123");
		lp.submit.click();
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleIs("OrangeHRM"));
		
	}
	
	@AfterTest
	public void tearDown()
	{
		driver.close();
	}

}
