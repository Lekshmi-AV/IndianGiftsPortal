package testpackage;

import org.testng.Assert;
import org.testng.annotations.Test;

import basepackage.IGPBaseClass;

public class IGPGiftCustomizationTest extends IGPBaseClass{
	
	@Test
	public void verifyUserCanCustomizePhotoCakeWithImageUpload() throws Exception {
		hp.searchForProduct("photo cake");
		pdp.openFirstProduct();
		
		 gcp.clickPersonalize();
	     gcp.clickUploadImage();
	     
	     gcp.uploadPhoto(System.getProperty("user.dir") + "/src/test/resources/testdata/cake.jpg");
	     Assert.assertTrue(gcp.isImagePreviewDisplayed(),"Uploaded image preview is not displayed");

	     gcp.clickNext();
	     Thread.sleep(2000);
	     gcp.saveUploadedImage();
	     Assert.assertTrue(gcp.isImageUploaded(),"Uploaded image is not displayed");
	     Thread.sleep(5000);
		 gcp.takeScreenshot("PhotoCake");
	    }
	
	@Test
	public void verifyNamePersonalizationOnProduct() throws Exception {
		hp.searchForProduct("Floral Travel Mug");
		pdp.openFirstProduct();
		
		gcp.clickPersonalize();
		gcp.enterText("Cyndrella");
		Thread.sleep(5000);
		gcp.takeScreenshot("PersonalizedMug");
	}

}
