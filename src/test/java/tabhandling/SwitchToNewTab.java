package tabhandling;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SwitchToNewTab {
	protected static String url = "https://www.tutorialspoint.com/selenium/practice/menu.php#";

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
	
	@Test
	public void switchToTab() throws InterruptedException {
		Page firstTab = context.newPage();
		firstTab.navigate("https://www.testingtherapy.com/");
		Thread.sleep(3000);
		
		Page secondTab = context.newPage();
		secondTab.bringToFront();
		secondTab.navigate("https://www.google.com/");
		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
