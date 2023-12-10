package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.SavedListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidSavedListsPageObject extends SavedListsPageObject {

    static {
        NAME_OF_BOOKMARKS_LIST_TPL = "xpath://android.widget.TextView[@text = '{FOLDER_NAME}']";
        BOOKMARK_TO_DELETE_TPL = "xpath://android.widget.TextView[@text = '{TEXT}']";
    }

    public AndroidSavedListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
