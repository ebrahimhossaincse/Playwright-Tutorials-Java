package mouseevents;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Mouse;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.BoundingBox;

public class DragAndDropByOffset {
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

	@Test
	public void dragAndDropByOffset() throws InterruptedException {
		ElementHandle srcElement = page.querySelector("#draggable");

		// Get the bounding box of the element
		BoundingBox srcBoundingBox = srcElement.boundingBox();

		// Calculate the center of the element
		int srcCenterX = (int) (srcBoundingBox.x + srcBoundingBox.width / 2);
		int srcCenterY = (int) (srcBoundingBox.y + srcBoundingBox.height / 2);

		// Create a new Mouse object
		Mouse mouse = page.mouse();

		// Perform the drag and drop by moving the element by an offset
		mouse.move(srcCenterX, srcCenterY);
		mouse.down();
		mouse.move(srcCenterX + 200, srcCenterY + 150); // Move by 200px right and 150px down
		mouse.up();

		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
