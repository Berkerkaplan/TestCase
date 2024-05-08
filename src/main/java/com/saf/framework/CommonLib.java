package com.saf.framework;


import groovyjarjarantlr4.v4.codegen.model.ExceptionClause;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.net.URL;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static com.saf.framework.BaseTest.getChromeOptions;

public class CommonLib {
    public WebDriver oDriver;
    public static String tcKimlikOlustur;
    //  private HashMap<String, String> variables = new HashMap<String, String>();
    public HashMap<String, String> variables = new HashMap<String, String>();
    Map<String, String> map = new HashMap<String, String>();
    public String page = "common";
    Parser parser= new Parser();
    Actions actions;


    WebDriverWait wait;
    int timeout = 30;

    public CommonLib(WebDriver oDriver, WebDriverWait wait) {
        this.oDriver = oDriver;
        this.wait = wait;
        this.actions = new Actions(oDriver);
    }

    public CommonLib() {

    }


    public WebElement findElementAndWaitUntilClickable(String elem) {
        WebElement object = null;
        String element = parser.getElement(page, elem);

        try {
            if (element != null) {
                if (element.startsWith("//") || element.startsWith("(//")) {
                    object = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(element)));

                    System.out.println("Element found : " + elem);
                } else if (element.startsWith("#") || element.startsWith(".")) {

                    object = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(element)));
                    System.out.println("Element found : " + elem);
                } else {
                    object = wait.until(ExpectedConditions.elementToBeClickable(By.id(element)));
                    System.out.println("Element found : " + elem);
                }
            } else if (element == null) {
                object = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='" + elem + "'or contains(text(),'" + elem + "')]")));
            }

            if (object == null) {
                System.out.println("Element not found: " + elem);
                Assert.fail("Element not found : " + elem);
            }
            return object;
        } catch (Exception e) {
            System.out.println("Element not found: " + elem);
            Allure.addAttachment("There is no such element.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "There is no such element. " + elem, true);
            Assert.fail("Element not found : " + elem);

            return null;

        }
    }


    public WebElement findElement(String elem, int index) {
        WebElement object = null;
        String element = parser.getElement(page, elem);

        try {
            if (element != null) {
                if (element.startsWith("//") || element.startsWith("(//")) {
                    object = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(element)));

                    System.out.println("Element found : " + elem);
                } else if (element.startsWith("#") || element.startsWith(".")) {
                    object = oDriver.findElements(By.cssSelector(element)).get(index - 1);
                    System.out.println("Element found : " + elem);
                } else {
                    object = oDriver.findElements(By.id(element)).get(index - 1);
                    System.out.println("Element found : " + elem);
                }
            } else if (element == null) {
                object = oDriver.findElement(By.xpath("//*[text()='" + elem + "'or contains(text(),'" + elem + "')]"));
            }

            if (object == null) {
                System.out.println("Element not found: " + elem);
                Assert.fail("Element not found : " + elem);
            }
            return object;
        } catch (Exception e) {
            System.out.println("Element not found: " + elem);
            Allure.addAttachment("There is no such element.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "There is no such element. " + elem, true);
            Assert.fail("Element not found : " + elem);

            return null;

        }
    }

    public WebElement findElement(String elem, int index, boolean flag) {
        WebElement object = null;
        String element = parser.getElement(page, elem);

        try {
            if (element != null && flag == false) {
                if (element.startsWith("//") || element.startsWith("(//")) {
                    object = oDriver.findElements(By.xpath(element)).get(index - 1);

                    System.out.println("Element found : " + elem);
                } else if (element.startsWith("#") || element.startsWith(".")) {
                    object = oDriver.findElements(By.cssSelector(element)).get(index - 1);
                    System.out.println("Element found : " + elem);
                } else {
                    object = oDriver.findElements(By.id(element)).get(index - 1);
                    System.out.println("Element found : " + elem);
                }
            } else if (element == null) {
                object = oDriver.findElement(By.xpath("//*[text()='" + elem + "'or contains(text(),'" + elem + "')]"));
            }

            if (object == null && flag == true) {
                System.out.println("Element not found: " + elem);
                Assert.fail("Element not found : " + elem);
            }
            return object;
        } catch (Exception e) {
            System.out.println("Element not found: " + elem);
            //Allure.addAttachment("There is no such element.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "There is no such element. " + elem, true);
            //Assert.fail("Element not found : " + elem);
            return null;
        }
    }

    public WebElement findElement(String elem) {
        WebElement object = null;
        String element = parser.getElement(page, elem);

        try {
            if (element != null) {
                if (element.startsWith("//") || element.startsWith("(//")) {
                    object = oDriver.findElement(By.xpath(element));

                    System.out.println("Element found : " + elem);
                } else if (element.startsWith("#") || element.startsWith(".")) {
                    object = oDriver.findElement(By.cssSelector(element));
                    System.out.println("Element found : " + elem);
                } else {
                    object = oDriver.findElement(By.id(element));
                    System.out.println("Element found : " + elem);
                }
            } else if (element == null) {
                object = oDriver.findElement(By.xpath("//*[text()='" + elem + "'or contains(text(),'" + elem + "')]"));
            }

            return object;
        } catch (Exception e) {
            System.out.println("Element not found: " + elem);
            //Allure.addAttachment("There is no such element.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "There is no such element. " + elem, true);
            //Assert.fail("Element not found : " + elem);
            return null;
        }
    }

    public String seePage(String page) {
        List<String> returnValue = parser.isPageExist(page);

        try {
            if (returnValue.get(0).equalsIgnoreCase(page)) {
                System.out.println(page + " page found!");
                this.page = page;
                if (returnValue.get(1).length() > 0) {
                    waitElement(returnValue.get(1), timeout, 1);
                }
                return page;
            }
        } catch (Exception e) {
            Assert.fail("Page not found! '" + page + "'");
        }
        return null;
    }

    public WebElement waitElement(String element, int timeout, int index) throws InterruptedException {
        WebElement object;
        try {
            for (int i = 0; i < timeout; i++) {

                object = findElement(element, index);
                if (object != null) {
                    Thread.sleep(2000);
                    return object;
                } else {
                    Thread.sleep(2000);
                }
            }
        } catch (Exception e) {
            Assert.fail("Waiting element is not found!");
            //reportResult("FAIL", "Element could not find. ", true);
        }
        return null;
    }

    public boolean checkDisplayed(String element) {
        boolean flag = false;
        WebDriverWait wait = new WebDriverWait(oDriver, 5);
        try {
            WebElement elem = findElement(element);
            if (elem != null) {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

//    public static boolean waitElementIfExist(WebDriver oDriver, WebElement element) {
//        boolean flag = false;
//        WebDriverWait wait = new WebDriverWait(oDriver, 60);
//
//        try {
//            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(String.valueOf(element))));
//            flag = true;
//        } catch (Exception e) {
//            flag = false;
//        }
//        return flag;
//    }

    public String readTheElementInformation(String elem, int index) {
        System.out.println(findElement(elem, index).getText());
        String elementText = findElement(elem, index).getText();
        System.out.println(elementText);
        return elementText;
    }

    public void doubleClickElement(WebElement object) {
        actions.doubleClick(object).perform();

    }

    public void clickElement(WebElement object) {
        actions.click(object).perform();

    }

    public String getRandomString() {
        String RANDOMCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * RANDOMCHARS.length());
            salt.append(RANDOMCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }

    public static Proxy getProxy() throws Exception {
        Proxy oProxy = new Proxy();
        String sProxyString = String.format("%s:%d", "AutomationConstants.sProxyHostName", "AutomationConstants.iProxyPort");

        oProxy.setProxyType(ProxyType.MANUAL);
        oProxy.setHttpProxy(sProxyString);
        oProxy.setSslProxy(sProxyString);

        return oProxy;

    }






    public static boolean setAttribute(WebDriver oDriver, By identifier, String attribute, String value) {
        if (oDriver.findElements(identifier).size() > 0) {
            WebElement oElement = oDriver.findElement(identifier);
            if (oElement.isDisplayed() && oElement.isEnabled()) {
                JavascriptExecutor jsExec = (JavascriptExecutor) oDriver;
                jsExec.executeScript("arguments[0].setAttribute('" + attribute + "','" + value + "')", oElement);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkElementDisplayed(WebDriver oDriver, By identifier) {
        boolean status = false;
        oDriver.manage().timeouts().implicitlyWait(TimeUnit.SECONDS.ordinal(), TimeUnit.SECONDS);
        try {
            if (oDriver.findElement(identifier).isDisplayed()) {
                status = true;
            }
        } catch (NoSuchElementException e) {
            System.out.println("Element not found");
        }
        return status;
    }

    public void navigateToURL(WebDriver oDriver, String URL) throws Exception {
        oDriver.manage().deleteAllCookies();
        oDriver.navigate().refresh();
        getChromeOptions().addArguments("--disable-notifications");
        oDriver.navigate().to(URL);
    }

    public boolean loginToApp(String username, String password) throws InterruptedException {
        Thread.sleep(15000);
        WebElement usernameObject = findElementAndWaitUntilClickable("username text area");
        //usernameObject = waitElement(username, 30, 1);

        WebElement passwordObject = findElement("password text area", 1);

        //passwordObject = waitElement(passwordElement, 30, 1);

        boolean flag = false;
        try {
            if (usernameObject != null && passwordObject != null) {

                usernameObject.sendKeys(username);
                passwordObject.sendKeys(password);
                System.out.println("The text has been entered:" + username + password);
                Allure.addAttachment("The text has been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                //reportResult("PASS", "I entered the text: " + username + password, true);

                return true;

            }
        } catch (Exception e) {
            Allure.addAttachment("The text has not been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "I cannot entered the element: " + username + password, true);
            Assert.fail("Could not entered the text:" + username + password);
            flag = false;
        }
        return flag;
    }

    public boolean checkElementNoVisibility(String elem, int index) {
        WebElement element = findElement(elem, index);
        boolean flag = false;
        if (!element.isDisplayed()) {
            System.out.println("The element has not been visible.");
            Allure.addAttachment("The element has not been visible.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("PASS", "The element has not been visible. ", true);
            flag = true;
        } else {
            Allure.addAttachment("The element has been visible.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "The element has been visible.", true);
            Assert.fail("The element has been visible.");
            return flag;
        }
        return flag;
    }

    public boolean checkElementVisibility(String elem, int index) {
        WebElement element = findElement(elem, index);
        boolean flag = false;
        if (element.isDisplayed()) {
            System.out.println("The element is visible.");
            Allure.addAttachment("The element is visible.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            flag = true;
        } else {
            Allure.addAttachment("The element is not visible.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            Assert.fail("The element is not visible.");
            return flag;
        }
        return flag;
    }


//    public void sendKey (String element, String text) {
//
//        try {
//            waitElementAndReturnIfLocated(element).sendKeys(text);
//            allureReport(StepResultType.PASS, element + " ", Boolean.TRUE);
//        } catch (Exception e) {
//            allureReport(StepResultType.FAIL, element + " is not displayed(Function: waitElement).", Boolean.TRUE);
//        }
//
//    }

    public boolean sendKeys(WebElement element, String text) {
        boolean flag = false;
        WebElement myelement = null;

        try {
            if (element.isDisplayed() && element.isEnabled()) {
                waitSeconds(1);
                element.click();
                if (element.getText().equals("")) {
                    element.clear();
                    waitSeconds(1);
                }
                element.sendKeys(text);
                Allure.addAttachment("The element is visible!", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));

                //MyTestNGBaseClass.reportResult("PASS", "A value has been entered in the " + element.getText() + " Input field.", true);
                return true;
            }
        } catch (Exception e) {
            // MyTestNGBaseClass.reportResult("FAIL", "Could not enter value for " + element.getText() + " element.", true);
            Assert.fail("Could not enter value for element." + element.getText());
            flag = false;
        }
        return flag;
    }

    public static void waitSeconds(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }




    public void waitElement(String element) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated((By) findElement(element)));
            allureReport(StepResultType.PASS, element + " is displayed.", Boolean.TRUE);
        } catch (Exception e) {
            allureReport(StepResultType.FAIL, element + " is not displayed(Function: waitElement).", Boolean.TRUE);
        }
    }




    public void allureReport(StepResultType result, String message, boolean ssFlag) {
        try {
            if (ssFlag) {
                Allure.addAttachment("Screenshot : " + message, new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            }
            if (result.toString().equalsIgnoreCase("PASS")) {
                Allure.step(message, Status.PASSED);
            } else if (result.toString().equalsIgnoreCase("INFO")) {
                Allure.step(message, Status.SKIPPED);
            } else if (result.toString().equalsIgnoreCase("FAIL")) {
                Allure.step(message, Status.FAILED);
                Assert.fail(message);
            } else {
                Allure.step(message);
            }

        } catch (Exception e) {
        }

    }

    public void sleep(int seconds) {
        try {
            Thread.sleep((long) seconds * 1000);
        } catch (Exception e) {
            //
        }
    }

    public void waitAndClick(String element) {
        try {
            waitElementAndReturnIfLocated(element).click();
            allureReport(StepResultType.PASS, element + " is clicked.", Boolean.TRUE);
        } catch (Exception e) {
            allureReport(StepResultType.FAIL, element + "Error while clicking " + element + " element.", Boolean.TRUE);
        }
    }



    public WebElement waitElementAndReturnIfLocated(String element) {
        WebElement myelement = null;
        try {
            myelement = wait.until(ExpectedConditions.visibilityOfElementLocated((By) findElement(element)));
            allureReport(StepResultType.PASS, element + " is displayed.", Boolean.TRUE);
        } catch (Exception e) {
            allureReport(StepResultType.FAIL, element + " is not displayed.", Boolean.TRUE);
        }
        return myelement;
    }



//    public WebElement waitElementWithIndexAndReturnIfClickable(String element, int index) {
//        WebElement myelement = null;
//        try {
//            myelement = findLocator(getLocator(element), index);
//            allureReport(StepResultType.PASS, element + " element is found.", Boolean.TRUE);
//        } catch (Exception e) {
//            allureReport(StepResultType.FAIL, element + " element at " + index + ".index could not found.", Boolean.TRUE);
//        }
//
//        if (myelement != null) {
//            try {
//                wait.until(ExpectedConditions.elementToBeClickable(myelement));
//                allureReport(StepResultType.PASS, element + " element is clickable.", Boolean.TRUE);
//            } catch (Exception e) {
//                allureReport(StepResultType.FAIL, element + " is not clickable.", Boolean.TRUE);
//            }
//        } else {
//            allureReport(StepResultType.FAIL, element + " is not found.", Boolean.TRUE);
//        }
//        return myelement;
//    }




    public static boolean waitElementVisible(WebDriver oDriver, WebElement element) {
        boolean flag;
        WebDriverWait wait = new WebDriverWait(oDriver, 60);
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public static boolean waitElementClickable(WebDriver oDriver, WebElement element) {
        boolean flag;
        WebDriverWait wait = new WebDriverWait(oDriver, 60);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }


    public void setVariables(String key, String value){

        variables.put(key,value);

    }

    public String getVariables(String key) {

        return variables.get(key);

    }




    public void setGlobalVariables(String key, String value) {

        AutomationConstants.globalVariables.put(key,value);
    }

    public String getGlobalVariables(String key) {

        return AutomationConstants.globalVariables.get(key);

    }
}

