package testpackage;

import org.testng.annotations.Test;

import basepackage.IGPBaseClass;

public class IGPLoginTest extends IGPBaseClass{
	
	@Test
	public void login() throws Exception {
		lp.loginWithEmail();
		Thread.sleep(1000);
		lp.verifyUserAccountIconChange();
	}

}
