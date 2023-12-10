package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.WelcomeScreenPageObject;
import lib.ui.android.AndroidWelcomeScreenPageObject;
import lib.ui.ios.IOSWelcomeScreenPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomeScreenPageObjectFactory {
    public static WelcomeScreenPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidWelcomeScreenPageObject(driver);
        } else {
            return new IOSWelcomeScreenPageObject(driver);
        }
    }
}
