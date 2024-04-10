package locators.dynamicxpath;

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

public class LocateByLast {
	protected static String url = "https://www.tutorialspoint.com/selenium/practice/webtables.php";

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
	public void clickMethod() throws InterruptedException {
		ElementHandle xpathByLast = page.querySelector("//table[@class='table table-striped mt-3']/tbody/tr[last()]/td[4]");
		System.out.println(xpathByLast.textContent());
		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
