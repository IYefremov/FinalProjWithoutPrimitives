package com.iyfinproj.test.tests;

import com.iyfinproj.test.Driver;
import org.testng.annotations.AfterMethod;

public class BaseTest {
    @AfterMethod (alwaysRun = true)
    public void tearDown() throws InterruptedException {
        Thread.sleep(5000); // Debug purpose only
        Driver.quit();
    }
}
