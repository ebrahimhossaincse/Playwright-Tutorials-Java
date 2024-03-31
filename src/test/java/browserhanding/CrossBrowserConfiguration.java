package browserhanding;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CrossBrowserConfiguration {
	
	Playwright playwright;
	BrowserType browserType;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;

	public void launchPlaywright(String browserName, String headless) {
		playwright = Playwright.create();
		if (browserName.equalsIgnoreCase("chrome")) {
			browserType = playwright.chromium();
		} else if (browserName.equalsIgnoreCase("webkit")) {
			browserType = playwright.webkit();
		}

		if (headless.equalsIgnoreCase("true")) {
			browser = browserType.launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(true));
		} else {
			browser = browserType.launch(new BrowserType.LaunchOptions().setChannel(browserName).setHeadless(false));
		}
		context = browser.newContext(new Browser.NewContextOptions());
		page = browser.newPage();
		System.out.println("**** Project Browser Name and Version is : " + browserName + " : " + browser.version());

	}

	@SuppressWarnings("static-access")
	public void launchApplication(String url) {
		page.navigate(url);
	}

	@Test(priority = 0)
	public void openURL(@Optional("https://www.google.com") String url, @Optional("chrome") String browserName,
			@Optional("false") String headless) throws InterruptedException {
		launchPlaywright(browserName, headless);
		launchApplication(url);
		Thread.sleep(3000);
	}

	@AfterSuite
	public void closePlaywright() {
		page.close();
		browser.close();
		playwright.close();
	}
}
