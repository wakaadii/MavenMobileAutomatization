package homeworkTests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SavedListsPageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SavedBookmarksTests extends CoreTestCase {

    //ex5
    private static final String searchLine = "java",
            folderName = "first list",
            textFirstSavedPage = "Object-oriented programming language",
            textSecondSavedPage = "High-level programming language",
            login = "wakaadii",
            password = "Aezakmi1";
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
        } else if (Platform.getInstance().isIOS()){
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
        } else {
            AuthorizationPageObject auth = new AuthorizationPageObject(driver);
            NavigationUIPageObject navigation = new NavigationUIPageObject(driver);
            ArticlePageObject.waitForTitleElement();
            FirstArticleTitle = ArticlePageObject.getArticleTitle();
            ArticlePageObject.saveArticleToDefaultList();

            auth.clickAuthButton();
            auth.enterLoginData(login, password);
            auth.submitForm();

            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login",
                    FirstArticleTitle,
                    ArticlePageObject.getArticleTitle());

            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchLine);
            SearchPageObject.clickByArticleWithSubstring(textSecondSavedPage);
            ArticlePageObject.waitForTitleElement();
            SecondArticleTitle = ArticlePageObject.getArticleTitle();
            ArticlePageObject.saveArticleToDefaultList();
            navigation.openNavigation();
            navigation.clickToMyLists();
            SavedListsPageObject.deleteBookmarkFromList(FirstArticleTitle);
            System.out.println(SecondArticleTitle);
            SavedListsPageObject.waitForArticleToAppearByH3(SecondArticleTitle);

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
