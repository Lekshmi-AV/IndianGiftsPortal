package pagepackage;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IGPLoginPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//div[@title='Account']")
	WebElement accountMenu;
	
	@FindBy(xpath = "//input[@placeholder='Email or Mobile Number']")
	WebElement emailField;
	
	@FindBy(xpath = "//button[normalize-space()='Next']")
	WebElement nextButton;
	
	@FindBy(xpath = "//a[normalize-space()='Login via Password']")
	WebElement chooseLoginViaPassword;
	
	@FindBy(id = "passwd")
	WebElement passwordField;
	
	@FindBy(xpath = "//button[@class='btn-main password-submit revamp-btn-main Paragraph-14-S--Medium']")
	WebElement submitButton;
	
	@FindBy(xpath = "//div[@id='user-menu']//span[contains(text(),'T')]")
	WebElement userIcon;
	
	public IGPLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	public void loginWithEmail() {
		accountMenu.click();
		emailField.sendKeys("testingpurpose25user@gmail.com");
		nextButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(chooseLoginViaPassword)).click();
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys("abc@1234");
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
	}
	
	public void verifyUserAccountIconChange() {
		wait.until(ExpectedConditions.visibilityOf(userIcon));
	}

}
