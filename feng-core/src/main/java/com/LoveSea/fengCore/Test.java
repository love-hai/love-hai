package com.LoveSea.fengCore;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Collection;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiahaifeng
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\xiaha\\AppData\\Local\\JxBrowser\\7.19\\chromium.exe"); // 设置 ChromeDriver 路径
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--headless"); // 可选：如果你不需要 GUI，可以使用无头模式
        WebDriver driver = new ChromeDriver();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        String result = (String) js.executeScript("'是西安'");
        System.out.println("return" + result);

        driver.quit();
    }
}
