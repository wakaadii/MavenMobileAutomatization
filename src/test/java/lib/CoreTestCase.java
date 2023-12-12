package lib;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import junit.framework.TestCase;
import lib.ui.WelcomeScreenPageObject;
import lib.ui.factory.WelcomeScreenPageObjectFactory;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class CoreTestCase {


    protected RemoteWebDriver driver;

    @Before
    @Step("Run driver and session")
    public void setUp() throws Exception{
        driver = Platform.getInstance().getDriver();
        this.createAllureProperty();
        this.rotateScreenPortrait();
        this.skipWelcomeScreen();
        this.openWikiWebPageForMobile();
    }

    @After
    @Step("Remove driver and session")
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Step("rotate app screen to portrait mode")
    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("method rotateScreenLandscape() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("rotate app screen to landscape mode")
    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("move app to background")
    protected void moveToBackground(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("method moveToBackground() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Open web page for browser")
    protected void openWikiWebPageForMobile() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org/");
        } else {
            System.out.println("method openWikiWebPageForMobile() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    @Step("Skip welcome screen for app")
    private void skipWelcomeScreen () {
        if (driver instanceof AppiumDriver) {
            WelcomeScreenPageObject WelcomeScreenPageObject = WelcomeScreenPageObjectFactory.get(driver);
            WelcomeScreenPageObject.clickSkip();
        } else {
            System.out.println("method skipWelcomeScreen () does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void createAllureProperty() {
        String path = System.getProperty("allure.results.directory");
        System.out.println(path);
        try{
            Properties props = new Properties();
            FileOutputStream fos = new FileOutputStream(path + "/environment.properties");
            props.setProperty("Environment", Platform.getInstance().getPlatformVar());
            props.store(fos, "See https://allurereport.org/docs/how-it-works-environment-file/");
            fos.close();
        } catch (Exception e) {
            System.err.println("IO problem when writing allure environment properties file");
            e.printStackTrace();
        }

    }


}
