package com.simplogics.base;
import static org.testng.Assert.assertEquals;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.mail.EmailException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.simplogics.utilities.ExcelReader;
import org.apache.log4j.Logger;


public class BaseData extends Email {

	public static WebDriver driver;
	public static ChromeOptions options;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public JavascriptExecutor js = (JavascriptExecutor) driver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel = new ExcelReader(
			System.getProperty("user.dir") + "/src/test/Files/excel/testdata3.xlsx");
	public static WebDriverWait wait;
	// public ExtentReports rep = ExtentManager.getInstance();
	// public static ExtentTest test;
	public static String browser;
	public WebElement cell;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	
	@BeforeMethod(alwaysRun = true)
	public void setReport() throws EmailException {
		// System.out.println("running before test.....");
		
		htmlReporter = new ExtentHtmlReporter(
				System.getProperty("user.dir") + "/target/surefire-reports/html/Aspial_Test_Result.html");
		// extent = new
		// ExtentReports(System.getProperty("user.dir")+"/target/surefire-reports/html/extent.html",true,DisplayOrder.OLDEST_FIRST);
		htmlReporter.setAppendExisting(true);// works only for aventstack 3.1.5 dependency and relevantcodes 2.41.2
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Automation Tester", "Minni S Thottumkal");
		extent.setSystemInfo("Orgainzation", "Simplogics");


	}

	//

	@BeforeMethod
	// @BeforeSuite
	public void setUp() {
		// BasicConfigurator.configure();

		if (driver == null) {
			try {
				// test = new ExtentTest("Demo", "");
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				System.out.println("Config file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream(System.getProperty("user.dir") + "/src/test/resources/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				System.out.println("OR file loaded !!!");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.setProperty("webdriver.chrome.logfile",
					"/home/appus/Desktop/SMTP/Aspial_Automation/target/surefire-reports/html/Aspial_Test_Log.log");
			System.setProperty("webdriver.chrome.verboseLogging", "true");
			// String log4jConfPath =
			// "/home/appus/eclipse-workspace/Atidee/src/test/resources/log4j.properties";
			// PropertyConfigurator.configure("log4j.properties");

			// System.setProperty("org.freemarker.loggerLibrary", "none");
			// PropertyConfigurator.configure(BaseData.class.getProtectionDomain().getCodeSource().getLocation().getPath()
			// + "log4j.properties");

			if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {

				browser = System.getenv("browser");
			} else {

				browser = config.getProperty("browser");

			}

			config.setProperty("browser", browser);

			if (config.getProperty("browser").equals("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "/src/test/Files/executables/geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (config.getProperty("browser").equals("chrome")) {

				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/src/test/Files/executables/chromedriver");
				//options = new ChromeOptions();
				//options.setHeadless(true);
				//driver = new ChromeDriver(options);
				driver = new ChromeDriver();
				driver.manage().window().setSize(new Dimension(1600,700));
				
				System.out.println("Chrome Launched !!!");
			} else if (config.getProperty("browser").equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "/src/test/Files/executables/IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}
			driver.get(config.getProperty("testsiteurl"));
			// log.debug("Navigated to : " + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			// driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),
			// TimeUnit.SECONDS);
			// wait = new WebDriverWait(driver, 5);
		}
	}

	public static void click(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).click();
		} else if (locator.endsWith("_linkText")) {
			driver.findElement(By.linkText(OR.getProperty(locator))).click();
		}
		// test.log(Status.INFO, "Clicking on : " + locator);
	}

	public void type(String locator, String value) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		test.log(Status.INFO, "Typing in : " + locator + " entered value as " + value);
	}

	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void AssertEquals(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected);
		} catch (Throwable t) {
			test.log(Status.FAIL, "Verifiation of TestData failed:" + t.getMessage());
			test.fail("Please check the below Screenshot :",
					MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
		}
	}
	public static void AssertEqual(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected,"Second Checkbox should be Ticked when Untick the first checkbox only   ");
		} catch (Throwable t) {
			test.log(Status.FAIL, "Verifiation of TestData failed:" + t.getMessage());
			test.fail("Please check the below Screenshot :",
					MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
		}
	}


