package locators;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class XPathStrategies {
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

//	@Test(priority = 0)
	public void relativeXPath() throws InterruptedException {
		page.navigate(url);
		page.waitForLoadState();
		ElementHandle element = page.querySelector("//input[@id='name']");
		element.fill("Ebrahim Hossain");
		Thread.sleep(3000);
	}

//	@Test(priority = 1)
	public void exampleOfXPathContains() throws InterruptedException {
		page.navigate(url);
		page.waitForLoadState();
		//Sample-1 - Format: //*[contains(text(), 'value')]
		ElementHandle element = page.querySelector("//*[contains(text(),'Email')]");
		System.out.println(element.textContent());
		
		//Sameple-2 - Format: //*[contains(@attribute, 'value')]
		ElementHandle element2 = page.querySelector("//*[contains(@id,'email')]");
		element2.fill("ebrahimhossain@noemail.com");
		Thread.sleep(3000);
	}
	
//	@Test(priority = 2)
	public void exampleOfXPathOR() throws InterruptedException {
		page.navigate(url);
		page.waitForLoadState();
		//Sample-1 - Format: //*[first condition or second condition or ....]
		ElementHandle element2 = page.querySelector("//*[contains(@id,'email') or @placeholder='name@example.com']");
		element2.fill("ebrahimhossain@noemail.com");
		Thread.sleep(3000);
	}
	
	@Test(priority = 3)
	public void exampleOfXPathAND() throws InterruptedException {
		page.navigate(url);
		page.waitForLoadState();
		//Sample-1 - Format: //*[first condition and second condition and ....]
		ElementHandle element2 = page.querySelector("//*[contains(@id,'email') and @placeholder='name@example.com']");
		element2.fill("ebrahimhossain@noemail.com");
		Thread.sleep(3000);
	}
	
	@AfterSuite
	public void closeChromeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}

}
