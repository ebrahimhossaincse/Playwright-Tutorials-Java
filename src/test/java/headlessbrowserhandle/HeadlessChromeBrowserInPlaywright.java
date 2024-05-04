package headlessbrowserhandle;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class HeadlessChromeBrowserInPlaywright {
	protected static String url = "https://www.testingtherapy.com/";

	Playwright playwright;
	BrowserType browserType;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;

	@BeforeSuite
	public void startChromeBrowserInHeadlessMode() {
		playwright = Playwright.create();
		browserType = playwright.chromium();
		browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true));
		context = browser.newContext(new Browser.NewContextOptions());
		
		page = browser.newPage();
		System.out.println("**** Chrome Browser Version is : " + browser.version());
	}

	@Test
	public void openUrl() throws InterruptedException {
		page.navigate(url);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}

}
