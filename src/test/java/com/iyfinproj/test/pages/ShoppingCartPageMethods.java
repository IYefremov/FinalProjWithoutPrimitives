package com.iyfinproj.test.pages;

import com.iyfinproj.test.CommonUtils;
import com.iyfinproj.test.prodtransition.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import static com.iyfinproj.test.Driver.getDriver;

public class ShoppingCartPageMethods {
    private static final By PAGE_SHOPPING_CART_TITLE = By.xpath("//h1[.='Shopping Cart']");
    private static final By NAME_IN_SHOPPING_CART = By.xpath("//h2[@class='product-name']");
    private static final By PRICE_IN_SHOPPING_CART = By.xpath("//td[@class='product-cart-price']//span[@class='price']");
    private static final By SUBTOTAL = By.xpath("//tbody//td[@class='a-right']//span[@class='price']");
    private static final By TOTAL = By.xpath("//tfoot//td[@class='a-right']//span[@class='price']");

    @Step("Verify the 'Shopping Cart' page is displayed")
    public ShoppingCartPageMethods checkIfShoppingCartPageIsShown() {
        WebElement myWishListPage = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(PAGE_SHOPPING_CART_TITLE));

        Assert.assertEquals(myWishListPage.getText(), "SHOPPING CART", "Page Shopping Cart is not loaded");

        return this;
    }
    @Step("Verify the name and price of chosen element is presented")
    public ShoppingCartPageMethods checkIfNameAndPriceOfChosenItemIsPresent(Product prod) {
        WebElement prodName = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(NAME_IN_SHOPPING_CART));
        Assert.assertEquals(prodName.getText(), prod.getName(),
                "Chosen element is not presented on the Shopping Cart page");
        WebElement prodPrice = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(PRICE_IN_SHOPPING_CART));

        Assert.assertEquals(CommonUtils.getDoubleFromString(prodPrice.getText()), prod.getNewPrice(),
                "Chosen element is not presented on the Shopping Cart page");

        return this;
    }

    @Step("Verify the grand total amount is equal to the subtotal")
    public ShoppingCartPageMethods checkGrandTotalAmountIsEqualToTheSubtotal() {
        WebElement subtotal = getDriver().findElement(SUBTOTAL);
        WebElement total = getDriver().findElement(TOTAL);

        Assert.assertEquals(subtotal.getText(), total.getText(),
                "SUBTOTAL value is not equal to the TOTAL value on Shopping Cart page");
        return this;
    }
}
