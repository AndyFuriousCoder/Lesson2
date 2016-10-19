package com.examples.test;


import org.openqa.selenium.WebDriver;

public class RadioButtonElement extends Element {

    public RadioButtonElement(WebDriver driver, String name)
    {
        super(driver, name);
    }

    public void verify()
    {
        System.out.println("RadioButton Element: " + name);
        System.out.println("Enabled---> " + enable());
        System.out.println("Displayed-> " + display());
        System.out.println("Checked---> " + check());
        System.out.println();
    }
}
