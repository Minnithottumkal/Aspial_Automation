package com.simplogics.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

public class Invalid_Login extends BaseData {
	@Test(dataProviderClass=TestUtil.class,dataProvider = "dp",priority = 1)
	public void InvalidLogin(String username,String password) throws InterruptedException {
		test=extent.createTest("InvalidLogin");
		if(!(TestUtil.isTestRunnable("InvalidLogin", excel))){
			
			throw new SkipException("Skipping the test "+"InvalidLogin".toUpperCase()+ "as the Run mode is NO");
		}
	
		Thread.sleep(5000);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(OR.getProperty("Username_ID"))));
		type("Username_ID",(username));
		type("Password_ID",("11/11/111"));
		click("Login_CSS");
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("invalid_CSS"))));
		Asserttoast(OR.getProperty("expected_invalidlogin_toast"),"invalid_CSS");
		test.log(Status.PASS, "The assertion of invalid login is success");
		
	}
	

}
