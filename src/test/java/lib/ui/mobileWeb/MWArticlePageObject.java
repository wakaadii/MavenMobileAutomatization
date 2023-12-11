package lib.ui.mobileWeb;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {

    static {
        TITLE = "css:#content h1>span";
        FOOTER_ELEMENT = "css:footer";
        SAVE_BUTTON = "css:span.minerva-icon--star-base20";
        ADD_TO_LIST_BUTTON = "css:a#ca-watch";
        OPTIONS_REMOVE_FROM_LIST_BUTTON = "css:span.minerva-icon--unStar-progressive";
    }
    public MWArticlePageObject(RemoteWebDriver driver) {super(driver);}
}
