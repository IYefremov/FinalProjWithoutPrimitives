package com.iyfinproj.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver.get() == null) {
            init();
        }
        return driver.get();
    }


    private static synchronized void init() {

        String browser = System.getProperty("browser", "chrome");

        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver.set(new ChromeDriver());
                break;
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver.set(new FirefoxDriver());
                break;
            case "ie":
                WebDriverManager.iedriver().setup();
                driver.set(new InternetExplorerDriver());
                break;
            case "edge":
                WebDriverManager.edgedriver().setup();
                driver.set(new EdgeDriver());
            default:
                throw new IllegalArgumentException(String.format("%s isn't recognized", browser));
        }
        driver.get().manage().deleteAllCookies();
        driver.get().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        //driver.manage().window().maximize();
        driver.get().get(ProjectConstants.url);

    }

    public static void quit() {
        if (driver.get() != null) {
            driver.get().close();
            driver.remove();
        }
    }

}
