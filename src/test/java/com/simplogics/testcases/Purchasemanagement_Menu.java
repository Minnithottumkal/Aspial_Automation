package com.simplogics.testcases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

public class Purchasemanagement_Menu extends BaseData{
	@Test(priority = 3)
	public void Purchasemanagement_menu()throws InterruptedException, MalformedURLException, IOException {
		test=extent.createTest("Purchasemanagement_menu");
		if(!(TestUtil.isTestRunnable("Purchasemanagement_menu", excel))){
			
			throw new SkipException("Skipping the test "+"Purchasemanagement_menu"+ " as the Run mode is NO");
		}
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("Purchasemanagement_CSS"))));
		click("Purchasemanagement_CSS");
		test.log(Status.PASS, "Purchase management selected successfully");

		
		
	}

}
