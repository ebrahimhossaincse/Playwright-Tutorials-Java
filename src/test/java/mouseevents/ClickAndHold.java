package mouseevents;

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

public class ClickAndHold {
	String url = "https://www.tutorialspoint.com/selenium/practice/menu.php#";

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
		Thread.sleep(5000);
	}

	@Test
	public void clickAndHoldTest() throws InterruptedException {
		ElementHandle navbar = page.querySelector(".navbar-brand");

		if (navbar != null) {
			String beforeColor = navbar.evaluate("element => window.getComputedStyle(element).color").toString();
			System.out.println("Color of element before click and hold: " + beforeColor);

			navbar.hover();
			page.mouse().down();
			page.waitForTimeout(3000);
			page.mouse().up();

			String afterColor = navbar.evaluate("element => window.getComputedStyle(element).color").toString();
			System.out.println("Color of element after click and hold: " + afterColor);
		}
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
