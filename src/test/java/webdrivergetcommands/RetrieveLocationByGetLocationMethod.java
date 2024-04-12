package webdrivergetcommands;

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
import com.microsoft.playwright.options.BoundingBox;

public class RetrieveLocationByGetLocationMethod {

	String url = "https://selenium08.blogspot.com/2020/01/drag-me.html";
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
	public void fetchLocation() throws InterruptedException {
		ElementHandle srcElement = page.querySelector("#draggable");

		if (srcElement != null) {
			BoundingBox srcBoundingBox = srcElement.boundingBox();
			if (srcBoundingBox != null) {
				int srcCenterX = (int) (srcBoundingBox.x);
				int srcCenterY = (int) (srcBoundingBox.y);

				System.out.println("Element coordinates:");
				System.out.println("X: " + srcCenterX);
				System.out.println("Y: " + srcCenterY);
			} else {
				System.out.println("Bounding box not available.");
			}
		} else {
			System.out.println("Element not found.");
		}

		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
