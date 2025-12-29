package pagepackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IGPWishlistPage {
    WebDriver driver;
    WebDriverWait wait;
    
    @FindBy(xpath = "//span[contains(@class,'shortlist-subheader') and normalize-space(text())='Your wishlist seems to be empty.']")	
    WebElement emptyCartMesg;
    
    @FindBy(xpath = "//div[contains(@class,'product-grid-item product-grid-item-revamp col s3 slist-item')]")
    List<WebElement> wishlistItems;
    
    @FindBy(xpath = "//div[contains(@id,'heart-icon')]")
	List<WebElement> wishlistIcons;
    
    @FindBy(xpath = "//span[contains(text(),'Confirm')]")
    WebElement confirmRemoveFromWishlistButton;    
    
    @FindBy(xpath = "//span[@class='Heading-5--Bold']")
    WebElement wishlistHeadingCount;
    
    @FindBy(xpath = "//p[contains(@class,'product-name product-name-revamp Paragraph-14-S--Semibold')]//a")
    List<WebElement> wishlistProductLinks;
    
    By wishlistItemsLocator = By.xpath("//div[contains(@class,'slist-item')]");




	//Constructor to initialize Wishlist Page elements
	public IGPWishlistPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	// -----Page Actions-------
	
	public boolean isWishlistEmpty() {
	    return wishlistItems.size() == 0;
	}

	public void clearWishlist() {
	    while (wishlistItems.size() > 0) {
	        wishlistIcons.get(0).click();
	        wait.until(ExpectedConditions.visibilityOf(confirmRemoveFromWishlistButton)).click();
	        wait.until(ExpectedConditions.stalenessOf(wishlistItems.get(0)));
	    }
	}


	//Get wishlist empty message 
	public boolean isEmptyWishlistMessageDisplayed() {
	    return wait.until(ExpectedConditions.visibilityOf(emptyCartMesg)).isDisplayed();
	}
	
	public By getWishlistItemsLocator() {
	    return wishlistItemsLocator;
	}
	
	//Returns total number of items present in Wishlist
	public int getWishlistItemCount() {
		wait.until(ExpectedConditions.visibilityOfAllElements(wishlistItems));
        return wishlistItems.size();
    }
	
	public void removeItemFromWishlist(int index) {
		wishlistIcons.get(index).click();
		wait.until(ExpectedConditions.visibilityOf(confirmRemoveFromWishlistButton)).click();
	}
	
	public int getWishlistCount() {
	    // Example text: "My wishlist (2)"
	    String text = wishlistHeadingCount.getText();
	    return Integer.parseInt(text.replaceAll("\\D+", ""));     //......\\D+ removes everything except digits.
	}
	
	public void openProductDetailsFromWishlist(int index) {
	    wishlistProductLinks.get(index).click();
	}



}
