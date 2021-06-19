package com.simplogics.testcases;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.simplogics.base.BaseData;
import com.simplogics.utilities.TestUtil;

@Test(priority = 7)
public class Verifycontents_Editpage extends BaseData {
	public void VerifyContents_Editpage() throws InterruptedException, IOException {
		test = extent.createTest("VerifyContents_Editpage");
		SoftAssert s = new SoftAssert();
		if (!(TestUtil.isTestRunnable("VerifyContents_Editpage", excel))) {

			throw new SkipException("Skipping the test " + "VerifyContents_Editpage" + " as the Run mode is NO");
		}
		// Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("edit_CSS"))));
		click("edit_CSS");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(OR.getProperty("DocRefNo_CSS"))));
		click("DocRefNo_CSS");
		click("PONo_CSS");
		click("TotalQty_CSS");
		WebElement element = driver.findElement(By.cssSelector(".active-screen"));
		Actions builder = new Actions(driver);
		builder.moveToElement(element).clickAndHold().perform();
		click("WMCP_CSS");
		click("TotalGold_CSS");
		Thread.sleep(5000);
		click("Save_CSS");
		AssertSame("Invoice Currency values should not be empty",
				"Invoice currency Values of WMCP and Total Gold are missing");
		Thread.sleep(5000);
		Thread.sleep(5000);
	}

}
