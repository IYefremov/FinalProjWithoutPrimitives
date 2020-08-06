package com.iyfinproj.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import static com.iyfinproj.test.Driver.getDriver;

public class HomePageMethods {
    private static final By DROPDOWN_SELECT_LANGUAGE = By.id("select-language");
    private static final By BUTTON_HOME_AND_DECOR = By.xpath("//li[@class='level0 nav-4 parent']");
    private static final By DROPDOWN_ELEMENT_ELECTRONICS = By.xpath("//div[@id='header-nav']//li[@class='level1 nav-4-3']");
    private static final By BUTTON_ACCOUNT = By.cssSelector(".skip-account.skip-link > .label");
    private static final By DROPDOWN_ELEMENT_LOGIN = By.xpath("//a[@title='Log In']");
    private final static By SALE = By.xpath("//a[.='Sale']");


    public enum Language {
        AUTO("Automation"),
        ENG("English");

        private String name;

        private Language(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public HomePageMethods selectLanguage(Language lang) throws InterruptedException {

        Select langSelect = new Select(getDriver().findElement(DROPDOWN_SELECT_LANGUAGE));
        langSelect.selectByVisibleText(lang.toString());
        return this;
    }

    public ElectronicsPageMethods goToElectronicsPage() {

        Actions actions = new Actions(getDriver());
        WebElement target = getDriver().findElement(BUTTON_HOME_AND_DECOR);
        actions.moveToElement(target).perform();

        WebElement homeAndDecor = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(DROPDOWN_ELEMENT_ELECTRONICS));
        homeAndDecor.click();

        return new ElectronicsPageMethods();
    }

    public AccountPageMethods goToLoginPage() {
        WebElement logButton = getDriver().findElement(BUTTON_ACCOUNT);
        logButton.click();
        WebElement elementLogin = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.visibilityOfElementLocated(DROPDOWN_ELEMENT_LOGIN));
        elementLogin.click();

        return new AccountPageMethods();
    }

    public SalePageMethods goToSalePage() {

        WebElement saleElement = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(SALE));
        saleElement.click();

        return new SalePageMethods();
    }
}
