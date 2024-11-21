package com.saucedemo.utils;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotManager {
    public static void takeScreenshot(WebDriver driver, String testName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File screenshotFile = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
        String fileName = "screenshot_" + testName + "_" + timestamp + ".png";
        Path path = Paths.get("./screenshots", fileName);
        try {
            Files.createDirectories(path.getParent());
            Files.copy(screenshotFile.toPath(), path);
            Allure.addAttachment("Screenshot on Failure", "image/png", Files.newInputStream(path), ".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
