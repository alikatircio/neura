package com.neura.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitHelper {

    protected WebDriver driver;
    public WaitHelper (WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementVisibleFluently(By locator, int timeoutSeconds) {
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);

        return fluentWait.until(driver -> {
            WebElement element = driver.findElement(locator);
            if (element.isDisplayed()) {
                return element;
            }
            return null;
        });
    }
}
