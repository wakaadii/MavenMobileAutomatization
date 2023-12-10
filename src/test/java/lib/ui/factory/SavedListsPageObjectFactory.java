package lib.ui.factory;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.SavedListsPageObject;
import lib.ui.android.AndroidSavedListsPageObject;
import lib.ui.ios.IOSSavedListsPageObject;
import lib.ui.mobileWeb.MWSavedListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SavedListsPageObjectFactory {
    public static SavedListsPageObject get(RemoteWebDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidSavedListsPageObject(driver);
        } else if (Platform.getInstance().isIOS()) {
            return new IOSSavedListsPageObject(driver);
        } else {
            return new MWSavedListsPageObject(driver);
        }
    }


}
