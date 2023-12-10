package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SavedListsPageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SavedBookmarksTests extends CoreTestCase {

    private static final String folderName = "first list",
                                login = "wakaadii",
                                password = "Aezakmi1";

    @Test
    public void testSaveAndDeleteBookmarks(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SavedListsPageObject SavedListsPageObject = SavedListsPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String ArticleTitle = "";
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            ArticleTitle = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToNewList(folderName);
            ArticlePageObject.closePage();
            ArticlePageObject.closePage();
            ArticlePageObject.openSavedLists();
            SavedListsPageObject.openListOfBookmarks(folderName);
            SavedListsPageObject.deleteBookmarkFromList(ArticleTitle);
        } else if (Platform.getInstance().isIOS()){
            ArticlePageObject.waitForTitleElement("Java (programming language)");
            ArticleTitle = ArticlePageObject.getArticleTitle("Java (programming language)");
            ArticlePageObject.saveArticleToDefaultList();
            ArticlePageObject.closePage();
            SearchPageObject.clickCancelSearch();
            ArticlePageObject.openSavedLists();
            SavedListsPageObject.deleteBookmarkFromList(ArticleTitle);
        } else {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            NavigationUIPageObject navigation = new NavigationUIPageObject(driver);
            ArticlePageObject.waitForTitleElement();
            ArticleTitle = ArticlePageObject.getArticleTitle();
            ArticlePageObject.saveArticleToDefaultList();

            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    ArticleTitle,
                    ArticlePageObject.getArticleTitle());

            navigation.openNavigation();
            navigation.clickToMyLists();
            SavedListsPageObject.deleteBookmarkFromList(ArticleTitle);
        }
    }
}
