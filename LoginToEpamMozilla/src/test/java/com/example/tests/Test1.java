package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Test1 {
    private WebDriver driver;
    private String baseUrl;

    @BeforeTest
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

    @Test
    public void test1() {
        try {
            driver.navigate().to(baseUrl);
            driver.findElement(By.cssSelector("li.dropdown.uui-profile-menu > a.dropdown-toggle")).click();
            WebElement loginElement = driver.findElement(By.id("Login"));
            loginElement.clear();
            loginElement.sendKeys("epam");
            WebElement passwordElement = driver.findElement(By.id("Password"));
            passwordElement.clear();
            passwordElement.sendKeys("1234");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            Assert.assertTrue((driver.findElement(By.cssSelector(".logout"))).isEnabled());
            System.out.println("Login test done successfuly!");

            Alert alert = (new WebDriverWait(driver, 5)).until(ExpectedConditions.alertIsPresent());
            String message = alert.getText();
            System.out.println(message);
            alert.dismiss();

        }
        catch (Exception o){
            System.out.println("Test1 execution error! See stack-trace below:");
            o.printStackTrace();
        }
    }

    @AfterTest
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}