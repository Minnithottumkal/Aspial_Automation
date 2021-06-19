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

@Test(dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 9)
public class InvoiceJwellery_Negative extends BaseData {
	public void Invoicejwellery_Negative(String DocRefNo, String PONo, String TotalQty, String TotalWeight, String WMCP,
			String TotalGold, String Invoice, String ExchangeRate)
			throws InterruptedException, MalformedURLException, IOException {
		test = extent.createTest("Invoicejwellery_Negative");
		 SoftAssert s = new SoftAssert();
		if (!(TestUtil.isTestRunnable("Invoicejwellery_Negative", excel))) {

			throw new SkipException("Skipping the test " + "Invoicejwellery_Negative" + " as the Run mode is NO");
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
		click("Invoice_CSS");
		//type("Invoice_CSS", (Invoice));
		//Thread.sleep(5000);
		driver.findElement(By.xpath(OR.getProperty("ExchangeRate_XPATH"))).clear();
		type("ExchangeRate_XPATH", (ExchangeRate));

		if (WMCP.contains("-") || TotalGold.contains("-") || Invoice.contains("-") || TotalQty.contains("-")
				|| TotalWeight.contains("-") || ExchangeRate.contains("-")) {
			click("Save_CSS");
			//verifytoast("Negative values not allowed");
			Thread.sleep(5000);
			Thread.sleep(5000);
			Thread.sleep(5000);
			//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("detail_CSS"))));
			AssertEquals("Positive Values", "Negative Values");
			
			
			
		}
	}
}
