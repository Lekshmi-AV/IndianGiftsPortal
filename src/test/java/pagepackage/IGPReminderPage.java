package pagepackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IGPReminderPage {
	WebDriver driver;
	WebDriverWait wait;
	
	 @FindBy(xpath = "//img[@alt='Reminder']")
	 WebElement reminderIcon;
	
	 @FindBy(id = "name")
	 WebElement nameField;
	 
	 @FindBy(xpath = "//div[@class='save-name-cta Paragraph-16-M--Regular enabledState']")
	 WebElement saveNameButton;

	 @FindBy(xpath = "//span[@class='reminder_date_span reminder_date_default Paragraph-14-S--Regular']")
	 WebElement dateField;
	 
	 @FindBy(xpath = "//td[@data-handler='selectDay']/a")
	 List<WebElement> calendarDates;

	 @FindBy(xpath = "//div[@class='error_state_msg Paragraph-12-XS--Regular hide']")
	 WebElement dateErorMessage;
	 
	 @FindBy(xpath = "//button[normalize-space()='save & add']")
	 WebElement saveButton;

	 @FindBy(xpath = "//div[@data-successmessage='Your reminder has been saved!']")
	 WebElement successMessage;
	 
	 @FindBy(xpath = "//img[contains(@src,'reminder-dots.svg')]")
	 List<WebElement> moreOptionsMenu;
	 
	 @FindBy(xpath = "//div[@class='reminder_extra_delete_cont']")
	 List<WebElement> deleteButtons;
	 
	 @FindBy(xpath = "//div[contains(@class,'reminder_overlay_delete_cta Paragraph-16-M--Regular')][normalize-space()='Delete']")
	 WebElement confirmDeleteButton;
	 
	 @FindBy(xpath = "//div[contains(@class,'reminder_cards  relative')]")
	 List<WebElement> reminderCards;	 
	 
	
	
	//Constructor to initialize Reminder Page elements
			public IGPReminderPage(WebDriver driver) {
				this.driver = driver;
				PageFactory.initElements(driver, this);
				wait = new WebDriverWait(driver, Duration.ofSeconds(20));
			}
			
			// -----Page Actions-------
			
			public void openReminderPage() {
				wait.until(ExpectedConditions.elementToBeClickable(reminderIcon)).click();
			}
			
			public void enterReminderName(String name) {
				nameField.sendKeys(name);
				wait.until(ExpectedConditions.elementToBeClickable(saveNameButton)).click();
			}
			
			public void selectOccasion(String occasion) {
			    String xpath = "//span[normalize-space()='" + occasion + "']";
			    WebElement occasionOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			    occasionOption.click();
			}
			
			public void selectDayFromCalendar(String expectedDay) {
				
				dateField.click();
			   for (WebElement day : calendarDates) {
			      if (day.getText().equals(expectedDay)) {
			            day.click();
			            break;
			        }
			    }
			}

			
			public void selectRelation(String relation) {
			    String xpath = "//div[@data-relation='" + relation.toLowerCase() + "']";
			    WebElement relationOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
			    relationOption.click();
			}
			
			public void saveReminder() {
				saveButton.click();
			}
			
			public boolean isSuccessMessageDisplayed() {
				return wait.until(ExpectedConditions.visibilityOf(successMessage)).isDisplayed();
			}
			
			public void clickMoreOptions(int index) {
				int attempts = 0;

			    while (attempts < 3) {
			        try {
			            List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElements(moreOptionsMenu));
			            WebElement element = options.get(index);
			            wait.until(ExpectedConditions.elementToBeClickable(element));

			            // JS click avoids stale during React re-render
			            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

			            return; // success

			        } catch (StaleElementReferenceException e) {
			            attempts++;
			        }
			    }

			    throw new RuntimeException("Failed to click More Options due to stale DOM");
			}
			
			public void deleteReminder(int index) {
				int attempts = 0;

			    while (attempts < 3) {
			        try {
			            // ALWAYS re-fetch the list
			            List<WebElement> deletes = wait.until(ExpectedConditions.visibilityOfAllElements(deleteButtons));

			            WebElement deleteBtn = deletes.get(index);

			            // JS click avoids React re-render issues
			            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteBtn);

			            break;

			        } catch (StaleElementReferenceException e) {
			            attempts++;
			        }
			    }

			    // Confirm delete (also React-safe)
			    WebElement yes = wait.until(ExpectedConditions.elementToBeClickable(confirmDeleteButton));

			    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", yes);
		    }
			
			public int getReminderCount() {
				return reminderCards.size();
			}

}
