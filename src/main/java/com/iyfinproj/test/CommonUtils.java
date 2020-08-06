package com.iyfinproj.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.iyfinproj.test.Driver.getDriver;

public class CommonUtils {

    public static int getIntFromString(String str) {
        return Integer.parseInt(str.replaceAll("\\D*", ""));
    }

    public static double getDoubleFromString(String str) {
        return Double.parseDouble(str.replaceAll("[^0-9^.]?", ""));
    }

    public static boolean isByElementDisplayed(By element) {
        List<WebElement> elementsList = getDriver().findElements(element);
        return elementsList.size() != 0;
    }

    public static String generateRandomEmail() {

        DateFormat dateFormat = new SimpleDateFormat("yy_MM_dd_HH_mm_ss");
        Date date = new Date();
        String retData = dateFormat.format(date);

        return dateFormat.format(date)+"@gmail.com";


    }
}
