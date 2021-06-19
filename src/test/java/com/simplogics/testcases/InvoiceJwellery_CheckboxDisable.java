package com.simplogics.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

@Test(dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 6)
public class InvoiceJwellery_CheckboxDisable extends BaseData {
	public void Invoicejwellery_CheckboxDisable(String DocRefNo, String PONo, String TotalQty, String TotalWeight,
			String WMCP, String TotalGold, String Invoice, String ExchangeRate)
			throws InterruptedException, MalformedURLException, IOException {
		test = extent.createTest("Invoicejwellery_CheckboxDisable");
		SoftAssert s = new SoftAssert();
		if (!(TestUtil.isTestRunnable("Invoicejwellery_CheckboxDisable", excel))) {

			throw new SkipException(
					"Skipping the test " + "Invoicejwellery_CheckboxDisable" + " as the Run mode is NO");
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
		// driver.findElement(By.id("choices--b7-DropdownSelect-item-choice-1")).click();
		driver.findElement(By.cssSelector("#\\$b9 .choices__inner .choices__item")).click();
		driver.findElement(By.xpath("//*[@id=\"choices--b9-DropdownSelect-item-choice-2\"]")).click();
		type("DocRefNo_CSS", (DocRefNo));
		type("PONo_CSS", (PONo));
		type("TotalQty_CSS", (TotalQty));
		type("TotalWeight_CSS", (TotalWeight));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		click("checkbox0_CSS");
		type("WMCP_CSS", (WMCP));
		type("TotalGold_CSS", (TotalGold));
		type("Invoice_CSS", (Invoice));
		Thread.sleep(5000);
		driver.findElement(By.xpath(OR.getProperty("ExchangeRate_XPATH"))).clear();
		type("ExchangeRate_XPATH", (ExchangeRate));
		if (driver.findElement(By.cssSelector("#l7-2-Checkbox3")).isSelected()) {
			WebElement xinvoice = driver.findElement(By.cssSelector(OR.getProperty("invoicesgd_CSS")));
			String invoice = xinvoice.getAttribute("value");
			// System.out.println("Invoice:" + invoice);
			String XTotalnetinvoicecurrency = driver
					.findElement(By.cssSelector(OR.getProperty("Totalnetinvoicecurrency_CSS"))).getText();
			String Totalnetinvoicecurrency = String.valueOf(Float.valueOf(XTotalnetinvoicecurrency));
			System.out.println("Actual Total net Invoice Currency (SGD)" + Totalnetinvoicecurrency);
			String TotalnetinvoicecurrencyExpected = String.valueOf(Float.valueOf(invoice));
			s.assertEquals(TotalnetinvoicecurrencyExpected, Totalnetinvoicecurrency,
					"Total Net Invoice Currency (SGD):");
			String XTotalnet = driver.findElement(By.cssSelector(OR.getProperty("Totalnet_CSS"))).getText();
			String Totalnet = String.valueOf(Float.valueOf(XTotalnet));
			System.out.println("Actual Total Local Invoice Currency (SGD)" + Totalnet);
			String TotalnetExpected = String.valueOf(Float.valueOf(invoice) * Float.valueOf(ExchangeRate));
			s.assertEquals(TotalnetExpected, Totalnet, "Total Net Local Invoice Currency (SGD):");
			click("Save_CSS");
			Thread.sleep(5000);

			Assertcheckbox("Checkbox should be ticked", "Checkbox not ticked");

			s.assertAll();
			Thread.sleep(5000);

		} else {
			System.out.println("Total Invoice Cost does not match up with sum of costs");
			click("Save_CSS");
			assertEquals("Total Invoice Cost does not match up with sum of costs",
					"Total Invoice Cost should match up with sum of costs");
		}

	}

}
