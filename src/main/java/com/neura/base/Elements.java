package com.neura.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Elements {


    public static WebElement byText(WebDriver driver, String text) {
        String xpath = "//*[text()='" + text + "']";
        return driver.findElement(By.xpath(xpath));
    }

    public static WebElement byContains(WebDriver driver, String text) {
        String xpath = "//*[contains(text(),'" + text + "')]";
        return driver.findElement(By.xpath(xpath));
    }


}
