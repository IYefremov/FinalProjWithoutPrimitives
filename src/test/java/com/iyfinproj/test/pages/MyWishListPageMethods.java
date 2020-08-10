package com.iyfinproj.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.iyfinproj.test.Driver.getDriver;

public class MyWishListPageMethods {
    private final By PAGE_MY_WISH_LIST_TITLE = By.xpath("//h1[.='My Wishlist']");
    private final By PRODUCT_NAME = By.xpath("//h3[@class='product-name']");

    public MyWishListPageMethods setElementName(String elementName) {
        ElementName = elementName;

        return  this;
    }

    public String getElementName() {
        return ElementName;
    }

    private String ElementName;

    public MyWishListPageMethods checkIfMyWishPageIsShown() {
        List<WebElement> myWishListPage = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PAGE_MY_WISH_LIST_TITLE));

        Assert.assertEquals(myWishListPage.size(), 1, "Page My Wishlist is not loaded");
        return this;
    }

    public MyWishListPageMethods checkIfNameOfChosenItemIsPresent() {
        WebElement prodName = new WebDriverWait(getDriver(),5)
                .until(ExpectedConditions.presenceOfElementLocated(PRODUCT_NAME));

        Assert.assertEquals(prodName.getText(), getElementName(),
                "Selected element is not presented on the My Wish page");
        return this;
    }
}
