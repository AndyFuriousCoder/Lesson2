package com.example.tests;

import java.util.concurrent.TimeUnit;

import com.examples.test.CheckBoxElement;
import com.examples.test.LoginElement;
import com.examples.test.RadioButtonElement;
import com.examples.test.SelectElement;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.testng.Assert.assertTrue;

public class Test1 {
    private WebDriver driver;
    private String baseUrl;

    @BeforeTest(alwaysRun = true)
    public void setUp() {
        FirefoxProfile profile = new FirefoxProfile();
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setCapability(FirefoxDriver.PROFILE, profile);
        capability.setPlatform(Platform.WINDOWS);
        driver = new FirefoxDriver(capability);
        baseUrl = "https://jdi-framework.github.io/tests/index.htm";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

    }

    //Login test
    @Test(enabled = true, dataProviderClass = LoginData.class, dataProvider = "LoginTest")
    //use DataProvider
    public void test1(boolean bool, String login, String password) {
        //go to <epam> site
        driver.navigate().to(baseUrl);
        //open dropdown menu
        driver.findElement(By.cssSelector("li.dropdown.uui-profile-menu > a.dropdown-toggle")).click();
        //authorizing using page object LoginElement
        new LoginElement(driver).authorize(login, password);
        //check authorizing
        if (bool) {
            WebElement logoutElement = driver.findElement(By.cssSelector(".logout"));
            assertTrue((logoutElement).isEnabled());
            logoutElement.click();
            System.out.println("Login test done successfuly!");
        }
        if (!bool) {
            assertTrue((driver.findElement(By.xpath("//button[@type='submit']"))).isEnabled());
            System.out.println("Login test done successfuly!");
        }
    }

    //alert check test
    @Test(enabled = true)
    public void checkAlert()
    {
        //call alert
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('It\\'s alert text!');");
        //handle alert
        Alert alert = (new WebDriverWait(driver, 15)).until(ExpectedConditions.alertIsPresent());
        String message = alert.getText();
        System.out.println(message);
        alert.dismiss();
    }

    //PageObject test
    @Test(enabled = true)
    public void checkboxTest()
    {
        //go to <epam> site
        driver.navigate().to(baseUrl);
        //open dropdown menu
        driver.findElement(By.cssSelector("li.dropdown.uui-profile-menu > a.dropdown-toggle")).click();
        //authorizing using page object LoginElement
        new LoginElement(driver).authorize("epam", "1234");
        //go to page with checkboxes and radioboxes
        driver.navigate().to("https://jdi-framework.github.io/tests/page8.htm");
        //verify checkboxes using page object CheckBoxElement
        new CheckBoxElement(driver, "Water").verify();
        new CheckBoxElement(driver, "Earth").verify();
        new CheckBoxElement(driver, "Wind").verify();
        new CheckBoxElement(driver, "Fire").verify();
        //verify radioboxes using page object RadioButtonElement
        new RadioButtonElement(driver, "Gold").verify();
        new RadioButtonElement(driver, "Silver").verify();
        new RadioButtonElement(driver, "Bronze").verify();
        new RadioButtonElement(driver, "Selen").verify();
    }

    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}