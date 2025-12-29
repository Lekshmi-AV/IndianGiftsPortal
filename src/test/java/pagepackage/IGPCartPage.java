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

 public class IGPCartPage {
    WebDriver driver;
    WebDriverWait wait;
       
    @FindBy(xpath = "//div[contains(@class,'oh-text text-center Heading-3--Bold')]")	
    WebElement emptyCartMesg;    
    
    @FindBy(xpath = "//button[@id='add-cart']")
    WebElement addToCartButton;   
    
    @FindBy(xpath = "//div[contains(@data-product-type,'main')]")
    List<WebElement> cartItems;
    
    @FindBy(xpath = "//input[@class='qty number c-item-qty-val c-item-qty-val-revamp Paragraph-16-M--Medium']")
    List<WebElement> itemQuantity;
    
    @FindBy(xpath = "//img[contains(@alt,'plus')]")
    List<WebElement> plusButtonOfCartItems;
    
    @FindBy(xpath = "//img[contains(@alt,'minus')]")
    List<WebElement> minusButtonOfCartItems;
    
    @FindBy(xpath = "//span[@id='cart-count']")
    WebElement cartCount;
    
    @FindBy(xpath = "//span[@class='inr s-total-val s-total-val-revamp Paragraph-20-XL--Semibold ']")
    List<WebElement> priceOfEachItem;
    
    @FindBy(xpath = "//span[@id='cart-total-val']")
    WebElement totalCartPrice;
    
    @FindBy(xpath = "//div[contains(@id,'cart-item-remove')]/img")
    List<WebElement> removeItemButtons;
    
    @FindBy(xpath = "//div[contains(@class,'c-item-overlay') and contains(@class,'c-show')]")
    WebElement removeConfirmationPopup;

    @FindBy(xpath = "//a[@class='remove-c' and normalize-space()='YES']")
    WebElement yesBtn;
    
    @FindBy(xpath = "//div[@id='enabled-button']/a")
    WebElement proceedToCheckoutButton;
    
    @FindBy(xpath = "//a[@title='Cart']//div[@class='icon-wrapper top-action-icons quick-menu']")
    WebElement headerCarticon;
    
    @FindBy(xpath = "//img[contains(@alt,'shortlist icon')]")
    List<WebElement> wishlistIconsOfCartItems;

    
  //Constructor to initialize Cart Page elements
	public IGPCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	}

	// -----Page Actions-------
	public void openCart() {
		wait.until(ExpectedConditions.visibilityOf(headerCarticon)).click();
	}
	
	public WebElement emptyCartMessage() {
		return emptyCartMesg;
	}
	
	public void clickAddToCart() {		
		addToCartButton.click();
	}
	
	public By getCartItemsLocator() {
	    return By.xpath("//div[contains(@class,'c-items-revamp flex-row')]"); 
	}
	
	public int getCartItemCount() {
		return cartItems.size();
	}
	
	public void removeItemFromCart(int index) {
	    // Click delete icon of item
	    removeItemButtons.get(index).click();

	    // Wait for confirmation popup
	    wait.until(ExpectedConditions.visibilityOf(removeConfirmationPopup));

	    // Click YES
	    yesBtn.click();
	}

	
	public int itemQuantity(int index) {
		String countText = itemQuantity.get(index).getAttribute("value").trim();
	    return Integer.parseInt(countText);
	}
	
	public void increaseItemQuantityInCart(int index) {
		plusButtonOfCartItems.get(index).click();
	}
	
	public void decreaseItemQuantityInCart(int index) {
		minusButtonOfCartItems.get(index).click();
	}
	
	public int getCartIconCount() {
		String countText = cartCount.getText().trim();
		int cartCount = Integer.parseInt(countText);
		return cartCount;
	}
	
	public int getPriceOfEachItem(int index) {
	    String priceText = priceOfEachItem.get(index).getText().replaceAll("[^0-9]", "").trim();
	    return Integer.parseInt(priceText);
	}
	
	public int getTotalCartPrice() {
	    String totalPriceText = totalCartPrice.getText().replaceAll("[^0-9]", "").trim();
	    return Integer.parseInt(totalPriceText);
	}
	
	public void clickProceedToCheckout() {
	    proceedToCheckoutButton.click();
	}
	
	public void moveItemToWishlistFromCart(int index) {
	    wait.until(ExpectedConditions.visibilityOfAllElements(wishlistIconsOfCartItems)).get(index).click();
	}

}
