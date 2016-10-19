package com.examples.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//this page object is used for login
public class LoginElement {
    private WebElement userName;
    private WebElement password;
    private WebDriver driver;

    //constructor
    public LoginElement(WebDriver driver)
    {
        this.userName = driver.findElement(By.id("Login"));
        this.password = driver.findElement(By.id("Password"));
        this.driver = driver;

    }

    //authorize to account
    public void authorize(String login, String psw)
    {
        userName.clear();
        userName.sendKeys(login);
        password.clear();
        password.sendKeys(psw);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

}
