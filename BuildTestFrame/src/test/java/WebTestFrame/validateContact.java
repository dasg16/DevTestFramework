package WebTestFrame;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pageObjects.ContactPage;
import pageObjects.LandingPage;
import resources.base;

public class validateContact extends base {

	public static Logger log = LogManager.getLogger(base.class.getName());

	@BeforeTest
	public void initialize() throws IOException {

		driver = initializeDriver();
		driver.get(prop.getProperty("url"));

	}

	@Test(priority = 0)
	public void validContactTitle() throws IOException {

		LandingPage lanp = new LandingPage(driver);
		lanp.getCloseButton().click();
		lanp.getNavigationContact().click();
		log.info("Entered into contact page from homepage");

		// Firstly, validate in the right page is open by validating page title.
		Assert.assertEquals(driver.getTitle(), "QAClick Academy | Contact Us");

	}

	@Test(priority = 1)
	public void validContactDetlsLable() throws IOException {

		ContactPage cp = new ContactPage(driver);

		WebDriverWait d = new WebDriverWait(driver, 40);
		d.until(ExpectedConditions.visibilityOfElementLocated(cp.getContactLableBy()));
		// Validate contact details lable.
		Assert.assertEquals(cp.getContactLable().getText(), "CONTACT DETAILS");
		log.info("Validated contact details lable");
	}

	@Test(priority = 2)
	public void validContactAddr() throws IOException {

		ContactPage cp = new ContactPage(driver);
		String addr = cp.getAddress().getText().replaceAll("\n", " ");

		// Validate address.
		Assert.assertEquals(addr, "ADDRESS Meenakshi Towers, Road Number 4, Kundanhalli, Bangalore, India");
		log.info("Validated contact address");
	}

	@Test(priority = 3)
	public void validContactPhone() throws IOException {

		ContactPage cp = new ContactPage(driver);

		// Validate phone number.
		Assert.assertEquals(cp.getPhone().getText(), "(+1) 323-744-6780");
		log.info("Validated contact phone number");
	}

	@Test(priority = 4)
	public void validContactEmails() throws IOException {

		ContactPage cp = new ContactPage(driver);
		WebElement email = cp.getEmail();

		int count = email.findElements(By.tagName("a")).size();

		for (int i = 0; i < count; i++) {

			// Validate email IDs.

			if (email.findElements(By.tagName("a")).get(i).getText().contains("info@qaclickacademy.com")) {
				Assert.assertEquals(email.findElements(By.tagName("a")).get(i).getText(), "info@qaclickacademy.com");
				log.info("Validate first Email ID");
			}

			if (email.findElements(By.tagName("a")).get(i).getText().contains("courses@qaclickacademy.com")) {
				Assert.assertEquals(email.findElements(By.tagName("a")).get(i).getText(), "courses@qaclickacademy.com");
				log.info("Validate Second Email ID");
			}

		}

	}

	@AfterTest
	public void teardown() {
		driver.close();
		driver = null;
	}

}
