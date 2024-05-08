package com.project.stepdefs;

import com.mysql.cj.jdbc.Driver;

import com.saf.framework.*;
import db.Methods.DBFunction;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.core.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import io.qameta.allure.model.Status;
import org.apache.tools.ant.taskdefs.Java;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.Assert;
import org.testng.annotations.Parameters;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;

public class StepDefs {

    public WebDriver oDriver;
    CommonLib commonLib;
    DBFunction dbFunction = new DBFunction();
    int timeout = 30;
    public String uuid = UUID.randomUUID().toString();
    public boolean checkLoginControl = false;
    public HashMap<String, String> strings = new HashMap<String, String>();
    InputStream stringsis;
    TestUtils utils;
    Parser parser = new Parser();
    WebDriverWait wait;


    @Before
    public void setup() {
        oDriver = LocalDriver.getTLDriver();
        wait = new WebDriverWait(oDriver, 30);
        commonLib = new CommonLib(oDriver, wait);
    }


    @After
    public void afterScenario(Scenario scenario) {
        oDriver.quit();

    }


    @Given("Open the {string}")
    public void openUrl(String URL) throws Exception {
        try {
            System.out.println("Test başladı -------------------------------------------------------");
            commonLib.navigateToURL(oDriver, URL);
        } catch (Exception e) {
            System.out.println("url açılamadı ---------------------------------------------------------------------------");
        }

    }

    @When("LoginToSOT {string} and {string}")
    public void loginToApp(String username, String password) throws Throwable {

        Thread.sleep(20000);
        commonLib.loginToApp(username, password);
    }






    @When("I click {string} element at index {int}")
    public void ElementClick(String element, int index) throws Throwable {
        clickElement(element, index);
    }

    @And("I must able to see {string} element at index {int}")
    public void seeControl(String element, int index) throws Throwable {
        commonLib.checkElementVisibility(element, index);
    }

    @And("I must be able to see {string} element")
    public void see2Control(String element) throws Throwable {

        for (int i = 1; i < 50; i++) {

            commonLib.checkElementVisibility(element, 1);
        }
    }

    @Then("^I see (.*) page$")
    public void seePage(String page) {
        commonLib.seePage(page);
    }

