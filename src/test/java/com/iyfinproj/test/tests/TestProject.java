package com.iyfinproj.test.tests;

import static com.iyfinproj.test.pages.HomePageMethods.Language.AUTO;
import static com.iyfinproj.test.pages.HomePageMethods.Language.ENG;

import com.iyfinproj.test.pages.HomePageMethods;

import com.iyfinproj.test.pages.MyWishListPageMethods;
import com.iyfinproj.test.pages.ShoppingCartPageMethods;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TestProject extends BaseTest {
    @DataProvider
    public Object[] myDataProvider(){
        return new Object[]{
                "Data 1 from my data provider",
                "Data 2 from my data provider",
                "Data 3 from my data provider",
                "Data 4 from my data provider",
                "Data 5 from my data provider"
        };
    }

    @Test(invocationCount = 1, dataProvider = "myDataProvider")
    public void checkItemsCounter(String value) throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(AUTO)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("25")
                .checkCountLoadedItemsIsEquaulToPageCounter();
        System.out.println(value);
    }

    @Test(invocationCount = 1)
    public void checkShowSelect() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(AUTO)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("5")
                .checkQuantityOfItemsPerPage(5);
    }

    @Test(invocationCount = 1)
    public void checkSortBy() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(AUTO)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("25")
                .selectPriceInSortBySelection("Price")
                .checkIfPriceOfEachNextItemHigherThanPrevious();
    }


    @Test(invocationCount = 1)
    public void checkPriceFilter() throws InterruptedException {
        new HomePageMethods()
                .selectLanguage(AUTO)
                .goToElectronicsPage()
                .clickAsListButton()
                .setItemsPerPage("25")
                .setFilterInPriceSection()
                .checkIfPriceOfElementsIsLessThanAmount(1000);
    }

    @Test(invocationCount = 1)
    public void checkAddToWishList() throws InterruptedException {
        String wishName = new HomePageMethods()
                .selectLanguage(AUTO)
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
                .selectLanguage(AUTO)
                .goToSalePage()
                .clickGridButton()
                .setItemsPerPage("36")
                .checkOldPriceHigherThanSalePriceForEachItem();
    }

    @Test(invocationCount = 1)
    public void checkShoppingCart() throws InterruptedException {
        List<String> item = new HomePageMethods()
                .selectLanguage(AUTO)
                .goToLoginPage()
                .goToAccountForm()
                .registerUser()
                .goToElectronicsPage()
                .clickGridButton()
                .setItemsPerPage("36")
                .getNameAndPriceOfRandomElement();

        new ShoppingCartPageMethods()
                .setNameToVerify(item.get(0))
                .setPriceToVerify(item.get(1))
                .checkIfShoppingCartPageIsShown()
                .checkIfNameAndPriceOfChosenItemIsPresent()
                .checkGrandTotalAmountIsEqualToTheSubtotal();
    }
}



