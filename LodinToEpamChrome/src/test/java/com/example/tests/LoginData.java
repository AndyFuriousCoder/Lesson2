package com.example.tests;

import org.testng.annotations.DataProvider;

public class LoginData {

    @DataProvider(name = "LoginTest")
    public static Object[][] test1() {
        return new Object[][] {{false, "epam", "123"},
                {false, "login", "1234"},
                {true, "epam", "1234"}};
    }
}