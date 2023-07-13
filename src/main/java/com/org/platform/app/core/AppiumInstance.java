package com.org.platform.app.core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumInstance {
    public static void main(String[] args) {
        // Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1"); // Set the IP address of the machine running the Appium server
        builder.usingPort(4723); // Set the port number
        builder.withArgument(() -> "--allow-insecure", "chromedriver_autodownload"); // Optional: enable chromedriver autodownload
        // Start the Appium server
        AppiumDriverLocalService service = AppiumDriverLocalService.buildService(builder);
        service.start();
        // Perform tests or other actions
        // Stop the Appium server
        URL appiumServerURL = null;
        try {
            appiumServerURL = new URL("http://localhost:4723/wd/hub");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        // Create DesiredCapabilities for the Android device
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Your_Device_Name");
        desiredCapabilities.setCapability("appPackage", "com.example.app");
        desiredCapabilities.setCapability("appActivity", "com.example.app.MainActivity");

        // Create an instance of AndroidDriver
        AppiumDriver driver = new AndroidDriver(appiumServerURL, desiredCapabilities);



        // Perform actions on the app
        // ...

        // Quit the driver
        driver.quit();
        service.stop();
    }
}
