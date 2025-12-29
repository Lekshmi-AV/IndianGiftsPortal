package testpackage;


import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basepackage.IGPBaseClass;

public class IGPReminderTest extends IGPBaseClass{
	
	@BeforeClass
	public void login() {
		lp.loginWithEmail();
	}
	
	@Test
	public void verifyUserCanOpenReminderPageFromHeader() {
		rp.openReminderPage();
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = "https://www.igp.com/reminders";
		Assert.assertEquals(actualUrl, expectedUrl, "Reminder page url mismatch");
	}
	@Test
	public void verifyUserCanAddANewReminder() {		
		rp.openReminderPage();
		rp.enterReminderName("Priyanka's Birthday");
		rp.selectOccasion("Birthday");
		rp.selectDayFromCalendar("14");
		rp.selectRelation("Friend");
		rp.saveReminder();
		assertTrue(rp.isSuccessMessageDisplayed(), "Success message not displayed");
	}
	
	@Test
	public void verifyUserIsAbleToDeleteReminder() throws Exception {

	    rp.openReminderPage();
	    rp.enterReminderName("Wedding Anniversary");
	    rp.selectOccasion("Anniversary");
	    rp.selectDayFromCalendar("25");
	    rp.saveReminder();

	    int beforeCount = rp.getReminderCount();
	    Assert.assertTrue(beforeCount > 0, "No reminders available to delete");
	    
	    rp.clickMoreOptions(0);
	    rp.deleteReminder(0);

	    By reminderItemsLocator = By.xpath("//div[contains(@class,'reminder_card')]");
		wait.until(ExpectedConditions.numberOfElementsToBe(reminderItemsLocator, beforeCount - 1));
	    int afterCount = rp.getReminderCount();

	    Assert.assertEquals(afterCount, beforeCount - 1, "Reminder was not deleted successfully");
	}

	
	
}
