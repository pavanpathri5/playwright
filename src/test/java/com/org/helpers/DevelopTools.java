package com.org.helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v112.network.Network;

import java.util.Optional;

public class DevelopTools {
    WebDriver driver;
    DevTools devTools;
    public DevelopTools(WebDriver driver){
        this.driver=driver;
        this.devTools = ((ChromeDriver) driver).getDevTools();
    }

    public void startsession(){
        System.out.println("session started");
        devTools.createSession();
        devTools.send(Network.enable(Optional.of(1000000), Optional.empty(), Optional.empty()));
    }
    public void verifyNetworkreqHeaders(){
        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Integer responseUrl = responseReceived.getResponse().getStatus();
                System.out.println("Url: " + responseUrl);
                System.out.println("Response headers: " + responseReceived.getResponse());
        });
    }
}
