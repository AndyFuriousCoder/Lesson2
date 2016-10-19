package com.examples.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {

    protected By locator;
    protected String name;
    protected WebElement element;
    protected WebDriver driver;

    public Element(WebDriver driver, String name){
        this.driver = driver;
        this.name = name;
        this.locator = By.xpath("//label[contains(.,'" + name + "')]/input");
    }

    public boolean check()
    {
        return getElement().isSelected();
    }

    public boolean enable()
    {
        return getElement().isEnabled();
    }

    public boolean display()
    {
        return getElement().isDisplayed();
    }

    public WebElement getElement()
    {
        if(element == null)
        {
            element = driver.findElement(locator);
        }
        //Todo check page (dom) version
        //If new dom then update element
        return element;
    }

}
