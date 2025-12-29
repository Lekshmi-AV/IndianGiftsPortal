package testpackage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.IGPBaseClass;

public class IGPWishlistTest extends IGPBaseClass{
	
	@Test
	public void verifyWishlistPageTitleIsCorrect() {
		hp.openWishlist();
		String actualTitle = "Shortlist";
		assertEquals(driver.getTitle(), actualTitle, "Wishlist title mismatch");
	}
	
	@Test(priority = -1)
	public void verifyWishlistIsEmptyWhenNoItemsAdded() {
		hp.openWishlist();
		assertTrue(wp.isEmptyWishlistMessageDisplayed(), "Wishlist seems to be empty message missing");
	}
	
	@Test
	public void verifyUserCanAddItemToWishlist() {
		hp.searchForProduct("Plants");
		hp.addTwoItemsToWishlist();
		String[] expectedItems = hp.getTitlesOfWishlistedItems();
		
		//verify that added items are present in the wishlist 
		hp.openWishlist();
		String[] actualItems =hp.itemsInWishlist();
		
		List<String> expectedList = Arrays.asList(expectedItems);
		List<String> actualList = Arrays.asList(actualItems);
		
		for (String expected : expectedList) {
		    Assert.assertTrue(actualList.contains(expected),"Wishlisted item missing → " + expected);		
	  }
	}
	
	@Test
	public void verifyUserCanRemoveItemFromWishlist() throws Exception {
		hp.searchForProduct("balloon decors");
		hp.addTwoItemsToWishlist();
		hp.openWishlist();
		int initialWishlistCount = wp.getWishlistItemCount();
		wp.removeItemFromWishlist(0);
		Thread.sleep(1000);
		assertEquals(wp.getWishlistItemCount(),initialWishlistCount - 1,"Item was not removed from cart");
	}	
	
	
	@Test
	public void verifyUserCanNavigateToProductDetailsFromWishlist() {
	    hp.searchForProduct("gourmet");
	    hp.addTwoItemsToWishlist();
	    hp.openWishlist();

	    wp.openProductDetailsFromWishlist(0); 
	    Assert.assertTrue(pdp.isProductDetailsPageDisplayed(),"User was not navigated to Product Details Page from Wishlist");
	    driver.navigate().back();
	    
	    wp.openProductDetailsFromWishlist(1);
	    Assert.assertTrue(pdp.isProductDetailsPageDisplayed(),"User was not navigated to Product Details Page from Wishlist");
	}

	
	

}