	public static void AssertEqualss(String expected, String actual) throws IOException {
		try {
			Assert.assertEquals(actual, expected,"Total Quantity and total weight should not be 0  ");
		} catch (Throwable t) {
			test.log(Status.FAIL, "Verifiation of TestData failed:" + t.getMessage());
			test.fail("Please check the below Screenshot :",
					MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
		}
	}
	
	public static void verifytoast(String expectedtoast) throws IOException {
		try {
		String appearedtoast = driver.findElement(By.cssSelector("#b9-Content > div > div.layout--space-between.margin--bottom__2 > label > span")).getText();
		assertEquals(appearedtoast, expectedtoast);
		}
		catch (Throwable t) {
			test.log(Status.FAIL, "Verifiation of invoice Details failed:" + t.getMessage());
			test.fail("Please check the below Screenshot :",
					MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
		}
	}
	public static void AssertSame(String expected,String actual) throws IOException {
		try {
		
		assertEquals(actual, expected,"Invoice should not be saved without invoice currency values     ");
		}
		catch (Throwable t) {
			test.log(Status.FAIL, "Verifiation of required contents in invoice edit page failed:" + t.getMessage());
			test.fail("Please check the below Screenshot :",
					MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
		}
	}
	public static void Assertcheckbox(String expected,String actual) throws IOException{
		try {
			
			assertEquals(actual, expected,"Invoice should not be saved without ticking checkbox    ");
			}
			catch (Throwable t) {
				test.log(Status.FAIL, "Verifiation of invoice creation without checkbox selection failed:" + t.getMessage());
				test.fail("Please check the below Screenshot :",
						MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());
			}
	}
	
	public static void Asserttoast(String expectedtoast, String locator) {
		if (locator.endsWith("_CSS")) {
			String appearedtoast = driver.findElement(By.cssSelector(OR.getProperty(locator))).getText();
			assertEquals(appearedtoast, expectedtoast);
		} else if (locator.endsWith("_XPATH")) {
			String appearedtoast = driver.findElement(By.xpath(OR.getProperty(locator))).getText();
			assertEquals(appearedtoast, expectedtoast);
		} else if (locator.endsWith("_ID")) {
			String appearedtoast = driver.findElement(By.id(OR.getProperty(locator))).getText();
			assertEquals(appearedtoast, expectedtoast);
		} else if (locator.endsWith("_linkText")) {
			String appearedtoast = driver.findElement(By.linkText(OR.getProperty(locator))).getText();
			assertEquals(appearedtoast, expectedtoast);
		}

	}


	public void clearthetextboxfield(String locator) {
		if (locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
		} else if (locator.endsWith("_XPATH")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
		} else if (locator.endsWith("_ID")) {
			driver.findElement(By.cssSelector(OR.getProperty(locator))).clear();
		}
	}
	
	

	@AfterMethod
	public void tearDown(ITestResult result) throws Exception {
		if (test != null) {

			if (result.getStatus() == ITestResult.FAILURE) {
				test.log(Status.FAIL, "Testcase failed : " + result.getName());

				test.log(Status.FAIL, "Failure Response: " + result.getThrowable());
				test.fail("Please check the below Screenshot :",
						MediaEntityBuilder.createScreenCaptureFromBase64String(getbase64()).build());

			} else if (result.getStatus() == ITestResult.SKIP) {
				test.log(Status.SKIP, "Testcase skipped :" + result.getName());
			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.log(Status.PASS, "Testcase success : " + result.getName());

			}
		}
		extent.flush();

	}

	public static String getbase64() {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

	}

	@AfterTest
	public static void Emailsend() throws EmailException, InterruptedException {
		Thread.sleep(5000);
		Thread.sleep(5000);
		driver.close();
		//email();
		//File f= new
		// File("/home/appus/Desktop/SMTP/Aspial_Automation/target/surefire-reports/html/Aspial_Test_Result.html");
		// f.delete();
	}

}
