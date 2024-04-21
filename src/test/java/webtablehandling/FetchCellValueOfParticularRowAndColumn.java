package webtablehandling;

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

public class FetchCellValueOfParticularRowAndColumn {
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
		Thread.sleep(40000);
	}

	@Test
	public void fetchCellValueOfParticularRowAndColumn() {
		ElementHandle tableRow = page.querySelector("//*[@id='leftcontainer']/table/tbody/tr[3]");
		String rowtext = tableRow.textContent();
		System.out.print("Third row of table : " + rowtext);

		ElementHandle cellIneed = page.querySelector("//*[@id='leftcontainer']/table/tbody/tr[3]/td[1]/a");
		String valueIneed = cellIneed.textContent();
		System.out.println("Cell value is : " + valueIneed);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
