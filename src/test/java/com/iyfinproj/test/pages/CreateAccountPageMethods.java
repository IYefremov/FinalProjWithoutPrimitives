package com.iyfinproj.test.pages;

import com.iyfinproj.test.CommonUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.iyfinproj.test.Driver.getDriver;

public class CreateAccountPageMethods {
    private static final By INPUT_FIELD_FIRST_NAME = By.xpath("//input[@id='firstname']");
    private static final By INPUT_FIELD_LAST_NAME = By.xpath(" //input[@id='lastname']");
    private static final By INPUT_FIELD_EMAIL = By.xpath("//li//input[@type='email']");
    private static final By INPUT_FIELD_PASSWORD = By.xpath("//input[@id='password']");
    private static final By INPUT_FIELD_CONFIRMATION = By.xpath("//input[@id='confirmation']");
    private static final By BUTTON_REGISTER = By.xpath("//button[@title='Register']");

    @Step("Fill in the register form")
    public DashboardPageMethods registerUser()  {

        WebElement firstName = getDriver().findElement(INPUT_FIELD_FIRST_NAME);
        firstName.sendKeys("Ivan");

        WebElement lastName = getDriver().findElement(INPUT_FIELD_LAST_NAME);
        lastName.sendKeys("Yefremov");

        WebElement email = getDriver().findElement(INPUT_FIELD_EMAIL);
        email.sendKeys(CommonUtils.generateRandomEmail());

        WebElement password = getDriver().findElement(INPUT_FIELD_PASSWORD);
        password.sendKeys("123456");

        WebElement confirm = getDriver().findElement(INPUT_FIELD_CONFIRMATION);
        confirm.sendKeys("123456");

        WebElement regButton = getDriver().findElement(BUTTON_REGISTER);
        regButton.click();

        return new DashboardPageMethods();
    }

}
