package com.iyfinproj.test.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import static com.iyfinproj.test.Driver.getDriver;

public class ShoppingCartPageMethods {
    private static final By PAGE_SHOPPING_CART_TITLE = By.xpath("//h1[.='Shopping Cart']");
    private static final By NAME_IN_SHOPPING_CART = By.xpath("//h2[@class='product-name']");
    private static final By PRICE_IN_SHOPPING_CART = By.xpath("//td[@class='product-cart-price']//span[@class='price']");
    private static final By SUBTOTAL = By.xpath("//tbody//td[@class='a-right']//span[@class='price']");
    private static final By TOTAL = By.xpath("//tfoot//td[@class='a-right']//span[@class='price']");

    private String name;
    private String price;

    public String getName() {
        return name;
    }

    public ShoppingCartPageMethods setNameToVerify(String name)
    {
        this.name = name;
        return this;
    }

    public String getPrice() {
        return price;
    }

    public ShoppingCartPageMethods setPriceToVerify(String price) {

        this.price = price;
        return this;
    }

    public ShoppingCartPageMethods checkIfShoppingCartPageIsShown() {
        WebElement myWishListPage = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(PAGE_SHOPPING_CART_TITLE));

        Assert.assertEquals(myWishListPage.getText(), "SHOPPING CART", "Page Shopping Cart is not loaded");

        return this;
    }

    public ShoppingCartPageMethods checkIfNameAndPriceOfChosenItemIsPresent() {
        WebElement prodName = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(NAME_IN_SHOPPING_CART));
        Assert.assertEquals(prodName.getText(), this.name,
                "Chosen element is not presented on the Shopping Cart page");
        WebElement prodPrice = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(PRICE_IN_SHOPPING_CART));
        Assert.assertEquals(prodPrice.getText(), this.price,
                "Chosen element is not presented on the Shopping Cart page");

        return this;
    }

    public ShoppingCartPageMethods checkGrandTotalAmountIsEqualToTheSubtotal() {
        WebElement subtotal = getDriver().findElement(SUBTOTAL);
        WebElement total = getDriver().findElement(TOTAL);

        Assert.assertEquals(subtotal.getText(), total.getText(),
                "SUBTOTAL value is not equal to the TOTAL value on Shopping Cart page");
        return this;
    }
}
