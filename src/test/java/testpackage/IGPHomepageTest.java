package testpackage;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.IGPBaseClass;

public class IGPHomepageTest extends IGPBaseClass{
	
	@Test
	public void verifyHomePageUrlIsCorrect() {
		String actualUrl = driver.getCurrentUrl();
	    System.out.println("Home page url: " + actualUrl);

	    Assert.assertTrue(actualUrl.contains("igp.com"),"Home page url mismatch");
	}
	
	@Test
	public void verifyHomePageTitleIsCorrect() {
		String actualTitle = driver.getTitle();
	    System.out.println("Page Title: " + actualTitle);

	    Assert.assertTrue(actualTitle.contains("IGP"),"Home page title mismatch");
	}
	
	@Test
	public void verifyIGPLogoIsPresent() {
		assertTrue(hp.isAppLogoDisplayed(),"Logo not displayed!");
		System.out.println("Logo is present");
	}
	
	@Test
	public void verifyAllHeaderMenusArePresent() {
		assertTrue(hp.isHamburgerMenuDisplayed(),"Hamburger Menu is missing in the header" );
		assertTrue(hp.isChangeCountryMenuDisplayed(),"Change country Menu is missing in the header" );
		assertTrue(hp.isSearchBarDisplayed(),"Search bar is missing in the header" );
		assertTrue(hp.isGetSelectMenuDisplayed(),"Get select Menu is missing in the header" );
		assertTrue(hp.isReminderMenuDisplayed(),"Reminder Menu is missing in the header" );
		assertTrue(hp.isCurrencyMenuDisplayed(),"Curreny Menu is missing in the header" );
		assertTrue(hp.isWishlistMenuDisplayed(),"Wishlist is missing in the header" );
		assertTrue(hp.isCartIconDisplayed(),"Cart icon is missing in the header" );
		assertTrue(hp.isUserAccountDisplayed(),"User account icon is missing in the header" );		
	}
	
	@Test
	public void verifyAllTopNavigationMenusArePresent() {
		List<WebElement> topNavMenus = hp.getTopNavigationMenus();

        String[] expectedMenus = {"Same Day Delivery", "Cakes", "Flowers", "Personalized", "Plants", "New Arrivals", "International", "Corp. Gifts"};
        
        for (int i = 0; i<expectedMenus.length ; i++) {
        	String menu = topNavMenus.get(i).getText();
            Assert.assertEquals(menu, expectedMenus[i], expectedMenus + " menu is NOT displayed");
            System.out.println("Menu-" + (i+1) + " : " + menu);
        }

        System.out.println("All top navigation menus are present and displayed correctly");
	}
	
	@Test
	public void verifyTheFestiveMenus() {
		List<WebElement> festiveMenus = hp.getFestiveMenus();
		
		for(WebElement fmenu: festiveMenus) {
			String festMenu = fmenu.getText();
		    System.out.println("Festive Menu: " + festMenu);
		}
    }
	
	@Test
	public void verifyUserCanSearchForProduct() {
		//Search for Butterfly Pendant Necklace
		hp.searchForProduct("Butterfly Pendant Necklace");
		
		//verify url
		assertTrue(driver.getCurrentUrl().toLowerCase().contains("butterfly%20pendant%20necklace"),"Search page was not loaded");
		
		//Validate Top 5 results contain keyword
        for (int i=0; i<=5; i++) {
            String productName = hp.getProductTitle().get(i).getText().toLowerCase();

            Assert.assertTrue(productName.contains("pendant") || productName.contains("necklace"), "Search result mismatch → '" + productName);
	}
  }
	
		
	@Test
	public void verifyCartIsEmptyByDefault() {
		hp.openCart();
		assertTrue(cp.emptyCartMessage().isDisplayed(), "Empty cart message is not displayed");
		System.out.println(cp.emptyCartMessage().getText().replace("\n", " "));
	}
	
	@Test
	public void verifyHeartIconTurnsRedOnAddingItemToWishlist() {
		hp.searchForProduct("candles");
		hp.addTwoItemsToWishlist();
		assertTrue(hp.getWishlistIconClassAfterClick(1).contains("active"),"Wishlist icon color/state did not change");
	}
	

	@Test
	public void verifyUserCanOpenAndCloseHamburgerMenu() {

	    // Open hamburger menu
	    hp.openHamburgerMenu();

	    // Verify menu is opened
	    Assert.assertTrue(hp.isHamBurgerMenuOpened(),"Hamburger menu did not open");

	    // Close hamburger menu
	    hp.closeHamburgerMenu();

	    // Verify menu is closed
	    Assert.assertTrue(hp.isHamburgerMenuClosed(),"Hamburger menu did not close");
	}

   

   
}
