package windowhandling;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CountTheWindows {

	protected static String url = "https://demoqa.com/browser-windows";

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
	public void countWindows() throws InterruptedException {
		driver.switchTo().newWindow(WindowType.WINDOW);
		Thread.sleep(5000);
		Set<String> windows = driver.getWindowHandles();
		System.out.println("Total Tab Number: "+windows.size());
	}

	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
