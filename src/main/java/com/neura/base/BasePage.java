package com.neura.base;

import com.neura.utils.GetElement;
import com.neura.utils.LanguageJsonReader;
import com.neura.utils.LanguageManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class BasePage {

    protected WebDriver driver;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement findElement(By locator) {
        return driver.findElement(locator);
    }

    public void clickElement(By locator){
        this.findElement(locator).click();
    }

    public void enterValue(By locator, String value) {
       this.findElement(locator).sendKeys(value);
    }

    public void redirectUrl (String url) {
        driver.get(url);
    }

    public void verifyTexts(String page, String locator, String value) throws IOException {
        String text = LanguageJsonReader.get(page, value);
        By element = GetElement.getLocator(locator);
        assertEquals(this.findElement(element).getAttribute("placeholder"), text);
    }

    public void getCurrentLanguage(String userLocator) {
        By element = GetElement.getLocator(userLocator);
        String usernamePlaceholder = this.findElement(element).getAttribute("placeholder");

        assert usernamePlaceholder != null;
        if (usernamePlaceholder.equals("Username")) {
            LanguageManager.setLanguage("eng");
        }
        if (usernamePlaceholder.contains("ユーザー名")) {
            LanguageManager.setLanguage("jps");
        }
    }



}
