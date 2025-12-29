package basepackage;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import pagepackage.IGPCartPage;
import pagepackage.IGPGiftCustomizationPage;
import pagepackage.IGPHomePage;
import pagepackage.IGPLoginPage;
import pagepackage.IGPProductDetailsPage;
import pagepackage.IGPReminderPage;
import pagepackage.IGPWishlistPage;

public class IGPBaseClass {
	
	public static ExtentSparkReporter reporter;
	public static ExtentTest testExt;
	public static ExtentReports extReports;
	
	public WebDriver driver;
	public WebDriverWait wait;
	public IGPLoginPage lp;
	public IGPHomePage hp;
	public IGPCartPage cp;
	public IGPWishlistPage wp;
	public IGPProductDetailsPage pdp;
	public IGPGiftCustomizationPage gcp;
	public IGPReminderPage rp;
	public Actions act;
	
	@BeforeSuite
	public void setUp() {		
		reporter = new ExtentSparkReporter("./Report/myReport.html");
		reporter.config().setDocumentTitle("AutomationReport");
		reporter.config().setReportName("Functional Test");
		reporter.config().setTheme(Theme.DARK);
		
		extReports = new ExtentReports();
		extReports.attachReporter(reporter);
		extReports.setSystemInfo("hostname", "localhost");
		extReports.setSystemInfo("os", "windows11");
		extReports.setSystemInfo("testername", "abc");
		extReports.setSystemInfo("Browser name", "Chrome");			
	}
	
	@BeforeClass(alwaysRun = true)
	public void initializePages() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.igp.com/");
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		
		lp = new IGPLoginPage(driver);
		hp = new IGPHomePage(driver);
		cp = new IGPCartPage(driver);
		wp = new IGPWishlistPage(driver);
		pdp = new IGPProductDetailsPage(driver);
		rp = new IGPReminderPage(driver);
		gcp = new IGPGiftCustomizationPage(driver);
		//act = new Actions(driver);		
	}	
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(Method meth) {
	    testExt = extReports.createTest(meth.getName());
	}

	
	@AfterMethod(alwaysRun = true)
	public void afterMthd(ITestResult result) {

	    if (testExt == null) return; // safety guard

	    if (result.getStatus() == ITestResult.FAILURE) {
	        testExt.log(Status.FAIL, "Testcase failed is " + result.getName());
	        testExt.log(Status.FAIL, result.getThrowable());
	    }
	    else if (result.getStatus() == ITestResult.SKIP) {
	        testExt.log(Status.SKIP, "Testcase skipped is " + result.getName());
	    }
	    else if (result.getStatus() == ITestResult.SUCCESS) {
	        testExt.log(Status.PASS, "Testcase passed is " + result.getName());
	    }
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownClass() {
	    if (driver != null) {
	        driver.quit();
	    }
	}

	
	@AfterSuite
    public void tearDownSuite() {
        extReports.flush();
    }

}
