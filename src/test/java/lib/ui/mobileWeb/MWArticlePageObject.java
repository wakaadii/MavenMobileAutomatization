package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1>span";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "css:#page-actions-watch";
        ADD_TO_LIST_BUTTON = "css:a#ca-watch";
        OPTIONS_REMOVE_FROM_LIST_BUTTON = "xpath://span[contains(text(), 'Unwatch')]";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {super(driver);}
}
