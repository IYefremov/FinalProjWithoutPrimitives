package com.iyfinproj.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.iyfinproj.test.Driver.getDriver;

public class DashboardPageMethods {
    private static final By BUTTON_HOME_AND_DECOR = By.xpath("//li[@class='level0 nav-4 parent']");
    private static final By DROPDOWN_ELEMENT_ELECTRONICS = By.xpath("//li[@class='level1 nav-4-3']");

    public ElectronicsPageMethods goToElectronicsPage(){
        Actions actions = new Actions(getDriver());
        WebElement target = getDriver().findElement(BUTTON_HOME_AND_DECOR);
        actions.moveToElement(target).perform();

        WebElement homeAndDecor = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(DROPDOWN_ELEMENT_ELECTRONICS));
        homeAndDecor.click();

        return new ElectronicsPageMethods();

    }
}
