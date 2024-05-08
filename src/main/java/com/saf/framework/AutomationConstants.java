package com.saf.framework;

import java.util.HashMap;

public class AutomationConstants

{

    public static String sIEDriverPath      	= "/Exes/IEDriverServer.exe";
    public static String sChromeDriverPath  	= "/Exes/chromedriver.exe";
    public static String sGeckoDriverPath   	= "/Exes/geckodriver.exe";

  

    public static HashMap<String, String> globalVariables = new HashMap<String, String>();

    public static long lngImplicitWaitTimeout;
    public static long lngPageLoadTimeout;
}
