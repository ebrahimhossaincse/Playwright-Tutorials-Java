package windowhandling;

import java.util.List;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CountTheWindows {
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
	public void countWindows() throws InterruptedException {
		page.navigate("https://www.testingtherapy.com/");
		Thread.sleep(3000);
		
		Page secondTab = browser.newContext().newPage();
		secondTab.bringToFront();
		secondTab.navigate("https://www.google.com/");
		Thread.sleep(3000);
		
		List<BrowserContext> allContexts = browser.contexts();
        int totalWindows = 0;
        for (BrowserContext ctx : allContexts) {
            totalWindows += ctx.pages().size();
        }

        System.out.println("Total number of windows: " + totalWindows);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
