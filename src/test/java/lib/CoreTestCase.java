package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomeScreenPageObject;
import lib.ui.factory.WelcomeScreenPageObjectFactory;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.time.Duration;

public class CoreTestCase extends TestCase {


    protected RemoteWebDriver driver;

    @Override
    protected void setUp() throws Exception{
        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomeScreen();
        this.openWikiWebPageForMobile();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenLandscape() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.LANDSCAPE);
        } else {
            System.out.println("method rotateScreenLandscape() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void rotateScreenPortrait() {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.rotate(ScreenOrientation.PORTRAIT);
        } else {
            System.out.println("method rotateScreenPortrait() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void moveToBackground(int seconds) {
        if (driver instanceof AppiumDriver) {
            AppiumDriver driver = (AppiumDriver) this.driver;
            driver.runAppInBackground(Duration.ofSeconds(seconds));
        } else {
            System.out.println("method moveToBackground() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    protected void openWikiWebPageForMobile() {
        if (Platform.getInstance().isMW()) {
            driver.get("https://en.m.wikipedia.org/");
        } else {
            System.out.println("method openWikiWebPageForMobile() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    private void skipWelcomeScreen () {
        if (driver instanceof AppiumDriver) {
            WelcomeScreenPageObject WelcomeScreenPageObject = WelcomeScreenPageObjectFactory.get(driver);
            WelcomeScreenPageObject.clickSkip();
        } else {
            System.out.println("method skipWelcomeScreen () does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }


}
