package pagepackage;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IGPGiftCustomizationPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//button[@id='personalize-revamp']")
	WebElement personalizeButton;
	
	@FindBy(xpath = "//a[@id='opener']")
	WebElement uploadImageBtn;	
	
	@FindBy(xpath = "//input[@type='file' and contains(@class,'cloudinary_fileupload')]")
	WebElement browseButton;
	
	@FindBy(xpath = "//img[@class='ReactCrop__image']")
	WebElement imagePreview;
	
	By nextButton = By.xpath("//button[@data-test='skip-button']");
	
	By saveButtonContainer = By.xpath("//div[contains(@class,'steps-action-buttons') and not(@hidden)]");
	By saveAndReviewBtn = By.xpath("//a[normalize-space()='Save & Review']");
	
	@FindBy(xpath = "//div[@class='view-width thumbnail-img pdp-page relative']/img")
	WebElement uploadedPhoto;
	
	By uploadFrame = By.xpath("//iframe[contains(@src,'cloudinary')]");

	@FindBy(xpath = "//input[@type='file']")
    WebElement uploadImageInput;
	
	@FindBy(xpath = "//input[@placeholder='Enter your text here']")
	WebElement personalizeTextField;
	
	@FindBy(xpath = "//button[normalize-space()='Save']")
	WebElement saveButton;
	
	
	//Constructor to initialize Homepage elements
		public IGPGiftCustomizationPage(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this);
			wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		}
		
		// -----Page Actions-------
		
		 public void clickPersonalize() {
		        wait.until(ExpectedConditions.elementToBeClickable(personalizeButton)).click();
		    }
		 
		 public void clickUploadImage() {
		        wait.until(ExpectedConditions.elementToBeClickable(uploadImageBtn)).click();
		    }

		 public void switchToCustomizationFrameIfPresent() {
		        List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
		        if (!iframes.isEmpty()) {
		            driver.switchTo().frame(iframes.get(0));
		        }
		    }

		    public void uploadPhoto(String imagePath) {
		        switchToCustomizationFrameIfPresent();
		        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
		        uploadImageInput.sendKeys(imagePath);
		    }

		    public boolean isImagePreviewDisplayed() {
		        return wait.until(ExpectedConditions.visibilityOf(imagePreview)).isDisplayed();
		    }

		    public void clickNext() {
		        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
		        wait.until(ExpectedConditions.invisibilityOfElementLocated(nextButton));
		    }
		    
		    public void saveUploadedImage() {
		    	driver.switchTo().defaultContent();
		    	WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(saveAndReviewBtn));
		    	System.out.println("Save button found in main DOM");
		    	wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
			}
		    
		    public boolean isImageUploaded() {
		    	return wait.until(ExpectedConditions.visibilityOf(uploadedPhoto)).isDisplayed();
		    }
		    
		    public void enterText(String text) {
				wait.until(ExpectedConditions.visibilityOf(personalizeTextField)).sendKeys(text);
				wait.until(ExpectedConditions.visibilityOf(saveButton)).click();
			}
		    
		    public void takeScreenshot(String fileName) throws Exception {
		    	File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		        String path = System.getProperty("user.dir") + "/screenshots/" + fileName + ".png";
		        FileHandler.copy(src, new File(path));
			}
	}
		
		


