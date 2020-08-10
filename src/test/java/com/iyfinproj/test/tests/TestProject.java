package com.iyfinproj.test.tests;
import static com.iyfinproj.test.pages.HomePageMethods.Language.ENG;
import com.iyfinproj.test.pages.HomePageMethods;
import com.iyfinproj.test.pages.MyWishListPageMethods;
import com.iyfinproj.test.pages.ShoppingCartPageMethods;
import com.iyfinproj.test.prodtransition.Product;
import org.testng.annotations.Test;

public class TestProject extends BaseTest {
    HomePageMethods.Language language = ENG;

    @Test(invocationCount = 1)
    public void checkItemsCounter() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(language)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("25")
                .checkCountLoadedItemsIsEquaulToPageCounter();
    }

    @Test(invocationCount = 1)
    public void checkShowSelect() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(language)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("5")
                .checkQuantityOfItemsPerPage(5);
    }

    @Test(invocationCount = 1)
    public void checkSortBy() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(language)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("25")
                .selectPriceInSortBySelection("Price")
                .checkIfPriceOfEachNextItemHigherThanPrevious();
    }

    @Test(invocationCount = 1)
    public void checkPriceFilter() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(language)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("25")
                .setFilterInPriceSection()
                .checkIfPriceOfElementsIsLessThanAmount(1000);
    }

    @Test(invocationCount = 1)
    public void checkAddToWishList() throws InterruptedException {
        String wishName = new HomePageMethods()
                .selectLanguage(language)
                .goToLoginPage()
                .goToAccountForm()
                .registerUser()
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("25")
                .getWishName();
        new MyWishListPageMethods()
                .setElementName(wishName)
                .checkIfMyWishPageIsShown()
                .checkIfNameOfChosenItemIsPresent();
    }

    @Test(invocationCount = 1)
    public void CheckSale() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(language)
                .goToSalePage()
                .clickGridButton()
                .setItemsPerPage("36")
                .checkOldPriceHigherThanSalePriceForEachItem();
    }

    @Test(invocationCount = 1)
    public void checkShoppingCart() throws InterruptedException {
        Product item = new HomePageMethods()
                .selectLanguage(language)
                .goToLoginPage()
                .goToAccountForm()
                .registerUser()
                .goToElectronicsPage()
                .clickGridButton()
                .setItemsPerPage("36")
                .getNameAndPriceOfRandomElement();
        new ShoppingCartPageMethods()
                .checkIfShoppingCartPageIsShown()
                .checkIfNameAndPriceOfChosenItemIsPresent(item)
                .checkGrandTotalAmountIsEqualToTheSubtotal();
    }
}



