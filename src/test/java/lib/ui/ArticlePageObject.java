package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

abstract public class ArticlePageObject extends MainPageObject{

    protected static String
        TITLE,
        TITLE_TPL,
        FOOTER_ELEMENT,
        SAVE_BUTTON,
        ADD_TO_LIST_BUTTON,
        OPTIONS_REMOVE_FROM_LIST_BUTTON,
        NAME_OF_BOOKMARKS_LIST,
        CLOSE_BOOKMARKS_POPUP_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        SAVED_LISTS_OF_BOOKMARKS,
        CLOSE_SYNC_POPUP,
        NAME_OF_BOOKMARKS_LIST_TPL;


    public ArticlePageObject(RemoteWebDriver driver) { super(driver); }

    @Step("find title element")
    public WebElement waitForTitleElement() {
            return this.waitForElementPresents(TITLE, "Can't find article title");
    }

   @Step("find title element with name '{titleName}'")
    public WebElement waitForTitleElement(String titleName) {
        return this.waitForElementPresents(getNameOfTitle(titleName), "Can't find article title");
    }

    @Step("get title of article")
    public String getArticleTitle () {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("contentDescription");
        } else if (Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    @Step("get title of article with title name input '{titleName}'")
    public String getArticleTitle (String titleName){
        WebElement title_element = waitForTitleElement(titleName);
        return title_element.getAttribute("name");
    }

    @Step("swipe to footer")
    public void swipeToFooter() {
        if (Platform.getInstance().isAndroid()) {
            this.swipeUpToElement(FOOTER_ELEMENT, "can't find the end of article", 30);
        } else if (Platform.getInstance().isIOS()) {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "can't find the end of article", 40);
        } else {
            this.swipeUpWebPageTillElementVisible(
                    FOOTER_ELEMENT,
                    "can't find the end of article",
                    40
            );
        }
    }

    @Step("add article to non-default bookmark's list with name '{nameOfFolder}'")
    public void addArticleToNewList(String nameOfFolder) {

        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Can't find 'save' button"
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "can't add to bookmarks",
                10
        );

        this.waitForElementAndClick(
                NAME_OF_BOOKMARKS_LIST,
                "can't find list's add");

        this.waitForElementAndSend(
                NAME_OF_BOOKMARKS_LIST,
                nameOfFolder,
                "can't send name for list of bookmarks"
        );

        this.waitForElementAndClick(
                CLOSE_BOOKMARKS_POPUP_BUTTON,
                "can't create list of bookmarks"
        );
    }

    @Step("save article to default bookmark's list")
    public void saveArticleToDefaultList() {
        if (Platform.getInstance().isMW()) {
            this.removeSavedArticleBeforeAdding();
        }
        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Can't find 'save' button"
        );
    }

    @Step("check and remove (if checked) article from bookmarks")
    public void removeSavedArticleBeforeAdding(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_LIST_BUTTON)) {
            this.waitForElementAndClick(
                    OPTIONS_REMOVE_FROM_LIST_BUTTON,
                    "Can't remove article from reading list",
                    5
            );
            this.waitForElementPresents(
                    ADD_TO_LIST_BUTTON,
                    "Can't find save to reading list button after removing");
        }
    }

    @Step("change title by template with text '{titleName}'")
    private static String getNameOfTitle (String titleName) {
        return TITLE_TPL.replace("{TEXT}", titleName);
    }
    @Step("change bookmark's list name by template with text '{folderName}'")
    private static String getNameOfBookmarksListXpathByName (String folderName) {
        return NAME_OF_BOOKMARKS_LIST_TPL.replace("{TEXT}", folderName);
    }

    @Step("add article to list '{folderName}'")
    public void addArticleToList(String folderName) {
        String folderXpath = getNameOfBookmarksListXpathByName(folderName);

        this.waitForElementAndClick(
                SAVE_BUTTON,
                "Can't find 'save' button"
        );

        this.waitForElementAndClick(
                ADD_TO_LIST_BUTTON,
                "can't add to bookmarks",
                10
        );

        this.waitForElementAndClick(
                folderXpath,
                "can't find list's add");

    }

    @Step("return to previous page")
    public void closePage() {
            if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
                this.waitForElementAndClick(
                        CLOSE_ARTICLE_BUTTON,
                        "can't find <- arrow on page"
                );
            } else {
                System.out.println("method closePage() does nothing for platform " + Platform.getInstance().getPlatformVar());
            }
    }

    @Step("open list of bookmarks")
    public void openSavedLists () {
        this.waitForElementAndClick(
                SAVED_LISTS_OF_BOOKMARKS,
                "Can't find saved bookmarks button"
        );
    }

    @Step("verify that element is on screen")
    public void assertElementPresent() {
        Assert.assertTrue(driver.findElement(this.getLocatorByString(TITLE)).isDisplayed());
    }

}
