package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import lib.Platform;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class SavedListsPageObject extends MainPageObject{

    protected static String
            NAME_OF_BOOKMARKS_LIST_TPL,
            BOOKMARK_TO_DELETE_TPL,
            CLOSE_SYNC_POPUP,
            BUTTON_TO_DELETE,
            REMOVE_FROM_SAVED_BUTTON;

    @Step("change template name of folder to '{folderName}'")
    private static String getFolderByName(String folderName) {
        return NAME_OF_BOOKMARKS_LIST_TPL.replace("{FOLDER_NAME}", folderName);
    }

    @Step("change template name of article to delete to '{bookmark name}'")
    private static String getBookmark(String bookmarkName) {
        return BOOKMARK_TO_DELETE_TPL.replace("{TEXT}", bookmarkName);
    }

    @Step("change remove locator name to '{bookmarkName}'")
    private static String getRemoveButtonByTitle(String bookmarkName) {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TEXT}", bookmarkName);
    }

    public SavedListsPageObject(RemoteWebDriver driver) { super(driver); }

    @Step("open list of bookmarks with name '{folderName}'")
    public void openListOfBookmarks(String folderName) {
        String xpath = getFolderByName(folderName);
        this.waitForElementAndClick(
                xpath,
                "can't find bookmarks list " + folderName
        );
    }

    @Step("find article with name '{nameOfBookmark}' in saved articles")
    public void waitForArticleToAppearByTitle(String nameOfBookmark) {
        String xpath = getBookmark(nameOfBookmark);
        this.waitForElementPresents(
                xpath,
                "Can't find saved Article by title " + nameOfBookmark,
                15
        );
    }

    @Step("find article with name '{nameOfBookmark}' in saved articles for web")
    public void waitForArticleToAppearByH3(String nameOfBookmark) {
        String xpath = getBookmark(nameOfBookmark);
        this.waitForElementPresents(
                xpath,
                "Can't find saved Article by title " + nameOfBookmark,
                15
        );
    }

    @Step("verify that article '{nameOfBookmark}' is delete")
    public void waitForArticleToDisappearByTitle(String nameOfBookmark) {
        String xpath = getBookmark(nameOfBookmark);
        this.waitForElementNotPresent(
                xpath,
                "marked page is in the list",
                15
        );
    }

    @Step("delete article '{nameOfBookmark}' from bookmarks")
    public void deleteBookmarkFromList(String nameOfBookmark) {
        if (Platform.getInstance().isAndroid()) {
            this.waitForArticleToAppearByTitle(nameOfBookmark);
            String xpath = getBookmark(nameOfBookmark);
            this.swipeElementToLeft(
                    xpath,
                    "can't find marked page"
            );
            waitForArticleToDisappearByTitle(nameOfBookmark);
        } else if (Platform.getInstance().isIOS()){
            waitForElementAndClick(CLOSE_SYNC_POPUP, "Can't close sync popup");
            this.waitForArticleToAppearByTitle(nameOfBookmark);
            String xpath = getBookmark(nameOfBookmark);
            this.swipeElementToLeft(
                    xpath,
                    "can't find marked page"
            );
            this.clickElementToTheRightUpCorner(BUTTON_TO_DELETE, "Can't click on delete bookmark button");
            waitForArticleToDisappearByTitle(nameOfBookmark);
        } else {
            this.waitForArticleToAppearByTitle(nameOfBookmark);
            String removeLocator = getRemoveButtonByTitle(nameOfBookmark);
            this.waitForElementAndClick(removeLocator, "Can't click button to remove article from saved list", 10);
            driver.navigate().refresh();
        }
    }




}
