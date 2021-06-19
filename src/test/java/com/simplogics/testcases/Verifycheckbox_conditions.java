package com.simplogics.testcases;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

@Test(dataProviderClass=TestUtil.class,dataProvider = "dp",priority = 8)
public class Verifycheckbox_conditions extends BaseData {
	public void VerifyCheckbox_conditions(String DocRefNo, String PONo, String TotalQty, String TotalWeight, String WMCP,
			String TotalGold, String Invoice, String ExchangeRate)
			throws InterruptedException, MalformedURLException, IOException {
		test = extent.createTest("VerifyCheckbox_conditions");
		 SoftAssert s = new SoftAssert();
		if (!(TestUtil.isTestRunnable("VerifyCheckbox_conditions", excel))) {

			throw new SkipException("Skipping the test " + "VerifyCheckbox_conditions" + " as the Run mode is NO");
		}
		Thread.sleep(5000);
		Thread.sleep(5000);
		click("invoicemenu_CSS");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("invoice_CSS"))));
		Thread.sleep(5000);
		click("invoice_CSS");
		Thread.sleep(3000);
		click("Jewellery_CSS");
		Thread.sleep(5000);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector("#Dropdown5 > .dropdown-display")).click();
		driver.findElement(By.xpath("//option[. = 'Singapore']")).click();		
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"Dropdown5\"]/select/option[21]")).click();
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("#\\$b7 .choices__inner .choices__item")).click();
		driver.findElement(By.xpath("//*[@id=\"choices--b7-DropdownSelect-item-choice-5\"]")).click();
		Thread.sleep(3000);
		//driver.findElement(By.id("choices--b7-DropdownSelect-item-choice-1")).click();
		driver.findElement(By.cssSelector("#\\$b9 .choices__inner .choices__item")).click();
		driver.findElement(By.xpath("//*[@id=\"choices--b9-DropdownSelect-item-choice-2\"]")).click();
		type("DocRefNo_CSS", (DocRefNo));
		type("PONo_CSS", (PONo));
		type("TotalQty_CSS", (TotalQty));
		type("TotalWeight_CSS", (TotalWeight));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		type("WMCP_CSS", (WMCP));
		type("TotalGold_CSS", (TotalGold));
		type("Invoice_CSS", (Invoice));
		Thread.sleep(5000);
		driver.findElement(By.xpath(OR.getProperty("ExchangeRate_XPATH"))).clear();
		type("ExchangeRate_XPATH", (ExchangeRate));
		click("checkbox0_CSS");
		if(driver.findElement(By.cssSelector("#l7-1-Checkbox3")).isSelected()) {
			AssertEquals("Checkbox ticked", "Checkbox ticked");
						
		}
		else {
			AssertEqual("Second Checkbox ticked", "Second Checkbox Unticked");
			Thread.sleep(5000);
		}
	}

}
