package com.manhpd;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlaylistYoutube {

    public List<String> getPlaylistLinks() {
        int numVideos = 0;
        WebDriver webDriver = WebDriverSingleton.getInstance().getChromeDriver();
        WebElement bodyElement = webDriver.findElement(By.cssSelector("body"));

        while (true) {
            IntStream.range(1, 6).forEach(idx -> {
                // 1st way: use PAGE_DOWN, PAGE_UP keyboard
                this.scrollDownWithkeyboard(bodyElement);

                // 2nd way: use scroll
//                try {
//                    this.scrollDown(webDriver);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
            });

            int newNumVideos = webDriver.findElements(By.cssSelector("#contents > ytd-playlist-video-renderer")).size();
            if (newNumVideos == numVideos) {
                break;
            }

            numVideos = newNumVideos;
        }

         return RegexUtils.extractVideoIds(webDriver.getPageSource())
                          .stream()
                          .map(videoId -> Constants.DOWNLOADED_URL + videoId)
                          .collect(Collectors.toList());
    }

    public Map<String, String> getPlaylistWithTitle() {
        WebDriver webDriver = WebDriverSingleton.getInstance().getChromeDriver();
        Map<String, String> idWithTitle = new HashMap<>();

        // extract video id and title
        List<WebElement> videos = webDriver.findElements(By.cssSelector("#contents > ytd-playlist-video-renderer"));
        videos.stream().forEach(video -> {
            String videoId = this.extractVideoIdFromHrefAttribute(video);
            String title = this.extractTitleElement(video);

            idWithTitle.put(title, Constants.DOWNLOADED_URL + videoId);
        });

        return idWithTitle;
    }

    public String extractVideoIdFromHrefAttribute(WebElement elem) {
        WebElement videoElement = elem.findElement(By.cssSelector("a[href]"));
        String hrefAttribute = videoElement.getAttribute("href");

        return RegexUtils.extractVideoId(hrefAttribute);
    }

    public String extractTitleElement(WebElement elem) {
        WebElement titleElement = elem.findElement(By.cssSelector("span[title]"));
        return titleElement.getAttribute("title");
    }

    public void scrollDownWithkeyboard(WebElement element) {
        element.sendKeys(Keys.PAGE_DOWN);
        element.sendKeys(Keys.PAGE_DOWN);
        element.sendKeys(Keys.PAGE_DOWN);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void scrollDown(WebDriver driver) throws InterruptedException {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        int pauseTimeMs = 1000;
        int scrollHeight = (int) executor.executeScript("return document.body.scrollHeight");

        while (true) {
            // scroll down to the bottom of document
            executor.executeScript("window.scrollTo(0, document.body.scrollHeight);");

            // wait to load page
            Thread.sleep(pauseTimeMs);

            // calculate the new scroll height and compare with the last scroll height
            int newScrollHeight = (int) executor.executeScript("return document.body.scrollHeight");
            if (newScrollHeight == scrollHeight) {
                break;
            }

            scrollHeight = newScrollHeight;
        }
    }

    public void downloadVideos(List<String> links) {
        AtomicInteger index = new AtomicInteger();
        links.stream().forEach(link -> {
            String fileName = "" + index.incrementAndGet();
            this.download(link, fileName);
        });
    }

    public void downloadVideosWith(Map<String, String> titleWithLinks) {
        titleWithLinks.entrySet().stream().forEach(titleWithLink -> {
            String title = titleWithLink.getKey();
            String link = titleWithLink.getValue();

            this.download(link, title);
        });
    }

    public void download(String link, String fileName) {
        try {
            URL downloadedLink = new URL(link);
            File file = new File("./files/" + fileName + ".mp4");
            FileUtils.copyURLToFile(downloadedLink, file);

            System.out.println(fileName + "is downloaded successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
