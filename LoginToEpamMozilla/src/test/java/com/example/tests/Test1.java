package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
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
            driver.findElement(By.id("Login")).clear();
            driver.findElement(By.id("Login")).sendKeys("epam");
            driver.findElement(By.id("Password")).clear();
            driver.findElement(By.id("Password")).sendKeys("1234");
            driver.findElement(By.xpath("//button[@type='submit']")).click();

            Assert.assertTrue((driver.findElement(By.cssSelector(".logout"))).isEnabled());
            System.out.println("Login test done successfuly!");
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