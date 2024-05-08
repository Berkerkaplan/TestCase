package com.saf.framework;

import org.openqa.selenium.WebDriver;

public class LocalDriver {
    private static  WebDriver tlDriver;

    public static synchronized void setTLDriver(WebDriver driver) { tlDriver=driver;}

    public static synchronized WebDriver getTLDriver() {
        return tlDriver;
    }
}