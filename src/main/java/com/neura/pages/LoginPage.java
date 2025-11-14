package com.neura.pages;

import com.neura.base.BasePage;
import com.neura.base.DriverFactory;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {

    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("h3[data-test='error']");

    public LoginPage() {
        super(DriverFactory.getDriver());
    }

    public void goToLoginPage(String url) {
        redirectUrl(url);
    }

    public void login(String username, String password) {
        enterValue(usernameField, username);
        enterValue(passwordField, password);
        clickElement(loginButton);
    }
}
