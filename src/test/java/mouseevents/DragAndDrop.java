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
import com.microsoft.playwright.options.BoundingBox;

public class DragAndDrop {
	String url = "https://demo.guru99.com/test/drag_drop.html";

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

	@Test
	public void dragAndDropTest() throws InterruptedException {
		ElementHandle fromElement = page.querySelector("//*[@id='credit2']/a");
		ElementHandle toElement = page.querySelector("//*[@id='bank']/li");

		BoundingBox fromBoundingBox = fromElement.boundingBox();
		BoundingBox toBoundingBox = toElement.boundingBox();

		// Calculate the center coordinates of the 'From' and 'To' elements
		int fromCenterX = (int) (fromBoundingBox.x + fromBoundingBox.width / 2);
		int fromCenterY = (int) (fromBoundingBox.y + fromBoundingBox.height / 2);
		int toCenterX = (int) (toBoundingBox.x + toBoundingBox.width / 2);
		int toCenterY = (int) (toBoundingBox.y + toBoundingBox.height / 2);

		// Perform the drag and drop action
		page.mouse().move(fromCenterX, fromCenterY);
		page.mouse().down();
		page.mouse().move(toCenterX, toCenterY);
		page.mouse().up();

		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
