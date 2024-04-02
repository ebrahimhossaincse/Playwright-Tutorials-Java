package webdrivergetcommands;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class RetrieveCurrentURLOfCurrentWebPage {
	protected static String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
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
	public void openUrl() throws InterruptedException{
		page.navigate(url);
		page.waitForLoadState();
	}

	@Test(priority = 0)
	public void fetchCurrentURL() throws InterruptedException {
		String currentURL = page.url();
		System.out.println(currentURL);
		Thread.sleep(1000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
