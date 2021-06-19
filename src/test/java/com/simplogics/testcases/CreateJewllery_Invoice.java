package com.simplogics.testcases;

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

import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

@Test(dataProviderClass = TestUtil.class, dataProvider = "dp", priority = 4)
public class CreateJewllery_Invoice extends BaseData {
	public void Createjewllery_Invoice(String DocRefNo, String PONo, String TotalQty, String TotalWeight, String WMCP,
			String TotalGold, String Invoice, String ExchangeRate)
			throws InterruptedException, MalformedURLException, IOException {
		test = extent.createTest("Createjewllery_Invoice");
		SoftAssert s = new SoftAssert();
		if (!(TestUtil.isTestRunnable("Createjewllery_Invoice", excel))) {

			throw new SkipException("Skipping the test " + "Createjewllery_Invoice" + " as the Run mode is NO");
		}
		Thread.sleep(5000);
		click("invoicemenu_CSS");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("invoice_CSS"))));
		Thread.sleep(5000);
		click("invoice_CSS");
		Thread.sleep(5000);
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
		Thread.sleep(5000);
		driver.findElement(By.xpath(OR.getProperty("ExchangeRate_XPATH"))).clear();
		type("ExchangeRate_XPATH", (ExchangeRate));
		// driver.findElement(By.xpath(OR.getProperty("ExchangeRate_XPATH"))).sendKeys("2");
		WebElement xExchangerate = driver.findElement(By.xpath(OR.getProperty("ExchangeRate_XPATH")));
		String Exchangerate = xExchangerate.getAttribute("value");
		System.out.println("Exchange Rate:" + Exchangerate);
		Thread.sleep(5000);
		driver.findElement(By.cssSelector(OR.getProperty("Xlocalwmcpsgd_CSS"))).click();
		String Xlocalwmcpsgd = driver.findElement(By.cssSelector(OR.getProperty("Xlocalwmcpsgd_CSS"))).getText();
		String localwmcpsgd = String.valueOf(Float.valueOf(Xlocalwmcpsgd));
		//System.out.println("Actual WMCP Invoice Currency (SGD)" + localwmcpsgd);
		String wmcplocalinvoiceCurrency = String.valueOf(Float.valueOf(WMCP) * Float.valueOf(Exchangerate));
		//System.out.println("Expected Local invoice currency of WMCPSGD:" + wmcplocalinvoiceCurrency);
		s.assertEquals(wmcplocalinvoiceCurrency, localwmcpsgd);
		// AssertEquals(wmcplocalinvoiceCurrency,localwmcpsgd);
		// totalgold calculation
		String Xlocaltotalgold = driver.findElement(By.cssSelector(OR.getProperty("Xlocaltotalgold_CSS")))
				.getText();
		String localtotalgold = String.valueOf(Float.valueOf(Xlocaltotalgold));
		//System.out.println("Actual Totalgold Invoice Currency (SGD)" + localtotalgold);
		String localinvoicecurrencyTotalgoldExpected = String
				.valueOf(Float.valueOf(TotalGold) * Float.valueOf(Exchangerate));
		//System.out.println("Expected TotalGoldSGD:" + localinvoicecurrencyTotalgoldExpected);
		s.assertEquals(localinvoicecurrencyTotalgoldExpected, localtotalgold);
		// Total Net calculation
		if (driver.findElement(By.cssSelector("#l7-0-Checkbox3")).isSelected()) {
			String XTotalnet = driver.findElement(By.cssSelector(OR.getProperty("Totalnet_CSS"))).getText();
			String Totalnet = String.valueOf(Float.valueOf(XTotalnet));
			System.out.println("Actual Total Local Invoice Currency (SGD)" + Totalnet);
			String TotalnetExpected = String.valueOf(
					Float.valueOf(wmcplocalinvoiceCurrency) + Float.valueOf(localinvoicecurrencyTotalgoldExpected));
			// System.out.println(TotalnetExpected);
			s.assertEquals(TotalnetExpected, Totalnet, "Total Net Local Invoice Currency (SGD):");
			WebElement xinvoice = driver.findElement(By.cssSelector(OR.getProperty("invoicesgd_CSS")));
		String invoice = xinvoice.getAttribute("value");
			//System.out.println("Invoice:" + invoice);

			// Invoiceccurrency sgd totalnett
			String XTotalnetinvoicecurrency = driver
					.findElement(By.cssSelector(OR.getProperty("Totalnetinvoicecurrency_CSS"))).getText();
			String Totalnetinvoicecurrency = String.valueOf(Float.valueOf(XTotalnetinvoicecurrency));
			System.out.println("Actual Total net Invoice Currency (SGD)" + Totalnetinvoicecurrency);
			String TotalnetinvoicecurrencyExpected = String.valueOf(Float.valueOf(invoice));
			// System.out.println(TotalnetinvoicecurrencyExpected);
			s.assertEquals(TotalnetinvoicecurrencyExpected, Totalnetinvoicecurrency,
					"Total Net Invoice Currency (SGD):");
			// AssertEquals(TotalnetinvoicecurrencyExpected,Totalnetinvoicecurrency);
			click("Save_CSS");
			Thread.sleep(5000);
			//Asserttoast(OR.getProperty("expected_invoicecreate_toast"),"Invoice_create_toast_CSS");
			Thread.sleep(5000);
			Thread.sleep(5000);
			//verifytoast("Invoice Details");
			
			s.assertAll();
			Thread.sleep(5000);
		}

	}


}