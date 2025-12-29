package pagepackage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IGPProductDetailsPage {
	WebDriver driver;
	WebDriverWait wait;
	
	@FindBy(id = "country")
    WebElement countryDropdown;
	
	@FindBy(css = "div.country-div")
	WebElement countryDropdownContainer;
	
	@FindBy(xpath = "//span[@class='country-name']")
	List<WebElement> countryList;
	
	@FindBy(id = "location-input") 
	WebElement pincodeField;	
	
	@FindBy(xpath = "//button[@id='available-pincode']")
	WebElement availableButton;
	
    @FindBy(xpath = "//input[@id='std-datepicker']")
    WebElement dateField;
    
    @FindBy(xpath = "//div[@class='ui-datepicker-title']")
    WebElement calendarHeader;
    
    @FindBy(xpath = "//span[@class='ui-icon ui-icon-circle-triangle-e']")
    WebElement nextMonthButton;
    
    @FindBy(id = "std-datepicker")
    WebElement selectedDeliveryDate;
    
    @FindBy(xpath = "//td[@data-handler='selectDay']/a")
    List<WebElement> enabledDates;
    
    @FindBy(xpath = "(//img[contains(@class,'product')])[1]")
    WebElement firstProduct;
    
    @FindBy(xpath = "//h1[contains(@class,'prod-name Heading-5--Bold')]")
    WebElement productTitle;
    
    @FindBy(xpath = "//div[@class='view-width thumbnail-img pdp-page lazy-load-image']")
    List<WebElement> productImages;

    @FindBy(xpath = "//div[contains(@class,'prod-price Paragraph-24-2XL--Semibold  ')]")
    WebElement productPrice;

    @FindBy(xpath = "//div[@class='pdp-desc-cont desc-container desc-link selected']/span")
    WebElement productDescription;

    @FindBy(xpath = "//span[contains(@class,'rating')]")
    WebElement productRating;
    
    @FindBy(xpath = "//button[@id='add-shortlist']")
    WebElement wishlistIcon;
    
       
    
  //Constructor to initialize Product Details Page elements
	public IGPProductDetailsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}
	
	// -----Page Actions-------
	
	public boolean isProductDetailsPageDisplayed() {
	    return productTitle.isDisplayed();
	}

	
	public void selectCountry(String countryName) {

	    // If country already selected, don’t click again
	    if (countryDropdown.getAttribute("value").equalsIgnoreCase(countryName)) {
	        return;
	    }

	    // Click the container, NOT the input
	    wait.until(ExpectedConditions.elementToBeClickable(countryDropdownContainer)).click();

	    wait.until(ExpectedConditions.visibilityOfAllElements(countryList));

	    for (WebElement country : countryList) {
	        if (country.getText().trim().equalsIgnoreCase(countryName)) {
	            wait.until(ExpectedConditions.elementToBeClickable(country)).click();
	            return;
	        }
	    }

	    throw new RuntimeException("Country not found: " + countryName);
	}

	public void enterPincode(String pin) {
		wait.until(ExpectedConditions.elementToBeClickable(pincodeField)).sendKeys(pin);
	}
	
	public WebElement getPincodeField() {
		return pincodeField;
	}

	public String getSelectedLocationText() {
	    return pincodeField.getAttribute("value");
	}
	
	public String getSelectedDeliveryDate() {
	    return selectedDeliveryDate.getAttribute("value");
	}
	
	public boolean isAvailableDisplayed() {
		return availableButton.isDisplayed();
	}
	
	public void openFirstProduct() {
		firstProduct.click();
	}	
	
	public String getProductDetailsTitle() {
	    return productTitle.getText();
	}
	
	public boolean isProductTitleDisplayed() {
	    return productTitle.isDisplayed();
	}
	
	public boolean areProductImagesDisplayed() {
	    return productImages.size() > 0;
	}

	public boolean isProductPriceDisplayed() {
	    return productPrice.isDisplayed();
	}

	public boolean isProductDescriptionDisplayed() {
	    return productDescription.isDisplayed();
	}

	public boolean isProductRatingDisplayed() {
	    return productRating.isDisplayed();
	}

	
	public void selectDeliveryDate() {
		wait.until(ExpectedConditions.visibilityOf(dateField)).click();
		
		if (!enabledDates.isEmpty()) {
            enabledDates.get(0).click();   // select first available date
        }

        // if no enabled dates, move to next month
        nextMonthButton.click();
    }
	
	public void addProductToWishlist() {
	    wishlistIcon.click();
	}

	public String isProductAddedToWishlist() {
		 wait.until(ExpectedConditions.attributeContains(wishlistIcon, "class", "active"));
	      String className = wishlistIcon.getAttribute("class");

	      return className;
	}
	
	public boolean isDeliveryDateSectionHighlighted() {
	    return dateField.getAttribute("class").contains("error");
	}


}
	
	
