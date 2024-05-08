package com.saf.framework;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;


public class BaseTest {
    public  String sDriverName = "";
    @BeforeMethod
    @Parameters({"browserName"})
    public void setup( @Optional("") String browserName) throws Exception {

        if (browserName.equalsIgnoreCase("ie")) {
            sDriverName = "ie";
        }
        else if (browserName.equalsIgnoreCase("firefox")) {
            sDriverName = "firefox";
        }
        else if (browserName.equalsIgnoreCase("chrome")) {
            sDriverName = "chrome";
        }
        else if (browserName.equalsIgnoreCase("htmlunit")) {
            sDriverName = "htmlunit";
        }
        else{
            throw new Exception("Unknown driver name = " + sDriverName +
                    "Valid names are: ie,firefox,chrome,htmlunit");
        }



        LocalDriver.setTLDriver(getDriver(sDriverName));
    }


    @AfterMethod
    public synchronized void teardown() {
        LocalDriver.getTLDriver().quit();
    }

    public WebDriver getDriver(String sBrowserName) throws Exception {

        WebDriver oDriver;

        switch (getBrowserId(sBrowserName)) {
            case 1:
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + AutomationConstants.sIEDriverPath);
                WebDriverManager.iedriver().setup();
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + AutomationConstants.sIEDriverPath);

                oDriver = new InternetExplorerDriver(getIEOptions());
                break;

            case 2:
                System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + AutomationConstants.sGeckoDriverPath);
                WebDriverManager.firefoxdriver().setup();
                oDriver = new FirefoxDriver(getFirefoxOptions());
                break;

            case 3:
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + AutomationConstants.sChromeDriverPath);
                WebDriverManager.chromedriver().setup();
                oDriver = new ChromeDriver(getChromeOptions());
                break;

            default:
                throw new Exception("Unknown browsername =" + sBrowserName +
                        " valid names are: ie,firefox,chrome,htmlunit");
        }

        oDriver.manage().deleteAllCookies();
        oDriver.manage().timeouts().pageLoadTimeout(AutomationConstants.lngPageLoadTimeout, TimeUnit.SECONDS);
        oDriver.manage().timeouts().implicitlyWait(AutomationConstants.lngImplicitWaitTimeout, TimeUnit.SECONDS);

        return oDriver;

    }



    public static InternetExplorerOptions getIEOptions() throws Exception {
        InternetExplorerOptions oIEOptions = new InternetExplorerOptions();
        oIEOptions.merge(getCapability());
        oIEOptions.ignoreZoomSettings();
        oIEOptions.introduceFlakinessByIgnoringSecurityDomains();

        return oIEOptions;
    }

    public static ChromeOptions getChromeOptions() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.merge(getCapability());
        chromeOptions.addArguments("test-type");
        //Dil çevirme penceresini kapattırma.
        chromeOptions.addArguments("disable-translate");
        //Browser tam ekranda gösterilir.
        chromeOptions.addArguments("start-maximized");
        //Pop-uplar bloklanır.
        chromeOptions.addArguments("disable-popup-blocking");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        //Alertler bloklanır.
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-alert");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NONE);
        return chromeOptions;
    }

    public static FirefoxOptions getFirefoxOptions() throws Exception {
        FirefoxOptions oFirefoxOptions = new FirefoxOptions();
        oFirefoxOptions.merge(getCapability());

        return oFirefoxOptions;
    }

    public static DesiredCapabilities getCapability() throws Exception {
        DesiredCapabilities oCapability = new DesiredCapabilities();
        oCapability.setJavascriptEnabled(true);
        //oCapability.setCapability("proxy", getProxy());

        return oCapability;
    }


    public static int getBrowserId(String sBrowserName) throws Exception {
        if (sBrowserName.equalsIgnoreCase("ie")) return 1;
        if (sBrowserName.equalsIgnoreCase("firefox")) return 2;
        if (sBrowserName.equalsIgnoreCase("chrome")) return 3;
        if (sBrowserName.equalsIgnoreCase("htmlunit")) return 4;

        return -1;
    }

}