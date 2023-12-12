package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Attachment;
import lib.Platform;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import static java.time.Duration.ofMillis;

public class MainPageObject {
    protected RemoteWebDriver driver;

    public MainPageObject (RemoteWebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForElementPresents(String locator, String errorMessage, long timeOutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    public WebElement waitForElementPresents(String locator, String errorMessage) {
        return waitForElementPresents(locator, errorMessage,5);
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(locator, errorMessage, timeOutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementAndClick(String locator, String errorMessage){
        WebElement element = waitForElementPresents(locator, errorMessage, 5);
        element.click();
        return element;
    }

    public void tryClickElementWithFewAttempts(String locator, String errorMessage, int amountOfAttempts) {
        int currentAttempt = 0;
        boolean needMoreAttempts = true;

        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        while (needMoreAttempts) {
            try {
                this.waitForElementAndClick(locator, errorMessage,1 );
                needMoreAttempts = false;
            } catch (Exception e) {
                if (currentAttempt > amountOfAttempts) {
                    this.waitForElementAndClick(locator, errorMessage);
                }
            }
            ++currentAttempt;
        }
    }

    private WebElement waitForElementAndSend(String locator, String message, String errorMessage, long timeOutInSeconds){
        WebElement element = waitForElementPresents(locator, errorMessage, timeOutInSeconds);
        element.sendKeys(message);
        return element;
    }

    public WebElement waitForElementAndSend(String locator, String message, String errorMessage){
        WebElement element = waitForElementPresents(locator, errorMessage, 5);
        element.sendKeys(message);
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String errorMessage, long timeOutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
        By by = this.getLocatorByString(locator);
        wait.withMessage(errorMessage + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public WebElement waitForElementAndClear(String locator, String errorMessage, long timeOutInSeconds) {
        WebElement element = waitForElementPresents(locator, errorMessage, 15);
        element.clear();
        return element;
    }

//    public void swipeUp(int timeOfSwipe) {
//        TouchAction action = new TouchAction(driver);
//        Dimension size = driver.manage().window().getSize();
//        int x = size.width / 2;
//        int start_y = (int) (size.height * 0.8);
//        int end_y = (int) (size.height * 0.2);
//        System.out.println(x + " " + start_y + " " + " " +end_y);
//        action
//                .press(PointOption.point(x, start_y))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
//                .moveTo(PointOption.point(x, end_y))
//                .release()
//                .perform();
//    }

    public void swipeUp(int timeInMills){
        if (driver instanceof AppiumDriver) {
            Dimension size = driver.manage().window().getSize();
            int startY = (int) (size.height * 0.70);
            int endY = (int) (size.height * 0.30);
            int centerX = size.width / 2;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
            Sequence swipe = new Sequence(finger,1);
            //Двигаем палец на начальную позицию
            swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                    PointerInput.Origin.viewport(),centerX,startY));
            //Палец прикасается к экрану
            swipe.addAction(finger.createPointerDown(0));
            //Палец двигается к конечной точке
            swipe.addAction(finger.createPointerMove(ofMillis(timeInMills),
                    PointerInput.Origin.viewport(),centerX,endY));
            //Убираем палец с экрана
            swipe.addAction(finger.createPointerUp(0));
            //Выполняем действия
            driver.perform(Arrays.asList(swipe));
        } else {
            System.out.println("method swipeUp() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }

    }

    protected void swipeUpQuick () {
        swipeUp(200);
    }

    public void swipeUpToElement(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;

        while (driver.findElements(getLocatorByString(locator)).size() == 0){
            if (alreadySwiped > maxSwipes){
                waitForElementPresents(locator, "Can't find element by swiping \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
        swipeUpQuick();

    }

    public void swipeUpTillElementAppear(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (!isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes){
                Assert.assertTrue(errorMessage, this.isElementLocatedOnTheScreen(locator));
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
        swipeUpQuick();
    }

    public void swipeWebPageUp(){
        if (Platform.getInstance().isMW()) {
            JavascriptExecutor JSExecutor = (JavascriptExecutor) driver;
            JSExecutor.executeScript("window.scrollBy(0, 250)");
        } else {
            System.out.println("method swipeUpWebPage() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void swipeUpWebPageTillElementVisible(String locator, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        WebElement element = this.waitForElementPresents(locator, errorMessage);
        while (!isElementLocatedOnTheScreen(locator)) {
            if (alreadySwiped > maxSwipes){
                Assert.assertTrue(errorMessage, element.isDisplayed());
            }
            swipeWebPageUp();
            ++alreadySwiped;
            System.out.println(alreadySwiped + " swipeUpWebPageTillElementVisible");
        }
    }

    public boolean isElementLocatedOnTheScreen(String locator) {
        int elementLocationByY = this.waitForElementPresents(locator, "can't find element by locator " + locator).getLocation().getY();
        if (Platform.getInstance().isMW()){
            JavascriptExecutor JSExecutor = (JavascriptExecutor)driver;
            Object js_result= JSExecutor.executeScript("return window.pageYOffset");
            elementLocationByY -= Integer.parseInt(js_result.toString());
        }
        int screenSizeByY = driver.manage().window().getSize().getHeight();
        return elementLocationByY < screenSizeByY;
    }

    public void swipeElementToLeft(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {
            WebElement element = waitForElementPresents(
                    locator,
                    errorMessage,
                    10);
            int leftX = element.getLocation().getX();
            int rightX = leftX + element.getSize().getWidth();
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();
            int middleY = (upperY + lowerY) / 2;

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);
            //Двигаем палец на начальную позицию
            swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),
                            PointerInput.Origin.viewport(), rightX - 10, (int) middleY))
                    //Палец прикасается к экрану
                    .addAction(finger.createPointerDown(0))
                    //Палец двигается к конечной точке
                    .addAction(finger.createPointerMove(Duration.ofMillis(700),
                            PointerInput.Origin.viewport(), leftX + 10, (int) middleY))
                    //Убираем палец с экрана
                    .addAction(finger.createPointerUp(0));
            driver.perform(Arrays.asList(swipe));
        } else {
            System.out.println("method swipeElementToLeft() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickElementToTheRightUpCorner(String locator, String errorMessage) {
        if (driver instanceof AppiumDriver) {

            WebElement element = this.waitForElementPresents(locator, errorMessage);
            int upperY = element.getLocation().getY();
            int lowerY = upperY + element.getSize().getHeight();

            int pointToClickY = (upperY + lowerY) / 2;
            int pointToClickX = element.getLocation().getX() + element.getSize().getWidth() - 3;

            TouchAction action = new TouchAction((AppiumDriver) driver);
            action.tap(PointOption.point(pointToClickX, pointToClickY)).perform();
        } else {
            System.out.println("method clickElementToTheRightUpCorner() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

//    protected void swipeElementToLeft(String locator, String error_message) {
//        RemoteWebElement carousel = (RemoteWebElement) waitForElementPresents(
//                locator,
//                error_message,
//                10);
//        driver.executeScript("gesture: swipe", Map.of("elementId", carousel.getId(), "percentage", 50, "direction", "left"));
//    }


    public boolean isElementPresent(String locator) {
            return getAmountOfElements(locator) > 0;
    }


    public int getAmountOfElements(String locator)    {
        List elementsCount = driver.findElements(getLocatorByString(locator));
        return elementsCount.size();
    }

    private void assertElementNotPresent (String locator, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        if (amountOfElements > 0) {
            String defaultMessage = "An element '" + locator.toString() + "supposed to not presented";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public void assertNoSearchResults(String locator, By byGetText, String errorMessage) {
        int amountOfElements = getAmountOfElements(locator);
        String textOfElement = driver.findElement(byGetText).getText();
        if (!textOfElement.equals("No results") & (amountOfElements == 1)){
            String defaultMessage = "An element '" + locator + "supposed to not presented";
            throw new AssertionError(defaultMessage + " " + errorMessage);
        }
    }

    public String waitForElementAndGetAttribute(String locator, String attribute, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresents(locator, errorMessage, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    public void assertElementHasText(String locator, String text, String errorMessage){
        WebElement element = waitForElementPresents(locator, errorMessage, 15);
        Assert.assertEquals(
                errorMessage,
                text,
                element.getText()
        );
    }

    public void assertElementContainText(String locator, String text, String errorMessage) {
        WebElement element = waitForElementPresents(
                locator,
                errorMessage,
                10
        );
        Assert.assertTrue(element.getText().contains(text));
    }

    By getLocatorByString(String locatorWithType) {
        String [] exploitedLocator = locatorWithType.split(Pattern.quote(":"), 2);
        String byType = exploitedLocator[0];
        String locator = exploitedLocator[1];

        if (byType.equals("xpath")) {
            return By.xpath(locator);
        } else if (byType.equals("id")) {
            return By.id(locator);
        }else if (byType.equals("class")) {
            return By.className(locator);
        } else if (byType.equals("css")){
            return By.cssSelector(locator);
        } else {
            throw new IllegalArgumentException("Can't get type of locator. Locator - " + locatorWithType);
        }
    }

    public String takeScreenshot(String name) {
        TakesScreenshot ts = (TakesScreenshot)this.driver;
        LocalDateTime date = LocalDateTime.now();
        File source = ts.getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshots/" + name + date + "_screenshot.png";
        try {
            FileUtils.copyFile(source, new File(path));
            System.out.println("The screenshot was taken: " + path);
        } catch (Exception e) {
            System.out.println("Can't take screenshot. Error: " + e.getMessage());
        }
        return path;
    }

    @Attachment
    public static byte[] screenshot(String path ) {
        byte[] bytes = new byte[0];

        try {
            bytes = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            System.out.println("Can't get bytes from screenshot. Error: " + e.getMessage());
        }
        return bytes;
    }
}