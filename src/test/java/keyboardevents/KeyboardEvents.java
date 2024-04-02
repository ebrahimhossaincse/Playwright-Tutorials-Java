package keyboardevents;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class KeyboardEvents {
	protected static String url = "https://www.tutorialspoint.com/selenium/practice/register.php";

	Playwright playwright;
	BrowserType browserType;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;

	@BeforeSuite
	public void startChromeBrowser() {
		playwright = Playwright.create();
		browserType = playwright.chromium();
		browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser.newContext(new Browser.NewContextOptions());

		page = browser.newPage();
		System.out.println("**** Chrome Browser Version is : " + browser.version());
	}

	@BeforeClass
	public void openUrl() throws InterruptedException {
		page.navigate(url);
		page.waitForLoadState();
	}

	@Test
	public void keyBoardEvent() throws InterruptedException {
		ElementHandle firstName = page.querySelector("#firstname");
		firstName.click();
		page.keyboard().type("Ebrahim");
		
        // Copy the text
        page.keyboard().down("Control");
        page.keyboard().press("KeyA");
        page.keyboard().up("Control");
        page.keyboard().down("Control");
        page.keyboard().press("KeyC");
        page.keyboard().up("Control");
		Thread.sleep(5000);
		
		page.keyboard().press("Tab");
		
		// Paste the text
        page.keyboard().down("Control");
        page.keyboard().press("KeyV");
        page.keyboard().up("Control");
        Thread.sleep(5000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
