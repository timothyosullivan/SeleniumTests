package com.scratchpad.selenium.webdriver;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumExampleTest {
	
	private WebDriver webDriver;
	
	@Before
	public void setUp() {
		File file = new File("libs/chromedriver");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		setWebDriver(new ChromeDriver());
	}
	
	@Test
	public void googleSearchTest() {
		
		getWebDriver().navigate().to("http://www.google.com");
		
		// check title of page navigated to is correct
		Assert.assertTrue("web page title is not correct",
				getWebDriver().getTitle().equalsIgnoreCase("Google"));
		
		WebElement element = getWebDriver().findElement(By.name("q"));
		element.sendKeys("Novartis\n"); // send also a "\n"
		element.submit();

		// wait until the google page shows the result
		WebElement myDynamicElement = (new WebDriverWait(getWebDriver(), 10))
				.until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));

		List<WebElement> findElements = getWebDriver().findElements(By.xpath("//*[@id='rso']//h3/a"));

		// this are all the links you like to visit
		for (WebElement webElement : findElements)
		{
			System.out.println(webElement.getAttribute("href"));
		}
	}
	
	@After
	public void tearDown() {
		getWebDriver().close();
	}
	
	private void setWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}
	
	private WebDriver getWebDriver() {
		return this.webDriver;
	}

}
