package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class Test1 {
    private WebDriver driver;
    private String baseUrl;


    @BeforeTest
    public void setUp() {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
        dc.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        dc.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, true);
        dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        dc.setJavascriptEnabled(true);
        dc.setCapability("ignoreProtectedModeSettings", true);
        driver = new InternetExplorerDriver(dc);
        baseUrl = "https://jdi-framework.github.io/tests/index.htm";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.navigate().to(baseUrl);
        driver.findElement(By.cssSelector(".profile-photo")).click();
    }

    @Test(enabled = true, dataProviderClass = LoginData.class, dataProvider = "LoginTest")
    public void test1(boolean bool, String login, String password) {
        try {

            WebElement loginElement = driver.findElement(By.id("Login"));
            loginElement.clear();
            loginElement.sendKeys(login);
            WebElement passwordElement = driver.findElement(By.id("Password"));
            passwordElement.clear();
            passwordElement.sendKeys(password);
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            if (bool) {
                WebElement logoutElement = driver.findElement(By.cssSelector(".logout"));
                Assert.assertTrue((logoutElement).isEnabled());
                logoutElement.click();
                System.out.println("Login test done successfuly!");
            }
            if (!bool) {
                Assert.assertTrue((driver.findElement(By.xpath("//button[@type='submit']"))).isEnabled());
                System.out.println("Login test done successfuly!");
            }

        }
        catch (Exception o) {
            System.out.println("Test1 execution error! See stack-trace below:");
            o.printStackTrace();
        }
    }

    @Test(enabled = true)
    public void checkAlert() //alert check
    {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('It\\'s alert text!');");

            Alert alert = (new WebDriverWait(driver, 15)).until(ExpectedConditions.alertIsPresent());
            String message = alert.getText();
            System.out.println(message);
            alert.accept();
        } catch (Exception a) {
            System.out.println("Test1 execution error! See stack-trace below:");
            a.printStackTrace();
        }
    }


    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}