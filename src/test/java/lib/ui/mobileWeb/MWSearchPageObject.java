package lib.ui.mobileWeb;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static {
        SEARCH_INIT_ELEMENT = "css:button.cdx-button#searchIcon";
        SEARCH_INPUT = "css:form>input.search";
        SEARCH_CANCEL_BUTTON = "css:div.header-action>button";
        SEARCH_FIELD = "css:form.search-box>input.search";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://a/div[contains(text(), '{SUBSTRING}')]";
        SEARCH_RESULT_LOCATOR = "css:ul.page-list>li.page-summary";
        NO_RESULTS_MESSAGE = "css:p.without-results";
    }
    public MWSearchPageObject(RemoteWebDriver driver) {super(driver);}
}
