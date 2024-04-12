package webtablehandling;

import java.util.List;
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

public class FetchTableHeading {
	protected static String url = "https://demo.guru99.com/test/web-table-element.php";

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
	public void fetchHeading() throws InterruptedException {
		ElementHandle tableElement = page.querySelector("//table[@class='dataTable']/thead");
		List<ElementHandle> rows = tableElement.querySelectorAll("tr");

		for (ElementHandle rowElement : rows) {
			List<ElementHandle> cells = rowElement.querySelectorAll("th");
			for (ElementHandle cellElement : cells) {
				String cellData = cellElement.textContent();
				System.out.print("| " + cellData + " |\t");
			}
			System.out.println();
		}

	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
