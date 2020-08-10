package com.iyfinproj.test.pages;

import com.iyfinproj.test.CommonUtils;
import com.iyfinproj.test.prodtransition.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

import static com.iyfinproj.test.Driver.getDriver;

public class SalePageMethods {
    private static final By BUTTON_GRID = By.xpath("//strong[@class='grid']");
    private static final By DROPDOWN_SELECT_ITEMS_PER_PAGE = By.xpath("//select[@title='Results per page']");
    private static final By OLD_PRICE_ON_PAGE = By.xpath("//p[@class='old-price']");
    private static final By SALE_PRICE_ON_PAGE = By.xpath("//p[@class='special-price']");
    private static final By SALE_PRODUCT_LIST = By.xpath("//div[@class='product-info']");
    private static final By PRODUCT_NAMЕ = By.xpath(" //h2[@class='product-name']");

    @Step
    public SalePageMethods clickGridButton() {
        WebElement gridButton = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(BUTTON_GRID));
        gridButton.click();
        return this;
    }

    @Step
    public SalePageMethods setItemsPerPage(String perPageItems) {
        Select selectItemsPerPage = new Select(getDriver().findElement(DROPDOWN_SELECT_ITEMS_PER_PAGE));
        selectItemsPerPage.selectByVisibleText(perPageItems);
        return this;
    }

    @Step("Verify the old price is higher than the sale price")
    public SalePageMethods checkOldPriceHigherThanSalePriceForEachItem() {
        List<WebElement> listOfItems = new WebDriverWait(getDriver(), 10).until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(SALE_PRODUCT_LIST));

        List<Product> items = new ArrayList<>();

        for (WebElement webItem : listOfItems) {
            Product item = Product.builder()
                    .title(webItem.findElement(PRODUCT_NAMЕ).getText())
                    .newPrice(CommonUtils.getDoubleFromString(webItem.findElement(SALE_PRICE_ON_PAGE).getText()))
                    .oldPrice(CommonUtils.getDoubleFromString(webItem.findElement(OLD_PRICE_ON_PAGE).getText()))
                    .build();
            items.add(item);
        }
        for (Product item : items) {
            Assert.assertTrue(item.getOldPrice() > item.getNewPrice(), String.format("Old price is higher or equal than a sale for {0}", item.getTitle()));
        }
        return this;
    }
}



