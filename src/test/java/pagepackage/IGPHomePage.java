package pagepackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IGPHomePage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(xpath = "//img[@class='img-responsive logo']")
	WebElement appLogo;
	
	@FindBy(xpath = "//div[@class='icon-wrapper gc-header-icon igp-hamburger-icon']/img")
	WebElement hamBurgerMenuIcon;
	
	@FindBy(xpath = "//div[@class='mm-content tree no-scrollbar']")
	WebElement hamBurgerMenu;
	
	@FindBy(xpath = "//img[@src='https://cdnnew.igp.com/assets/svg-icons/close-mm-icon.svg']")
	WebElement hamburgerCloseButton;
	
	@FindBy(xpath = "//div[@class='changeCountry_content']")
	WebElement changeCountry;
	
	@FindBy(xpath = "//input[@placeholder='Country']")
    WebElement countryDropdown;
	
	@FindBy(xpath = "//li[contains(@class,'country-list-item')]")
	List<WebElement> countryList;
	
	@FindBy(xpath = "//input[@id='location-input']") 
	WebElement pincodeField;	
	
	@FindBy(xpath = "//button[normalize-space()='Apply']")
	WebElement applyButton;
	
	@FindBy(xpath = "//input[@name='q']")
	WebElement searchBar;
		
	@FindBy(xpath = "//a[contains(@title,'Get Select')]")
	WebElement getSelectMenu;
	
	@FindBy(xpath = "//div[@class='icon-wrapper top-action-icons quick-menu']//img[@alt='Reminder']")
	WebElement reminderIcon;
	
	@FindBy(xpath = "//img[@id='currency-black']")
	WebElement currencyMenu;
	
	@FindBy(xpath = "//div[@title='Shortlist']")
	WebElement wishlistIcon;
	
	@FindBy(xpath = "//a[@title='Cart']")
	WebElement cartIcon;
	
	@FindBy(xpath = "//div[@title='Account']")
	WebElement accountIcon;
	
	@FindBy(xpath = "//div[@class='sel-pnl-re sel-pnl-revamp']/a")
	List<WebElement> topNavigationMenus;
	
	@FindBy(xpath = "//div[@data-widget-name='Festive Widget']/a")
	List<WebElement> festiveMenus;
	
	@FindBy(xpath = "//p[@class='product-name product-name-revamp Paragraph-14-S--Semibold']")
	List<WebElement> productTitles;
	
	@FindBy(xpath = "//div[contains(@class,'product-name-w product-name-w-revamp')]/p/a")
	List<WebElement> wishlistTitles;
	
	@FindBy(xpath = "//div[contains(@id,'heart-icon')]")
	List<WebElement> wishlistIcons;
	
	
	
	//Constructor to initialize Homepage elements
	public IGPHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	// -----Page Actions-------
	
	public boolean isAppLogoDisplayed() {
	    return wait.until(ExpectedConditions.visibilityOf(appLogo)).isDisplayed();
	}

	
	public boolean isHamburgerMenuDisplayed() {
		return wait.until(ExpectedConditions.visibilityOf(hamBurgerMenuIcon)).isDisplayed();
	}
	
	public boolean isChangeCountryMenuDisplayed() {
		return changeCountry.isDisplayed();
	}
	
	public boolean isSearchBarDisplayed() {
		return searchBar.isDisplayed();
	}
	
	public boolean isGetSelectMenuDisplayed() {
		return getSelectMenu.isDisplayed();
	}
	
	public boolean isReminderMenuDisplayed() {
		return reminderIcon.isDisplayed();
	}
	
	public boolean isCurrencyMenuDisplayed() {
		return currencyMenu.isDisplayed();
	}
	
	public boolean isWishlistMenuDisplayed() {
		return wishlistIcon.isDisplayed();
	}
	
	public boolean isCartIconDisplayed() {
		return cartIcon.isDisplayed();
	}
	
	public boolean isUserAccountDisplayed() {
		return accountIcon.isDisplayed();
	}
	
	public List<WebElement> getTopNavigationMenus() {
		return topNavigationMenus;
	}
	
	public List<WebElement> getFestiveMenus() {
		return festiveMenus;
	}
	
	public void openCart() {
		cartIcon.click();
	}
	
	public void selectCountry(String countryName) {
		wait.until(ExpectedConditions.visibilityOf(changeCountry));
	    wait.until(ExpectedConditions.elementToBeClickable(changeCountry)).click();
		wait.until(ExpectedConditions.invisibilityOfElementLocated( By.cssSelector("div.tr-overlay.global-location")));
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].click();", countryDropdown);
        wait.until(ExpectedConditions.visibilityOfAllElements(countryList));

        for (WebElement country : countryList) {
            if (country.getText().equalsIgnoreCase(countryName)) {
                country.click();
                break;
            }
        }
    }
	
	public void enterPincode(String pin) {
		wait.until(ExpectedConditions.visibilityOf(pincodeField)).sendKeys(pin);
    }
	
	public void clickApplyButton() {
		applyButton.click();
	}
	
	public void searchForProduct(String item) {
        searchBar.clear();
        searchBar.sendKeys(item + Keys.ENTER);
    }
	
	public List<WebElement> getProductTitle() {
		return productTitles;
	}
	
	public void addTwoItemsToWishlist() {
	    for (int i = 0; i < 2; i++) {
	        wishlistIcons.get(i).click();
	    }
	}

	public String[] getTitlesOfWishlistedItems() {        
            String[] wishlistedItems = new String[2];

            for (int i = 0; i < 2; i++) {
            	wishlistedItems[i] = productTitles.get(i).getText().toLowerCase();
           }
            return wishlistedItems;
	}
	
	public void openWishlist() {
		wait.until(ExpectedConditions.visibilityOf(wishlistIcon)).click();
	}
	
	public String[] itemsInWishlist() {
		   String[] wishlistItems = new String[2];

         for (int i = 0; i < 2; i++) {
        	wishlistItems[i] = wishlistTitles.get(i).getText().toLowerCase();
         }
           return wishlistItems;
	}
	
	public String getWishlistIconClassAfterClick(int index) {

	      WebElement wishlistIcon = wishlistIcons.get(index);
	      wait.until(ExpectedConditions.attributeContains(wishlistIcon, "class", "active"));
	      String className = wishlistIcon.getAttribute("class");

	      return className;
	}
	
	public void openProduct(int index) {
	    productTitles.get(index).click();
	}
	
	public void openHamburgerMenu() {
		wait.until(ExpectedConditions.elementToBeClickable(hamBurgerMenuIcon)).click();
	}
	
	public boolean isHamBurgerMenuOpened() {
		return wait.until(ExpectedConditions.visibilityOf(hamBurgerMenu)).isDisplayed();
	}
	
	public void closeHamburgerMenu() {
	    wait.until(ExpectedConditions.elementToBeClickable(hamburgerCloseButton)).click();
	}

	public boolean isHamburgerMenuClosed() {
	    return wait.until(ExpectedConditions.invisibilityOf(hamBurgerMenu));
	}
	


}
