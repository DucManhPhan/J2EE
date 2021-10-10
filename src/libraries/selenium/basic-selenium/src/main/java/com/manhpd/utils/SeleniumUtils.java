package com.manhpd.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumUtils {

    public static WebDriver initChromeDriver(String url) {
        System.setProperty(Constants.CHROME_DRIVER, Constants.DRIVER_PATH);

        // create driver object
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        // 1st way - use get() method
//        driver.get(url);

        // 2nd way - use navigate() method
        driver.navigate().to(url);

        return driver;
    }
}
