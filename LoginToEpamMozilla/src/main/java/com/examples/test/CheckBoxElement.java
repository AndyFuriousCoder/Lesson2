package com.examples.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckBoxElement extends Element
{
    public CheckBoxElement(WebDriver driver, String name)
    {
        super(driver, name);
    }

    public void verify()
    {
        System.out.println("Checkbox Element: " + name);
        System.out.println("Enabled---> " + enable());
        System.out.println("Displayed-> " + display());
        System.out.println("Checked---> " + check());
        System.out.println();
    }

}
