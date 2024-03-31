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
import com.microsoft.playwright.options.WaitUntilState;

public class RetrievePageSourceOfCurrentWebPage {
	protected static String url = "https://demoqa.com/automation-practice-form";
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

    @SuppressWarnings("static-access")
	@BeforeClass
	public void openUrl() throws InterruptedException{
		page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED.NETWORKIDLE));
		Thread.sleep(3000);
	}

	@Test(priority = 0)
	public void fetchPageSource() throws InterruptedException {
		String pageSource = page.content();
		System.out.println(pageSource);
		Thread.sleep(1000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
