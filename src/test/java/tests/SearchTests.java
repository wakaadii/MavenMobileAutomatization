package tests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    @Test
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    @Test
    public void testCancelSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForCancelButtonToAppear();
        SearchPageObject.clickCancelSearch();
//        Use for manual clearing field
//        SearchPageObject.clearSearchField();
        SearchPageObject.waitForCancelButtonToDisappear();
    }

    @Test
    public void testAmounOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchLine = "Linkin park discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.searchResultIsNotEmpty();
    }

    @Test
    public void testAmounOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchLine = "82drjv";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.noSearchResult();
    }
}
