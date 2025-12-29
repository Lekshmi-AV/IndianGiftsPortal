package testpackage;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.IGPBaseClass;

public class IGPProductDetailsTest extends IGPBaseClass{
	
	@Test
	public void verifyUserIsNavigatedToProductDetailsPage() {
	    hp.searchForProduct("flowers");
	    pdp.openFirstProduct();
	    
	    assertTrue(pdp.isProductDetailsPageDisplayed(),"User was not navigated to Product Details Page");	    
	}
	
	
	@Test
	public void verifyCorrectProductDetailsPageIsDisplayed() {
	    hp.searchForProduct("perfumes");
	    String expectedProductName = hp.getProductTitle().get(0).getText();
	    hp.openProduct(0);
	    
	    String actualProductName = pdp.getProductDetailsTitle();
	    Assert.assertEquals(actualProductName,expectedProductName,"Incorrect Product Details Page is displayed");
	}
	
		
	@Test
	public void verifyProductDetailsAreDisplayed() {
	    hp.searchForProduct("planners");
        pdp.openFirstProduct();

        Assert.assertTrue(pdp.isProductTitleDisplayed(),"Product title is not displayed");
        Assert.assertTrue(pdp.areProductImagesDisplayed(),"Product images are not displayed");
        Assert.assertTrue(pdp.isProductPriceDisplayed(),"Product price is not displayed");
        Assert.assertTrue(pdp.isProductDescriptionDisplayed(),"Product description is not displayed");
        Assert.assertTrue(pdp.isProductRatingDisplayed(),"Product rating is not displayed");	    
	}
	
	
	@Test
	public void verifyUserCanSelectDeliveryLocation() {
		driver.get("https://www.igp.com");
		hp.searchForProduct("idol");
        pdp.openFirstProduct();
        
        pdp.selectCountry("India");
        pdp.enterPincode("695035");      
        
        wait.until(ExpectedConditions.attributeToBeNotEmpty(pdp.getPincodeField(), "value"));
        Assert.assertTrue(pdp.getSelectedLocationText().contains("695035"),"Delivery location was not selected successfully");	    
	}
	
	
	@Test
	public void verifyUserCanSelectDeliveryDate() {
		hp.searchForProduct("Plushie");
	    pdp.openFirstProduct();

	    // Location must be selected before date
	    pdp.selectCountry("India");
	    hp.enterPincode("695035");

	    pdp.selectDeliveryDate();
	    Assert.assertFalse(pdp.getSelectedDeliveryDate().isEmpty(),"Delivery date was not selected successfully");	    
	}
	
	
	@Test
	public void verifyUserCanAddProductToWishlistFromProductDetailsPage() throws Exception {
	    hp.searchForProduct("Cups");
        pdp.openFirstProduct();
        pdp.addProductToWishlist();
        Thread.sleep(1000);

        assertTrue(pdp.isProductAddedToWishlist().contains("active"),"Product was not added to wishlist from Product Details Page");
        
        hp.openWishlist();
         Assert.assertTrue(wp.getWishlistItemCount() > 0,"Wishlist count did not update after adding product");	    
	}
	
	

}
