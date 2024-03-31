package alerthandling;

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

public class DismissPromptAlert {
	protected static String url = "https://demoqa.com/alerts";

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
	public void dismissAlert() throws InterruptedException {

		page.onDialog(dialog -> {
			System.out.println("Dialog Type: " + dialog.type());
			System.out.println("Dialog Message: " + dialog.message());

			if (dialog.type().equals("alert")) {
				dialog.dismiss();
				System.out.println("Canceled Alert.");
			} else if (dialog.type().equals("confirm")) {
				dialog.dismiss();
				System.out.println("Canceled Confirmation.");
			} else if (dialog.type().equals("prompt")) {
				dialog.dismiss();
				System.out.println("Canceled Prompt.");
			}
		});

		Locator alertButton = page.locator("//button[@id='promtButton']");
		alertButton.click();
		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
