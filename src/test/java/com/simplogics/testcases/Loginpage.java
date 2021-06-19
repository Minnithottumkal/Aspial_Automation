package com.simplogics.testcases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

public class Loginpage extends BaseData{
	@Test(dataProviderClass=TestUtil.class,dataProvider = "dp",priority = 2)
	public void loginpage(String username,String password,String Y) throws InterruptedException, MalformedURLException, IOException{
		test=extent.createTest("Loginpage");
		if(!(TestUtil.isTestRunnable("Loginpage", excel))){
			
			throw new SkipException("Skipping the test "+"Loginpage".toUpperCase()+ "as the Run mode is NO");
		}
		
		
		driver.navigate().refresh();
		Thread.sleep(5000);
		driver.findElement(By.id(OR.getProperty("Username_ID"))).clear();
		driver.findElement(By.id(OR.getProperty("Password_ID"))).clear();
		type("Username_ID",(username));
		type("Password_ID",("11/11/1111"));
		click("Login_CSS");
		//Asserttoast(OR.getProperty("expected_login_toast"),"Title_XPATH");
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("jewelleryentity_CSS"))));
		Thread.sleep(5000);
		Thread.sleep(5000);
		click("jewelleryentity_CSS");
		click("suaccountelmogoh_CSS");
		Thread.sleep(5000);

		
		
	}
}
