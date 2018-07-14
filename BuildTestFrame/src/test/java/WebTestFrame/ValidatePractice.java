package WebTestFrame;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.PracticePage;
import resources.base;

public class ValidatePractice extends base {

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		// log.info("Driver has been initialized");
		driver.get(prop.getProperty("url"));

	}

	// <exclude name="validPracticeDropdown"/>
	// <exclude name="validPracticeCheckbox"/>
	// <exclude name="validPracticeElementDisplayed"/>
	// <exclude name="validPracticeWebTable"/>
	// <exclude name="validPracticeMouseOver"/>

	@Test(priority = 0)
	public void validPracticeLink() throws IOException, Exception {

		LandingPage lanp = new LandingPage(driver);
		// counting total number of links in the landing page.
		lanp.getCloseButton().click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		int count = lanp.getCountLinks();

		for (int i = 0; i < count; i++) {

			String test = lanp.getLinks().get(i).getAttribute("href");

			if (test.toLowerCase().contains("practice")) {
				lanp.getLinks().get(i).click();
				// Thread.sleep(5000L);

				log.info("Validated Practice page link on HomePage");
				i = count;// Doubt! Why isn't this working without this statement?

			}

		}

		AssertJUnit.assertEquals(driver.getTitle(), "Practice Page");
		log.info("Moved into Practice page");

	}

	@Test(priority = 1)
	public void validPracticeRadio() {
		PracticePage pracP = new PracticePage(driver);

		AssertJUnit.assertEquals(pracP.getRadioButtonTitle().getText(), "Radio Button Example");
		log.info("Verified radio button title tag");

		int count = pracP.getRadioButtonCommonPath().size();

		for (int i = 0; i < count; i++) {

			pracP.getRadioButtonCommonPath().get(i).click();

		}

	}

	@Test(priority = 2)
	public void validSuggessionTxtBox() {

		PracticePage pracP = new PracticePage(driver);

		AssertJUnit.assertEquals(pracP.getSuggessionTxtBoxTitle().getText(), "Suggession Class Example");
		log.info("Verified Suggestion Class Textbox title tag");

		pracP.getSuggessionTxtBoxPath().click();

		Actions a = new Actions(driver);

		a.moveToElement(pracP.getSuggessionTxtBoxPath()).click().keyDown(Keys.SHIFT).sendKeys("hello").build()
				.perform();
		// a.moveToElement(pracP.getSuggessionTxtBoxPath()).contextClick().build().perform();
		// a.moveToElement(pracP.getSuggessionTxtBoxPath()).click().build().perform();

	}

	@Test(priority = 3)
	public void validPracticeDropdown() {
		PracticePage pracP = new PracticePage(driver);

		// Assert.assertEquals(pracP.getDropdownTitle().getText(), "Dropdown Example");
		// log.info("Verified dropdown button title tag");

		// Using JavaScriptExecutor because otherwise the links are not clickable. Using
		// explicit wait ExpectedConditions.elementToBeClickable worked for a while.
		// WebElement element = pracP.getDropdownCommonPath();
		Select s = new Select(driver.findElement(By.xpath("//*[@id=\'dropdown-class-example\']")));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", s);

		s.selectByValue("option1");
		s.selectByIndex(1);
		s.selectByVisibleText("Option3");

	}

	@Test
	public void validPracticeCheckbox() {
		PracticePage pracP = new PracticePage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Assert.assertEquals(pracP.getCheckboxTitle().getText(), "Checkbox Example");
		log.info("Verified dropdown button title tag");

		// WebElement element = pracP.getCheckboxOnePath();
		// JavascriptExecutor executor = (JavascriptExecutor) driver;
		// executor.executeScript("arguments[0].click();", element);

		String gou = driver.findElement(By.xpath("/html/body/div[1]/div[3]")).getText();
		System.out.println(gou);
		// *[@id="checkbox-example"]

		// pracP.getCheckboxOnePath().click();
		// pracP.getCheckboxOnePath().isSelected();
		// pracP.getCheckboxTwoPath().click();
		// pracP.getCheckboxTwoPath().isSelected();
		// pracP.getCheckboxThreePath().click();
		// pracP.getCheckboxThreePath().isSelected();
	}

	@Test(enabled = false)
	public void validPracticeSwitchWindow() {
		PracticePage pracP = new PracticePage(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		AssertJUnit.assertEquals(pracP.getChildWindowTitle().getText(), "Switch Window Example");
		log.info("Verified Switch Window title tag");

		System.out.println(driver.getTitle());
		pracP.getChildWindowPath().click();

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);

		System.out.println("After Switching");
		System.out.println(driver.getTitle());
		System.out.println("Switched back to parent");
		driver.switchTo().window(childID).close();
		;
		driver.switchTo().window(parentID);

	}

	@Test(enabled = false)
	public void validPracticeSwitchTab() {
		PracticePage pracP = new PracticePage(driver);

		AssertJUnit.assertEquals(pracP.getSwitchTabTitle().getText(), "Switch Tab Example");
		log.info("Verified Switch Window title tag");

		System.out.println(driver.getTitle());
		pracP.getTabPath().click();

		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		String parentID = it.next();
		String childID = it.next();
		driver.switchTo().window(childID);
		driver.switchTo().window(childID).close();
		;
		driver.switchTo().window(parentID);

	}

	@Test(enabled = false)
	public void validPracticeSwitchAlert() {
		PracticePage pracP = new PracticePage(driver);

		AssertJUnit.assertEquals(pracP.getAlertTitle().getText(), "Switch To Alert Example");
		log.info("Verified Switch Window title tag");

		System.out.println(driver.getTitle());
		pracP.getAlertTextBox().sendKeys("Gourav");
		pracP.getAlertButton().click();
		String msg = driver.switchTo().alert().getText();

		AssertJUnit.assertEquals(msg, "Hello Gourav, share this practice page and share your knowledge");

		driver.switchTo().alert().accept();
		pracP.getAlertConfirmButton().click();
		driver.switchTo().alert().dismiss();

	}

	@Test(enabled = false)
	public void validPracticeElementDisplayed() {
		PracticePage pracP = new PracticePage(driver);

		// Assert.assertEquals(pracP.getIsDisplayedTitle().getText(), "Element Displayed
		// Example");
		log.info("Verified Element Displayed title tag");

		pracP.getHideButton().click();
		AssertJUnit.assertFalse(pracP.getIsDisplayedElement().isDisplayed());

		pracP.getShowButton().click();
		AssertJUnit.assertFalse(pracP.getIsDisplayedElement().isDisplayed());

	}

	@Test(enabled = false)
	public void validPracticeWebTable() {
		PracticePage pracP = new PracticePage(driver);

		AssertJUnit.assertEquals(pracP.getAlertTitle().getText(), "Switch To Alert Example");
		log.info("Verified Switch Window title tag");

		System.out.println(driver.getTitle());
		pracP.getAlertTextBox().sendKeys("Gourav");
		pracP.getAlertButton().click();
		String msg = driver.switchTo().alert().getText();

		AssertJUnit.assertEquals(msg, "Hello Gourav, share this practice page and share your knowledge");

		driver.switchTo().alert().accept();
		pracP.getAlertConfirmButton().click();
		driver.switchTo().alert().dismiss();

	}

	@Test(enabled = false)
	public void validPracticeMouseOver() {
		PracticePage pracP = new PracticePage(driver);

		// Assert.assertEquals(pracP.getMouseHoverTitle().getText(), "Mouse Hover
		// Example");

		Actions a = new Actions(driver);
		a.moveToElement(pracP.getMouseHoverButton()).build().perform();
		a.moveToElement(pracP.getMouseHoverTopButton()).click().build().perform();
		a.moveToElement(pracP.getMouseHoverButton()).build().perform();
		a.moveToElement(pracP.getMouseHoverReloadButton()).click().build().perform();
		log.info("Verified Mouse Hover title tag");

	}

	@AfterTest
	public void teardown() {
		driver.quit();
		driver = null;
	}

}
