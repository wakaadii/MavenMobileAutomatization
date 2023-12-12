package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
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



    @Step("Initialize search field")
    public void initSearchInput() {
        this.waitForElementPresents(SEARCH_INIT_ELEMENT, "can't find search bar and click");
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "search bar is not founded");
    }

    @Step("Send text '{searchLine}' to search line")
    public void typeSearchLine(String searchLine) {
        this.waitForElementAndSend(SEARCH_INPUT, searchLine, "Can't find and type Duration search input");
    }

    @Step("wait for text '{substring}' in search result")
    public void waitForSearchResult(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        System.out.println(searchResultXpath + " waitForSearchResult");
        this.waitForElementPresents(searchResultXpath, "There is no text '" + substring + "' in server answer");
    }

    @Step("Waiting for button to cancel search result")
    public void waitForCancelButtonToAppear(){
        this.waitForElementPresents(SEARCH_CANCEL_BUTTON, "Can't find 'cancel search' cross");
    }

    @Step("Waiting for search cancel button to do disappear")
    public void waitForCancelButtonToDisappear(){
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "Button 'cancel search' is presented", 10);
    }

    @Step("clear search field")
    public void clearSearchField () {
        this.waitForElementAndClear(SEARCH_FIELD, "field for clear does not present", 10);
    }

    @Step("click on on cancel search button")
    public void clickCancelSearch () {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Can't click on 'cancel search' button");
    }

    @Step("Open article with subtitle '{substring}'")
    public void clickByArticleWithSubstring(String substring) {
        String searchResultXpath = getResultSearchElement(substring);
        this.waitForElementAndClick(searchResultXpath, "can't click result with substring text'" + substring + "'", 15);
    }

    @Step("count number of results")
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

    @Step("get text of '{index}' article in search results")
    public String getTextOfLine(int index) {
        if (Platform.getInstance().isAndroid()) {
            String textElement = getSearchResultLocator("android.view.ViewGroup[@index = " + index + "]/android.widget.TextView");
            return driver.findElement(getLocatorByString(textElement)).getText();
        } else {
            String textElement = getSearchResultLocator("[" + (index + 1) + "]/XCUIElementTypeOther[2]/XCUIElementTypeStaticText[1]");
            System.out.println(textElement);
            System.out.println(driver.findElement(getLocatorByString(textElement)).getText() + " getTextOfLine " + index);
            return driver.findElement(getLocatorByString(textElement)).getText();
        }
    }

    @Step("verify that search results is not empty")
    public void searchResultIsNotEmpty() {
        Assert.assertTrue("too few results", ((countNumberOfLines() > 1) || !(getTextOfLine(0).equals("No results"))));
    }

    @Step("verify that search results are empty")
    public void noSearchResult() {
        if (Platform.getInstance().isAndroid()) {
            Assert.assertTrue("Some results are founded",
                                ((countNumberOfLines() == 1)
                                        & getTextOfLine(0)
                                          .equals("No results")));
        } else if (Platform.getInstance().isIOS()){
            Assert.assertTrue("Some results are founded",
                                (waitForElementPresents(NO_RESULTS_MESSAGE, "can't find no results message")
                                .getAttribute("value")
                                .equals("No results found")));
        } else {
            System.out.println(waitForElementPresents(NO_RESULTS_MESSAGE,"").getAttribute("style"));
            Assert.assertTrue("Some results are founded",
                    (waitForElementPresents(NO_RESULTS_MESSAGE, "can't find no results message")
                            .getAttribute("style")
                            .equals("display: none;")));
        }
    }

    @Step("Verify that search results are cleared")
    public void searchResultIsEmpty() {
        waitForElementNotPresent(SEARCH_RESULTS_LIST_ID, "Element is presented", 15);
    }


}
