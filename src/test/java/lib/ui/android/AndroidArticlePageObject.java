package lib.ui.android;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "xpath://*[@resource-id = 'pcs-edit-section-title-description']";
        FOOTER_ELEMENT = "xpath://android.view.View[@content-desc='View article in browser']";
        SAVE_BUTTON = "id:org.wikipedia:id/page_save";
        ADD_TO_LIST_BUTTON = "id:org.wikipedia:id/snackbar_action";
        NAME_OF_BOOKMARKS_LIST = "xpath://android.widget.EditText[@resource-id = 'org.wikipedia:id/text_input' and @text = 'Name of this list']";
        CLOSE_BOOKMARKS_POPUP_BUTTON = "xpath://*[@text = 'OK']";
        CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";
        SAVED_LISTS_OF_BOOKMARKS = "id:org.wikipedia:id/nav_tab_reading_lists";
        NAME_OF_BOOKMARKS_LIST_TPL = "xpath://android.widget.TextView[@text = '{TEXT}']";
    }
    public AndroidArticlePageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
