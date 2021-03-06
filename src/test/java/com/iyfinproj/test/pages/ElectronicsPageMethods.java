package com.iyfinproj.test.pages;

import com.iyfinproj.test.CommonUtils;
import com.iyfinproj.test.prodtransition.Product;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.List;

import static com.iyfinproj.test.Driver.getDriver;

public class ElectronicsPageMethods {
    private static final By BUTTON_SHOW_AS_LIST = By.xpath("//a[@class='list']");
    private static final By DROPDOWN_SELECT_ITEMS_PER_PAGE = By.xpath("//select[@title='Results per page']");
    private static final By PRODUCT_LIST = By.xpath("//ol[@id='products-list']/li");
    private static final By COUNTER = By.xpath("//div[@class='toolbar']//strong[contains(text(),'Item')]");
    private static final By BUTTON_NEXT_PAGE = By.xpath("//a[@class='next i-next']");
    private static final By DROPDOWN_SORTBY = By.cssSelector(".category-products > .toolbar select[title='Sort By']");
    private static final By PRICE_OF_GOODS = By.cssSelector(".product-shop .price-from > .price, .regular-price > .price");
    private static final By SHOP_BY_PRICE = By.xpath("//*[@id='narrow-by-list']//li[1]");
    private static final By BUTTON_GRID = By.xpath("//strong[@title='Grid']");
    private static final By PRODUCT_LIST_GRID = By.xpath("//ul[@class='products-grid products-grid--max-4-col first last odd']/li");
    private static final String XPATH_ELEMENT_GRID_BY_INDEX = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li[%s]//div[@class='actions']";
    private static final String XPATH_ELEMENT_BY_INDEX = "//ol[@id='products-list']/li[%s]//a[@class='link-wishlist']";
    private static final String XPATH_ELEMENT_NAME_BY_INDEX = "//ol[@id='products-list']/li[%s]//h2[@class='product-name']";
    private static final String XPATH_ELEMENT_BUTTON_ADDTOCART_BY_INDEX = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li[%s]//button[@title='Add to Cart']";
    private static final String XPATH_ELEMENT_NAME_LOCATOR_BY_INDEX = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li[%s]//h2[@class='product-name']";
    private static final String XPATH_ELEMENT_PRICE_LOCATOR_BY_INDEX = "//ul[@class='products-grid products-grid--max-4-col first last odd']/li[%s]//span[@class='price']";

    @Step("Click 'As List' button")
    public ElectronicsPageMethods clickAsListButton() {
        WebElement asListWebElement = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(BUTTON_SHOW_AS_LIST));
        asListWebElement.click();

