package com.manhpd.selenium;

import com.manhpd.utils.Constants;
import com.manhpd.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class Ex2_GetContentElement {

    private WebDriver webDriver;

    public static void main(String[] args) {
        Ex2_GetContentElement mainDriver = new Ex2_GetContentElement();
        mainDriver.setWebDriver(SeleniumUtils.initChromeDriver(Constants.BASE_URL));

//        mainDriver.getContentWith("col-md-12");
//        mainDriver.getLinkWithText("This is a link");
//        mainDriver.fillContentInTextBox("fname", "Hello world");
//        mainDriver.clearTextBox("fname");

//        List<String> contents = mainDriver.getContentOfDropDown("testingDropdown");
//        contents.stream().forEach(System.out::println);

//        mainDriver.clickBtn("idOfButton");

        mainDriver.closeBrowser();
    }

    public void setWebDriver(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void closeBrowser() {
        this.webDriver.close();
    }

    public void refreshBrowser() {
        this.webDriver.navigate().refresh();
    }

    public String getContentWith(String classContent) {
        String content = this.webDriver.findElement(By.className(classContent)).getText();
        System.out.println(content);

        return content;
    }

    public String getLinkWithText(String text) {
        WebElement element = this.webDriver.findElement(By.linkText(text));
        String linkText = element.getText();
        System.out.println("Link Text: " + linkText);

        // go to the other site
        element.click();

        return linkText;
    }

    public void fillContentInTextBox(String id, String filledText) {
        WebElement element = this.webDriver.findElement(By.id(id));
        element.sendKeys(filledText);
    }

    public void clearTextBox(String id) {
        WebElement element = this.webDriver.findElement(By.id(id));
        element.clear();
    }

    /**
     * Working with dropdown
     * @param id
     * @return
     */
    public List<String> getContentOfDropDown(String id) {
        WebElement element = this.webDriver.findElement(By.id(id));
        Select dropDown = new Select(element);

        List<WebElement> options = dropDown.getOptions();
        List<String> contents = options.stream()
               .map(elem -> elem.getText())
               .collect(Collectors.toList());

        return contents;
    }

    public void clickBtn(String id) {
        WebElement element = this.webDriver.findElement(By.id(id));
        element.click();
    }
}
