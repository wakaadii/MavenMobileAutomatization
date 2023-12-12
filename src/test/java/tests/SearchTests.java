package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

@Epic("search tests")
public class SearchTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "search"), @Feature(value = "article")})
    @DisplayName("basic search test")
    @Description("open articleverify title of article")
    @Step("start test testSearch()")
    public void testSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @Features(value = {@Feature(value = "search")})
    @DisplayName("Cancel search")
    @Description("open search, clear search field, verify that 'no result' message is displayed")
    @Step("start test testCancelSearch()")
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
    @Features(value = {@Feature(value = "search")})
    @DisplayName("Displaying one result")
    @Description("open search, verify that one result is displayed")
    @Step("start test testAmounOfNotEmptySearch()")
    public void testAmounOfNotEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchLine = "Linkin park discography";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.searchResultIsNotEmpty();
    }

    @Test
    @Features(value = {@Feature(value = "search")})
    @DisplayName("No results search")
    @Description("open search, verify that no one result is displayed")
    @Step("start test testAmountOfEmptySearch()")
    public void testAmountOfEmptySearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchLine = "82drjv";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.noSearchResult();
    }
}
