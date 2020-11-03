package com.manhpd.selenium;

import com.manhpd.utils.Constants;
import com.manhpd.utils.SeleniumUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Step 1: Download chromedriver from: http://chromedriver.storage.googleapis.com/index.html
 *
 * Step 2: Select the suitable version of chromedriver.exe for our current chrome browser
 */
public class Ex1_StartSeleniumDriver {

    private WebDriver driver;

    public static void main(String[] args) {
        Ex1_StartSeleniumDriver driverMain = new Ex1_StartSeleniumDriver();
        driverMain.setWebDriver(SeleniumUtils.initChromeDriver(Constants.BASE_URL));

        driverMain.displayInfo();
    }

    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }

    public void displayInfo() {
        String url = this.driver.getCurrentUrl();
        System.out.println(url);

        String title = this.driver.getTitle();
        System.out.println(title);
    }

}
