package webdrivernavigationcommands;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ForwardCommand {
protected static String url = "https://demoqa.com/";
	
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

	@Test(priority = 0)
	public void forwardCommand() throws InterruptedException {
		Locator option = page.locator("//div[@class='home-body']/div/div[1]");
		option.click();
		Thread.sleep(3000);
		page.goBack();
		Thread.sleep(3000);
		page.goForward();
		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
