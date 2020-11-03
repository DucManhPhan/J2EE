package com.manhpd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class WebDriverSingleton {

    private static WebDriverSingleton singleton;

    private WebDriver webDriver;

    private WebDriverSingleton() {
        System.setProperty(Constants.CHROME_DRIVER, Constants.DRIVER_PATH);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");

        // define options for hiding chrome browser
        this.webDriver = new ChromeDriver(options);
        this.webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        this.webDriver.manage().window().maximize();

        this.webDriver.get(Constants.PLAYLIST_CHANNEL_URL + Constants.PLAYLIST_ID);
    }

    public static WebDriverSingleton getInstance() {
        if (Objects.isNull(singleton)) {
            singleton = new WebDriverSingleton();
        }

        return singleton;
    }

    public WebDriver getChromeDriver() {
        return this.webDriver;
    }

}
