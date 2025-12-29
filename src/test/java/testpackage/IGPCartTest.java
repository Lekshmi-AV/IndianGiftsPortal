package testpackage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import basepackage.IGPBaseClass;

public class IGPCartTest extends IGPBaseClass{
	
	@BeforeClass
    public void setupCartWithItem() {
		
        hp.searchForProduct("Butterfly Pendant Necklace");
        pdp.openFirstProduct();
        pdp.selectCountry("India");
        hp.enterPincode("695035");
        pdp.selectDeliveryDate();
        cp.clickAddToCart();
        
        hp.searchForProduct("keychain holders");
		pdp.openFirstProduct();
		pdp.selectDeliveryDate();
		cp.clickAddToCart();
    }	
	
	@Test(priority = 1)
	public void verifyUserCanAddItemToCart() {			
		hp.openCart();
		assertEquals(cp.getCartItemCount(), 2 , "Expected 2 items in cart but found " + cp.getCartItemCount());
	}
	
	@Test(priority = 2)
	public void verifyUserCanRemoveItemFromCart() throws Exception {
		hp.openCart();
		
		int initialCount = cp.getCartItemCount();
	    cp.removeItemFromCart(0);
	    Thread.sleep(1000);

	    assertEquals(cp.getCartItemCount(),initialCount - 1,"Item was not removed from cart");
	}
	
	
	@Test(priority = 3)
	public void verifyUserCanIncreaseItemQuantityInCart() throws Exception {
		hp.openCart();
		int before = cp.itemQuantity(0);
		cp.increaseItemQuantityInCart(0);	
		Thread.sleep(1000);
		assertEquals(cp.itemQuantity(0), before+1 , "Item Quantity in cart didnot increase");
	}
	
	@Test(priority = 4)
	public void verifyUserCanDecreaseItemQuantityInCart() throws Exception {
		hp.openCart();
		cp.increaseItemQuantityInCart(0);
		int before = cp.itemQuantity(0);
		cp.decreaseItemQuantityInCart(0);
		Thread.sleep(1000);
		assertEquals(cp.itemQuantity(0), before-1 , "Item Quantity in cart didnot decrease");
	}	
	
	@Test(priority = 5)
	public void verifyCartIconCountUpdatesCorrectly() throws Exception {
		hp.openCart();
	    int initialCount = cp.getCartIconCount();

	    cp.increaseItemQuantityInCart(0);
	    Thread.sleep(1000);
	    assertEquals(cp.getCartIconCount(),initialCount + 1,"Cart icon count did not update correctly");
	}	
	
	@Test(priority = 6)
	public void verifyTotalPriceIsCalculatedCorrectly() throws Exception {
		hp.openCart();

        // initial values
        int initialTotal = cp.getTotalCartPrice();
        int itemPrice = cp.getPriceOfEachItem(0);

        // increase quantity
        cp.increaseItemQuantityInCart(0);

       // expected total = initial + item price
       int expectedTotal = initialTotal + itemPrice;
       Thread.sleep(1000);
       assertEquals(cp.getTotalCartPrice(),expectedTotal,"Total price not updated correctly after quantity increase");
	}
	
	@Test(priority = 7)
	public void verifyUserCanNavigateBackToShoppingFromCart() {		    
	    hp.openCart();
	    driver.navigate().back();

	    assertTrue(driver.getCurrentUrl().contains("p-key-chain-holder"),"User was not navigated back using browser back");
	}
	
	@Test(priority = 8)
	public void verifyUserCanProceedToCheckoutFromCart() {
		hp.openCart();

	    cp.clickProceedToCheckout();

	    assertTrue(driver.getCurrentUrl().contains("checkout"),"Checkout page URL not loaded");
	}

	

}
