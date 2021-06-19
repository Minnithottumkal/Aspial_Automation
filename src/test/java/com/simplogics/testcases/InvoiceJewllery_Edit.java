package com.simplogics.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;


@Test(priority=5)
public class InvoiceJewllery_Edit extends BaseData{
	public void Invoicejewllery_Edit() throws InterruptedException, IOException {
		test = extent.createTest("Invoicejewllery_Edit");
		 SoftAssert s = new SoftAssert();
		if (!(TestUtil.isTestRunnable("Invoicejewllery_Edit", excel))) {

			throw new SkipException("Skipping the test " + "Invoicejewllery_Edit" + " as the Run mode is NO");
		}
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("edit_CSS"))));
		click("edit_CSS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("TotalQty_CSS"))));
		driver.findElement(By.cssSelector(OR.getProperty("TotalQty_CSS"))).clear();
		driver.findElement(By.cssSelector(OR.getProperty("TotalQty_CSS"))).sendKeys("0");
		test.log(Status.INFO, "Typing in :  TotalQty entered value as " + 0);
		driver.findElement(By.cssSelector(OR.getProperty("TotalWeight_CSS"))).clear();
		driver.findElement(By.cssSelector(OR.getProperty("TotalWeight_CSS"))).sendKeys("0");	
		test.log(Status.INFO, "Typing in :  TotalWeight entered value as " + 0);
		click("Save_CSS");
		AssertEqualss("Positive Number", "0");
		
	}

}
