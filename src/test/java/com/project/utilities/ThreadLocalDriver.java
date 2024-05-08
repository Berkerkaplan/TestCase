package com.project.utilities;


import org.openqa.selenium.WebDriver;

public class ThreadLocalDriver {
    private static  ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

    public static synchronized void setTLDriver(WebDriver oDriver) { tlDriver.set(oDriver);}


    public static synchronized WebDriver getTLDriver() {
        return tlDriver.get();
    }
}