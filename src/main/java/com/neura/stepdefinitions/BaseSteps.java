package com.neura.stepdefinitions;

import com.neura.base.BasePage;
import com.neura.base.DriverFactory;
import com.neura.base.Elements;
import com.neura.base.WaitHelper;
import com.neura.utils.GetElement;
import com.neura.utils.Lang;
import com.neura.utils.LanguageManager;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.IOException;

import static org.testng.Assert.assertEquals;

public class BaseSteps {

    private WebDriver driver;
    private BasePage basePage;
    private WaitHelper waitHelper;

    public BaseSteps() {
        this.driver = DriverFactory.getDriver();
        this.basePage = new BasePage(this.driver);
        this.waitHelper = new WaitHelper(this.driver);
    }
    @Given("Click to element {string}")
    public void clickElement(String elementName) {
        By element = GetElement.getLocator(elementName);
        basePage.clickElement(element);
    }

    @Given("User navigates to {string}")
    public void userNavigatesTo(String url) {
        basePage.redirectUrl(url);
    }

    @When("User enter {string} value to {string}")
    public void enterValueElement(String text, String elementName) {
        By element = GetElement.getLocator(elementName);
        basePage.enterValue(element, text);
    }

    @Then("Wait {string} to element until visible")
    public void waitUntilElementIsVisible(String elementName) {
        By element = GetElement.getLocator(elementName);
        WebElement wb = waitHelper.waitForElementVisibleFluently(element, 90);
        Assert.assertTrue(wb.isDisplayed());
    }

    @Then("Verify {string} page {string} element {string} value")
    public void verifyLanguageString(String page, String locator, String value) throws IOException {
        basePage.verifyTexts(page, locator, value);
    }

    @Given("Get page language with {string} placeholder")
    public void getPageLanguage(String elementHolder) {
        basePage.getCurrentLanguage(elementHolder);
    }

    @Given("language is {string}")
    public void setLanguage(String lang) {
        LanguageManager.setLanguage(lang);
    }

    @Then("Pause")
    public void pause() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Then("Verify {string} placeholder")
    public void verifyPlaceholder(String key) {
        String expectedPlaceholder = Lang.text(key);
        WebElement el = Elements.byContains(this.driver, expectedPlaceholder);
        String actual = el.getAttribute("placeholder");
        assertEquals(expectedPlaceholder, actual,
                "Placeholder text does not match for key: " + key);
    }

    @When("I verify the text {string}")
    public void verifyText(String key) {
        String expected = Lang.text(key);
        WebElement el = Elements.byText(this.driver, expected);
        assertEquals(expected, el.getText());
    }

    @When("I click the text {string}")
    public void clickText(String key) {
        String text = Lang.text(key);
        Elements.byText(this.driver, text).click();
    }

    @When("User types {string} into {string}")
    public void enterValueElementSecondApproach(String value, String key) {
        String expectedText = Lang.text(key);
        WebElement el = Elements.byContains(this.driver, expectedText);
        el.clear();
        el.sendKeys(value);
    }




}