    @When("^(?:I )?click element: (\\w+(?: \\w+)*) at index (\\d+)")
    public boolean clickElement(String element, int index) {
        WebElement object = commonLib.findElement(element, index);
        // WebElement object = commonLib.findElementAndWaitUntilClickable(element);

        boolean flag = false;
        try {
            if (object != null) {
                object.click();
                System.out.println("Clicked on object-->" + element);
                Allure.addAttachment("Element is clicked.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                // reportResult("PASS", "I clicked the element: " + element, true);
                return true;
            }
        } catch (Exception e) {
            //reportResult("FAIL", "I cannot clicked the element: " + element, true);
            Allure.addAttachment("Element is not clicked.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            Assert.fail("Could not clicked the element:" + element);
            flag = false;
        }
        return flag;
    }



    @Then("^I enter \"([^\"]*)\" text to (.*) at index (\\d+)$")
    public boolean enterText(String text, String element, int index) throws InterruptedException {
        WebElement object;
        object = commonLib.waitElement(element, timeout, index);
        boolean flag = false;
        try {
            if (object != null) {
//                WebDriver driver = null;
//                WebElement webElement = null;
//                String value = null;

//                JavascriptExecutor jse = (JavascriptExecutor)oDriver;
//                jse.executeScript("arguments[0].value='"+ text +"';", object);
                // object.sendKeys(Keys.NUMPAD1);
                object.sendKeys(text);
                System.out.println("The text has been entered:" + text);
                Allure.addAttachment("The text has been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                //reportResult("PASS", "I entered the text: " + text, true);

                return true;
            }
        } catch (Exception e) {
            Allure.addAttachment("The text has not been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //  reportResult("FAIL", "I cannot entered the element: " + text, true);
            Assert.fail("Could not entered the text:" + text);
            flag = false;
        }
        return flag;
    }

    @Then("^I enter a email to (.*) at index (\\d+)")
    public boolean enterEmail(String element, int index) throws InterruptedException {
        WebElement object;
        object = commonLib.waitElement(element, timeout, index);
        boolean flag = false;
        String email = commonLib.getRandomString() + "@example.com";
        try {
            if (object != null) {
                object.sendKeys(email);
                System.out.println("The email has been entered:" + email);
                Allure.addAttachment("The email has been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                // reportResult("PASS", "I entered the email: " + email, true);
                return true;
            }
        } catch (Exception e) {
            Allure.addAttachment("The email has not been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "I cannot entered the element: " + email, true);
            Assert.fail("Could not entered the email:" + email);
            flag = false;
        }
        return flag;
    }

    @Then("I go to top of the site")
    public void topOfWebsite() {
        ((JavascriptExecutor) oDriver).executeScript("window.scrollTo(document.body.scrollHeight, 0)");
    }

    @Then("^I enter unique text to (.*) at index (\\d+)")
    public boolean uniqueText(String element, int index) throws InterruptedException {
        //mouseHover(element);
        WebElement object;
        object = commonLib.waitElement(element, timeout, index);
        String text = "automation" + uuid;
        boolean flag = false;
        try {
            if (object != null) {
                object.sendKeys(text);
                System.out.println("The text has been entered.");
                Allure.addAttachment("The text has been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                //  reportResult("PASS", "I entered the text: " + text, true);
                return true;
            }
        } catch (Exception e) {
            Allure.addAttachment("The text has not been entered.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            // reportResult("FAIL", "I cannot entered the element: " + text, true);
            Assert.fail("Could not entered the text:" + text);
            flag = false;
        }
        return flag;
    }

    @Then("^I verify the area (.*) by read only at index (\\d+)")
    public boolean readOnlyAreaCheck(String element, int index) throws InterruptedException {
        WebElement object;
        object = commonLib.waitElement(element, timeout, index);
        boolean flag = false;

        try {
            if (object != null) {
                if (!object.isEnabled()) {
                    System.out.println("The area is a read only area. Cannot be editable.");
                    Allure.addAttachment("The area is a read only area. Cannot be editable.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                    // reportResult("PASS", "The area is a read only area. Cannot be editable.", true);
                    return true;
                }
            }
        } catch (Exception e) {
            Allure.addAttachment("The area is not a read only area. Can be editable.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "The area is not a read only area. Can be editable.", true);
            Assert.fail("The area is not a read only area. Can be editable.");
            flag = false;
        }
        return flag;
    }

    @Then("^I clear text to (.*) at index (\\d+)")
    public boolean clearText(String element, int index) throws InterruptedException {
        WebElement object;
        object = commonLib.waitElement(element, timeout, index);
        boolean flag = false;
        try {
            if (object != null) {
                object.click();
                Thread.sleep(1000);
                object.sendKeys(Keys.CONTROL, "a");
                object.sendKeys(Keys.DELETE);
                Thread.sleep(1000);
                System.out.println("The text has been deleted.");
                Allure.addAttachment("The text has been deleted.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                //  reportResult("PASS", "The text has been deleted.", true);
                return true;
            }
        } catch (Exception e) {
            System.out.println("The text has not been deleted.");
            Allure.addAttachment("The text has not been deleted.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "The text has not been deleted", true);
            Assert.fail("Waiting element is not found!");
            flag = false;
        }
        return flag;
    }

    @And("^I wait (.*) element (\\d+) seconds at index (\\d+)")
    public void waitElement(String element, int timeout, int index) throws InterruptedException {
        commonLib.waitElement(element, timeout, index);
    }

    @When("User select element: {string} under {string} at index {int}")
    public boolean selectElement(String text, String element, int index) {
        WebElement object = commonLib.findElement(element, index);
        boolean flag = false;
        try {
            if (object != null) {
                object.click();
                System.out.println("Select the object type-->" + text + element);
                Select select = new Select(object);
                select.selectByVisibleText(text);
                Allure.addAttachment("The selection is done.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                return true;
            }
        } catch (Exception e) {
            System.out.println("The selection cannot be done.");
            Allure.addAttachment("The selection cannot be done.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "The selection cannot be done.", true);
            Assert.fail("The selection cannot be done!");
            flag = false;
        }
        return flag;
    }

    @And("^I need to just wait")
    public void justWait() throws InterruptedException {
        Thread.sleep(25000);
    }

    @Given("I wait for {int} seconds")
    public void sleep(int sec) throws InterruptedException {
        commonLib.sleep(sec);
    }

    @Then("^(?:I )?get the information by copying the value from: (\\w+(?: \\w+)*) at index (\\d+)")
    public boolean copyElement(String element, int index) throws InterruptedException {
        WebElement object;
        object = commonLib.waitElement(element, timeout, index);
        boolean flag = false;
        try {
            if (object != null) {
                object.click();
                Thread.sleep(1000);
                object.sendKeys(Keys.CONTROL, "c");
                Thread.sleep(1000);
                System.out.println("The text has been copied.");
                Allure.addAttachment("The text has been copied.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                //reportResult("PASS", "The text has been copied.", true);
                return true;
            }
        } catch (Exception e) {
            System.out.println("The copy action cannot be done.");
            Allure.addAttachment("The copy action cannot be done.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "The copy action cannot be done.", true);
            Assert.fail("The copy action cannot be done!");
            flag = false;

        }
        return flag;
    }

    @Then("^(?:I )?copy the information by copying the value to: (\\w+(?: \\w+)*) at index (\\d+)")
    public boolean pasteElement(String element, int index) throws InterruptedException {
        WebElement object;
        object = commonLib.waitElement(element, timeout, index);
        boolean flag = false;
        try {
            if (object != null) {
                object.click();
                Thread.sleep(1000);
                object.sendKeys(Keys.CONTROL, "v");
                Thread.sleep(1000);
                System.out.println("The text has been pasted.");
                Allure.addAttachment("The text has been pasted.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                // reportResult("PASS", "The text has been pasted.", true);
                return true;
            }
        } catch (Exception e) {
            System.out.println("The paste action cannot be done.");
            Allure.addAttachment("The paste action cannot be done.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            //reportResult("FAIL", "The paste action cannot be done.", true);
            Assert.fail("The paste action cannot be done!");
            flag = false;

        }
        return flag;
    }

    @When("^(?:I )?double click element: (\\w+(?: \\w+)*) at index (\\d+)")
    public void doubleClickElement(String element, int index) {
        WebElement object = commonLib.findElement(element, index);
        commonLib.doubleClickElement(object);
    }


    @When("My website is close")
    public void checkURLControl(String URL) throws Exception {
        //eğer URL kapalı ise git URL'i ayağa kaldır.
        if (checkLoginControl = false) {
            openUrl(URL);
            checkLoginControl = true;
        }
        //eğer URL açık ise (checkLoginControl = true)  hata bas.
        else {
            throw new InterruptedException("Your page is already opened. You cannot open the URL one more time.");
        }
    }


    @When("I click element: {string} if it exists at index {int}")
    public void clickIfExists(String element, int index) {
        boolean flag = false;
        try {
            WebElement object = commonLib.findElement(element, index, false);
            if (object != null && object.isDisplayed()) {
                System.out.println(element + " exists.");
                object.click();
                Allure.addAttachment("Element is clicked.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                //reportResult("PASS", "I clicked the element: " + element, true);
                flag = true;
            } else {
                System.out.println(element + " does not exist.");
                Allure.addAttachment("Element does not exist.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
                Allure.step("The element does not exist", Status.SKIPPED);
                flag = false;
            }
        } catch (Exception e) {
            Allure.addAttachment("Element does not exist.", new ByteArrayInputStream(((TakesScreenshot) oDriver).getScreenshotAs(OutputType.BYTES)));
            Allure.step("The element does not exist", Status.SKIPPED);
        }
    }



    @When("I scroll {string} element index {string}")
    public void scrollDownToElement(String searchedElement, int index) {
        WebElement object = commonLib.findElement(searchedElement, index);
        boolean flag = false;
        try {
            if (object != null) {
                String xmlFileName = "strings.xml";
                stringsis = this.getClass().getClassLoader().getResourceAsStream(xmlFileName);
                utils = new TestUtils();
                strings = utils.parseStringXML(stringsis);

                //object.click();
                String actualErrTxt = object.getText();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I scroll {string} element")
    public void scrollToElement(String searchedElement) {

        try {
            WebElement element = commonLib.findElement(searchedElement,1);
//            ((JavascriptExecutor) oDriver).executeScript("arguments[0].scrollIntoView(true);", element);
//            Thread.sleep(500);

            Actions actions = new Actions(oDriver);
            actions.moveToElement(element);
            actions.perform();
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I scroll down to element")
    public void scrollDown() throws InterruptedException {

        JavascriptExecutor jsx = (JavascriptExecutor) oDriver;
        Thread.sleep(5000);
        jsx.executeScript("window.scrollBy(0,3000)");
        Thread.sleep(5000);
        System.out.println("Scroll success");

    }



    //-mobilden gelenler

    @Given("I wait {string} element")
    public void waitElement(String element) {
        commonLib.waitElement(element);
    }


    @Given("I wait {string} element and click")
    public void waitAndClick(String elem) throws UnsupportedEncodingException, InterruptedException {

        WebElement element = commonLib.findElement(elem);

        if (elem != null) {

            MobileElement mobileElement = null;
            int timeout = 0;
            while (mobileElement == null && timeout < 15) {
                try {
                    commonLib.findElement(elem);
                    sleep(1);
                } catch (Exception e) {

                }

                timeout++;
            }
            if (mobileElement != null) {
                commonLib.allureReport(StepResultType.PASS, "Element  displayed", true);
                mobileElement.click();
                commonLib.allureReport(StepResultType.PASS, "Element  clicked", true);
            } else {
                commonLib.allureReport(StepResultType.FAIL, "Element not displayed", true);
            }
        }
    }





    @Then("I force click element: {string} at index {int}")
    public void iForceClickElementAtIndex(String element, int index) {
        Actions actions = new Actions(oDriver);
        //actions.click(commonLib.findElement(element,index)).build().perform();
        actions.moveToElement(commonLib.findElement(element, index)).click().build().perform();
        System.out.println("click element " + element);
    }

    @And("wait until element {string} is {string}")
    public void waitUntilElementIs(String element, String waitFor) {
        if (waitFor.equals("clickable")) {
            CommonLib.waitElementClickable(oDriver, commonLib.findElement(element, 1));
        }

        if (waitFor.equals("visible")) {
            CommonLib.waitElementVisible(oDriver, commonLib.findElement(element, 1));
        }
    }


    @Then("I save variable {string} of element value as {string}")
    public void setVariables(String element, String key) throws UnsupportedEncodingException {

        WebElement elem = commonLib.findElement(element, 1);
        commonLib.setVariables(key, elem.getText());
        System.out.println(elem.getText());

    }

    @Then("If any element text is equal to {string} key click to element")
    public void getVariables(String key) throws UnsupportedEncodingException {

        commonLib.clickElement((WebElement) By.xpath("//*[contains(@text,'" + commonLib.getVariables(key) + "')]"));

    }

    @Then("slider")
    public void slider(String key) throws UnsupportedEncodingException {

        oDriver.switchTo().frame(0); //need to switch to this frame before clicking the slider
        WebElement slider = oDriver.findElement(By.xpath("//div[@id='slider']/span"));
        Actions move = new Actions(oDriver);
        Action action = (Action) move.dragAndDropBy(slider, 30, 0).build();
        action.perform();

    }

    @Then("I mouse hover to {string} element and click to {string} element")
    public void mouseHover(String mouseHover, String clickToMouseHover) {

        WebElement elementToHover = commonLib.findElement((mouseHover));

        WebElement elementToClick = commonLib.findElement((clickToMouseHover));

        Actions actions = new Actions(oDriver);
        //actions.moveToElement(elementToHover).click(elementToClick).build().perform();
        actions.moveToElement(elementToHover).perform();
    }




    //Mernis am a commit atacağım method
    @Then("I highlight to {string} element")
    public void highlightToElement(String element) {

        JavascriptExecutor jse = (JavascriptExecutor) oDriver;

        WebElement elem = commonLib.findElement(element,1);

        jse.executeScript("arguments[0].setAttribute('style','background: yellow; border: 2px solid red;');", elem);

    }

}


