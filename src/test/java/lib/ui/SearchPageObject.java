package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT,
            SEARCH_CANCEL_BUTTON,
            SEARCH_FIELD,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_LOCATOR,
            SEARCH_RESULTS_LIST_ID,
            NO_RESULTS_MESSAGE;

    public SearchPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    /* template methods*/
    private static String getResultSearchElement(String substring){
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    private static String getSearchResultLocator(String substring){
        return SEARCH_RESULT_LOCATOR.replace("{SUBSTRING}", substring);
    }
    /* template methods*/



    public void initSearchInput() {
        this.waitForElementPresents(SEARCH_INIT_ELEMENT, "can't find search bar and click");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "search bar is not founded");
    }

    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSend(SEARCH_INPUT, searchLine, "Can't find and type Duration search input");
    }

    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        System.out.println(searchResultXpath + " waitForSearchResult(String substring)");
        this.waitForElementPresents(searchResultXpath, "There is no text '" + substring + "' in server answer");
    }

    public void waitForCancelButtonToAppear(){
        this.waitForElementPresents(SEARCH_CANCEL_BUTTON, "Can't find 'cancel search' cross");
    }

    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Button 'cancel search' is presented", 10);
    }

    public void clearSearchField () {
        this.waitForElementAndClear(SEARCH_FIELD, "field for clear does not present", 10);
    }

    public void clickCancelSearch () {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can't click on 'cancel search' button");
    }

    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "can't click result with substring text'" + substring + "'", 15);
    }

    public int countNumberOfLines() {
        String countElementsLocator = SEARCH_RESULT_LOCATOR;
        if (Platform.getInstance().isAndroid()) {
            countElementsLocator = getSearchResultLocator("android.view.ViewGroup");
        }
        this.waitForElementPresents(
                countElementsLocator,
                "incorrect search locator " + countElementsLocator
        );
        return getAmountOfElements(countElementsLocator);
    }

    public String getTextOfLine(int index) {
        if (Platform.getInstance().isAndroid()) {
            String textElement = getSearchResultLocator("android.view.ViewGroup[@index = " + index + "]/android.widget.TextView");
            return driver.findElement(getLocatorByString(textElement)).getText();
        } else {
            String textElement = getSearchResultLocator("[" + (index + 1) + "]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]");
            System.out.println(textElement);
            System.out.println(driver.findElement(getLocatorByString(textElement)).getAttribute("value") + " getTextOfLine");
            return driver.findElement(getLocatorByString(textElement)).getText();
        }
    }

    public void searchResultIsNotEmpty() {
        Assert.assertTrue("too few results", ((countNumberOfLines() > 1) || !(getTextOfLine(0).equals("No results"))));
    }

    public void noSearchResult() {
        if (Platform.getInstance().isAndroid()) {
            Assert.assertTrue("Some results are founded", ((countNumberOfLines() == 1) & getTextOfLine(0).equals("No results")));
        } else if (Platform.getInstance().isIOS()){
            Assert.assertTrue("Some results are founded",
                                (waitForElementPresents(NO_RESULTS_MESSAGE, "can't find no results message")
                                .getAttribute("value")
                                .equals("No results found")));
        } else {
            Assert.assertTrue("Some results are founded",
                    (waitForElementPresents(NO_RESULTS_MESSAGE, "can't find no results message")
                            .getAttribute("text")
                            .equals("No page with this title.")));
        }
    }

    public void waitForElementByTitleAndDescription(String title, String description){

    }

    public void searchResultIsEmpty() {
        waitForElementNotPresent(SEARCH_RESULTS_LIST_ID, "Element is presented", 15);
    }


}
