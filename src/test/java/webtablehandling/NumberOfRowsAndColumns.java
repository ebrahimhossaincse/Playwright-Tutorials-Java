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

public class NumberOfRowsAndColumns {
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

	@Test
	public void fetchNumberOfRowsAndColumns() {
		List<ElementHandle> col = page.querySelectorAll("//*[@id='leftcontainer']/table/thead/tr/th");
		System.out.println("No of cols are : " + col.size());

		List<ElementHandle> rows = page.querySelectorAll("//*[@id='leftcontainer']/table/tbody/tr/td[1]");
		System.out.println("No of rows are : " + rows.size());
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
