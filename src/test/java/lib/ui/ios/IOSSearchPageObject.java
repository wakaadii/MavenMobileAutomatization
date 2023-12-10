package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSSearchPageObject extends SearchPageObject {

    static {
        SEARCH_INIT_ELEMENT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
//        SEARCH_INPUT = "xpath://XCUIElementTypeSearchField[@name='Search Wikipedia']";
        SEARCH_INPUT = "id:Search Wikipedia";
//        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']" или //XCUIElementTypeButton[@name="Clear text"];
        SEARCH_CANCEL_BUTTON = "xpath://XCUIElementTypeStaticText[@name='Cancel']";
//        SEARCH_FIELD = "id:org.wikipedia:id/search_src_text";
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://XCUIElementTypeStaticText[contains(@name, '{SUBSTRING}')]";
        SEARCH_RESULT_LOCATOR = "xpath://XCUIElementTypeCell";
//        SEARCH_RESULTS_LIST_ID = "xpath://*[@resource-id = 'org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']";
        NO_RESULTS_MESSAGE = "xpath://XCUIElementTypeStaticText[@name='No results found']";
    }
    public IOSSearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }


}
