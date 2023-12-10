package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSavedListsPageObject extends SavedListsPageObject {

    static {
        CLOSE_SYNC_POPUP = "xpath://XCUIElementTypeButton[@name='Close']";

        BOOKMARK_TO_DELETE_TPL = "xpath://XCUIElementTypeStaticText[@value='{TEXT}']";
//        BOOKMARK_TO_DELETE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TEXT}']";
        BUTTON_TO_DELETE = "xpath://XCUIElementTypeButton[@name='swipe action delete']";
    }


    public IOSSavedListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
