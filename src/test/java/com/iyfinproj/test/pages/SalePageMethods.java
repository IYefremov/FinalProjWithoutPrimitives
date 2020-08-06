package com.iyfinproj.test.pages;

import com.iyfinproj.test.CommonUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.iyfinproj.test.Driver.getDriver;

public class SalePageMethods {
    private static final By BUTTON_GRID = By.xpath("//strong[@class='grid']");
    private static final By DROPDOWN_SELECT_ITEMS_PER_PAGE = By.xpath("//select[@title='Results per page']");
    private static final By OLD_PRICE_ON_PAGE = By.xpath("//p[@class='old-price']");
    private static final By SALE_PRICE_ON_PAGE = By.xpath("//p[@class='special-price']");


    public SalePageMethods clickGridButton(){
        WebElement gridButton = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(BUTTON_GRID));
        gridButton.click();
        return this;
    }

    public SalePageMethods setItemsPerPage(String perPageItems) {
        Select selectItemsPerPage = new Select(getDriver().findElement(DROPDOWN_SELECT_ITEMS_PER_PAGE));
        selectItemsPerPage.selectByVisibleText(perPageItems);
        return this;
    }

    public SalePageMethods checkOldPriceHigherThanSalePriceForEachItem(){
        boolean flag = true;
        double oldPriceValue, salePriceValue;

        List<WebElement> oldPrices = getDriver().findElements(OLD_PRICE_ON_PAGE);
        List<WebElement> salePrices = getDriver().findElements(SALE_PRICE_ON_PAGE);

        for (int i = 0; i < oldPrices.size(); i++){
            oldPriceValue = CommonUtils.getDoubleFromString(oldPrices.get(i).getText());
            salePriceValue = CommonUtils.getDoubleFromString(salePrices.get(i).getText());

            if (oldPriceValue < salePriceValue ){
                flag = false;
                break;
            }
        }
        Assert.assertTrue(flag, "Old Price Is Higher Than Sale Price For Each Item");
        return this;
    }


}
