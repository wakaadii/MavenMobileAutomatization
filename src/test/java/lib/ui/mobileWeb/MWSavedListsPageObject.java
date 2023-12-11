package lib.ui.mobileWeb;

import lib.ui.SavedListsPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSavedListsPageObject extends SavedListsPageObject {

    static {
        BOOKMARK_TO_DELETE_TPL = "xpath://li/a/h3[contains(text(), '{TEXT}')]";
        REMOVE_FROM_SAVED_BUTTON = "xpath://li[@title='{TEXT}']/a[contains(@class, 'watched')]";
    }

    public MWSavedListsPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