        return this;
    }

    @Step("Set items per Page")
    public ElectronicsPageMethods setItemsPerPage(String perPageItems) {
        Select selectItemsPerPage = new Select(getDriver().findElement(DROPDOWN_SELECT_ITEMS_PER_PAGE));
        selectItemsPerPage.selectByVisibleText(perPageItems);

        return this;
    }

    @Step("Verify page counter value is equal to the quantity of loaded items ")
    public ElectronicsPageMethods checkCountLoadedItemsIsEquaulToPageCounter() throws InterruptedException {
        new WebDriverWait(getDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
        //Thread.sleep(5000);
        List<WebElement> listOfItems = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_LIST));

        Assert.assertEquals(listOfItems.size(), getValueOfPageCounter(), "The loaded elements quantity is not equal to the setted value");
        return this;

    }

    @Step("Getting the value of page counter")
    public static int getValueOfPageCounter() {
        WebElement counter = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfElementLocated(COUNTER));
        String counterStr = counter.getText();
        return CommonUtils.getIntFromString(counterStr);
    }

    @Step("Checking the quantity of items per page")
    public ElectronicsPageMethods checkQuantityOfItemsPerPage(int mustBeQuantity) {

        int elementsQuantity = 0;
        boolean compareQuantity = true;

        while (CommonUtils.isByElementDisplayed(BUTTON_NEXT_PAGE)) {
            List<WebElement> listOfItems = new WebDriverWait(getDriver(), 10)
                    .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_LIST));
            elementsQuantity = listOfItems.size();
            if (elementsQuantity != mustBeQuantity) {
                compareQuantity = false;
            }
            new WebDriverWait(getDriver(), 10)
                    .until(ExpectedConditions.elementToBeClickable(BUTTON_NEXT_PAGE)).click();
        }

        List<WebElement> listOfItems = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_LIST));
        elementsQuantity = listOfItems.size();
        if (elementsQuantity > mustBeQuantity) {
            compareQuantity = false;
        }
        Assert.assertEquals(compareQuantity, true, "The loaded elements quantity is not equal to the setted value");

        return this;
    }

    @Step("Set the 'Sort By' filter")
    public ElectronicsPageMethods selectPriceInSortBySelection(String value) {
        Select sortBy = new Select(getDriver().findElement(DROPDOWN_SORTBY));
        sortBy.selectByVisibleText(value);
        return this;
    }

    @Step("Verify if price of each next item higher than previous one")
    public ElectronicsPageMethods checkIfPriceOfEachNextItemHigherThanPrevious() {

        double amountOfCurrentElement;
        double min = 0;
        boolean flag = true;

        List<WebElement> prices = getDriver().findElements(PRICE_OF_GOODS);

        for (WebElement price : prices) {
            amountOfCurrentElement = CommonUtils.getDoubleFromString(price.getText());
            if (amountOfCurrentElement >= min) {
                min = amountOfCurrentElement;
            } else {
                flag = false;
                break;
            }
        }
        Assert.assertEquals(flag, true, "Data are not sorted by price from min to max");
        return this;
    }

    @Step("Set filter in price section")
    public ElectronicsPageMethods setFilterInPriceSection() {
        WebElement shopByPrice = getDriver().findElement(SHOP_BY_PRICE);
        shopByPrice.click();
        return this;
    }

    public ElectronicsPageMethods checkIfPriceOfElementsIsLessThanAmount(int noMoreValue) {
        boolean flag = true;
        List<WebElement> prices = getDriver().findElements(PRICE_OF_GOODS);
        for (WebElement price : prices) {
            if (CommonUtils.getDoubleFromString(price.getText()) > noMoreValue) {
                flag = false;
                break;
            }
        }
        Assert.assertEquals(flag, true, "Page contains elements with price more than " + noMoreValue);
        return this;
    }

    @Step ("Get name of goods from Wish ")
    public String getWishName() {

        new WebDriverWait(getDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        List<WebElement> elementsOnPage = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_LIST));
        int quantityOfElementsOnPage = elementsOnPage.size();

        int randomElementNumber = 1 + (int) (Math.random() * quantityOfElementsOnPage);

        WebElement randomElement = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(XPATH_ELEMENT_BY_INDEX, randomElementNumber))));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", randomElement);

        WebElement randomElementName = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(XPATH_ELEMENT_NAME_BY_INDEX, randomElementNumber))));
        String chosenName = randomElementName.getText();

        randomElement.click();
        return chosenName;
    }

    @Step("Click the 'Grid' button")
    public ElectronicsPageMethods clickGridButton() {
        WebElement gridButton = getDriver().findElement(BUTTON_GRID);
        gridButton.click();

        return this;
    }

    @Step("Getting name and price of random element")
    public Product getNameAndPriceOfRandomElement() throws InterruptedException {

        int randomElementNumber;

        new WebDriverWait(getDriver(), 10).until(
                webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        List<WebElement> elementsOnPage = new WebDriverWait(getDriver(), 10)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(PRODUCT_LIST_GRID));
        int quantityOfElementsOnPage = elementsOnPage.size();

        while (true) {
            randomElementNumber = 1 + (int) (Math.random() * quantityOfElementsOnPage);
            WebElement buttonContainer = getDriver()
                    .findElement(By.xpath(String.format(XPATH_ELEMENT_GRID_BY_INDEX, randomElementNumber)));
            String str = buttonContainer.getText();
            if (str.contains("ADD TO CART")) {
                break;
            }
        }

        // Find AddToCart button, Name and Price elements for generated locators
        WebElement randomElementAddToCartButton = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath(String.format(XPATH_ELEMENT_BUTTON_ADDTOCART_BY_INDEX, randomElementNumber))));
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView();", randomElementAddToCartButton);

        WebElement randomElementName = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(
                        By.xpath(String.format(XPATH_ELEMENT_NAME_LOCATOR_BY_INDEX, randomElementNumber))));
        WebElement randomElementPrice = new WebDriverWait(getDriver(), 5)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(XPATH_ELEMENT_PRICE_LOCATOR_BY_INDEX, randomElementNumber))));

        Product item = Product.builder()
                .newPrice(CommonUtils.getDoubleFromString(randomElementPrice.getText()))
                .name(randomElementName.getText())
                .build();

        randomElementAddToCartButton.click();
        return item;
    }
}
