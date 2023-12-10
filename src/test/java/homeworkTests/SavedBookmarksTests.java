package homeworkTests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SavedListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SavedListsPageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SavedBookmarksTests extends CoreTestCase {

    //ex5
    private static final String searchLine = "java",
            folderName = "first list",
            textFirstSavedPage = "Java (programming language)",
            textSecondSavedPage = "JavaScript";
    @Test
        public void testSaveAndDeleteBookmarks(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SavedListsPageObject SavedListsPageObject = SavedListsPageObjectFactory.get(driver);


        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring(textFirstSavedPage);
        String FirstArticleTitle = "",
                SecondArticleTitle = "";
        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.waitForTitleElement();
            FirstArticleTitle = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToNewList(folderName);
            ArticlePageObject.closePage();
            SearchPageObject.clickByArticleWithSubstring(textSecondSavedPage);
            ArticlePageObject.waitForTitleElement();
            SecondArticleTitle = ArticlePageObject.getArticleTitle();
            ArticlePageObject.addArticleToList(folderName);
            ArticlePageObject.closePage();
            ArticlePageObject.closePage();
            ArticlePageObject.openSavedLists();
            SavedListsPageObject.openListOfBookmarks(folderName);
            SavedListsPageObject.deleteBookmarkFromList(FirstArticleTitle);
            SavedListsPageObject.waitForArticleToAppearByTitle(SecondArticleTitle);
        } else {
            ArticlePageObject.waitForTitleElement(textFirstSavedPage);
            FirstArticleTitle = ArticlePageObject.getArticleTitle(textFirstSavedPage);
            ArticlePageObject.saveArticleToDefaultList();
            ArticlePageObject.closePage();
            SearchPageObject.clickByArticleWithSubstring(textSecondSavedPage);
            ArticlePageObject.waitForTitleElement(textSecondSavedPage);
            SecondArticleTitle = ArticlePageObject.getArticleTitle(textSecondSavedPage);
            ArticlePageObject.saveArticleToDefaultList();
            ArticlePageObject.closePage();
            SearchPageObject.clickCancelSearch();
            ArticlePageObject.openSavedLists();
            SavedListsPageObject.deleteBookmarkFromList(FirstArticleTitle);
            SavedListsPageObject.waitForArticleToAppearByTitle(SecondArticleTitle);
        }
    }

    //ex12

    private static final String textThirdSavedPage = "Java";
    @Test
    public void testSaveAndDeleteThreeBookmarks(){

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        SavedListsPageObject SavedListsPageObject = SavedListsPageObjectFactory.get(driver);


        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring(textFirstSavedPage);
        String FirstArticleTitle,
                SecondArticleTitle,
                ThirdArticleTitle;
        if (Platform.getInstance().isAndroid()) {
            return;
        } else {
            ArticlePageObject.waitForTitleElement(textFirstSavedPage);
            FirstArticleTitle = ArticlePageObject.getArticleTitle(textFirstSavedPage);
            ArticlePageObject.saveArticleToDefaultList();
            ArticlePageObject.closePage();
            SearchPageObject.clickByArticleWithSubstring(textSecondSavedPage);
            ArticlePageObject.waitForTitleElement(textSecondSavedPage);
            SecondArticleTitle = ArticlePageObject.getArticleTitle(textSecondSavedPage);
            ArticlePageObject.saveArticleToDefaultList();
            ArticlePageObject.closePage();
            SearchPageObject.clickByArticleWithSubstring(textThirdSavedPage);
            ArticlePageObject.waitForTitleElement(textThirdSavedPage);
            ThirdArticleTitle = ArticlePageObject.getArticleTitle(textThirdSavedPage);
            ArticlePageObject.saveArticleToDefaultList();
            ArticlePageObject.closePage();
            SearchPageObject.clickCancelSearch();
            ArticlePageObject.openSavedLists();
            SavedListsPageObject.deleteBookmarkFromList(FirstArticleTitle);
            SavedListsPageObject.waitForArticleToAppearByTitle(SecondArticleTitle);
            SavedListsPageObject.waitForArticleToAppearByTitle(ThirdArticleTitle);
        }
    }

}
