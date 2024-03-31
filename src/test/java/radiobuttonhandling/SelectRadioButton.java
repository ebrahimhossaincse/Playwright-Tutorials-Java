package radiobuttonhandling;

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
import com.microsoft.playwright.options.WaitUntilState;

public class SelectRadioButton {
	String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";
	
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

    @SuppressWarnings("static-access")
	@BeforeClass
	public void openUrl() throws InterruptedException{
		page.navigate(url, new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED.LOAD));
		Thread.sleep(3000);
	}

	@Test
	public void radioButton() throws InterruptedException {
		Locator radiobtn = page.locator("//*[@id='gender']");
		radiobtn.click();

		boolean result = radiobtn.isChecked();
		System.out.println("Checking if a radio button is selected: " + result);
		Thread.sleep(5000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
