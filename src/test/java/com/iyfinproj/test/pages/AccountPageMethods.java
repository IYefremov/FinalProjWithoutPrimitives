package com.iyfinproj.test.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.iyfinproj.test.Driver.getDriver;

public class AccountPageMethods {
    private static final By BUTTON_CREATE_ACCOUNT = By.cssSelector("a[title='Create an Account']");
    private static final By BUTTON_INSIDE_SMALL_CREATEACCOUNT_BUTTON = By.xpath("//img[@alt='close']");

    @Step("Click Create Account Button")
    public CreateAccountPageMethods goToAccountForm(){

        WebElement insideCloseButt = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.visibilityOfElementLocated(BUTTON_INSIDE_SMALL_CREATEACCOUNT_BUTTON));
        insideCloseButt.click();

        WebElement createAccountButton = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.visibilityOfElementLocated(BUTTON_CREATE_ACCOUNT));
        createAccountButton.click();

        return new CreateAccountPageMethods();
    }

}
