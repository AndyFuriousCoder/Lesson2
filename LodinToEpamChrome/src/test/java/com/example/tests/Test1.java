package com.example.tests;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.*;

public class Test1 {
    private WebDriver driver;
    private String baseUrl;


    @BeforeTest(alwaysRun = true)
    public void setUp() {
        driver = new ChromeDriver();
        baseUrl = "https://jdi-framework.github.io/tests/index.htm";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(baseUrl);
        driver.findElement(By.cssSelector("li.dropdown.uui-profile-menu > a.dropdown-toggle")).click();
    }
    //login test
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


    //alert check
    @Test(enabled = true)
    public void checkAlert()
    {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("alert('It\\'s alert text!');");

            Alert alert = (new WebDriverWait(driver, 15)).until(ExpectedConditions.alertIsPresent());
            String message = alert.getText();
            System.out.println(message);
            alert.dismiss();
        } catch (Exception a) {
            System.out.println("Test1 execution error! See stack-trace below:");
            a.printStackTrace();
        }
    }


    @AfterTest(alwaysRun = true)
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}