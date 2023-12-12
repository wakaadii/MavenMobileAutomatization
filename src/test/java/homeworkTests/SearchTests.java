package homeworkTests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("search tests")
public class SearchTests extends CoreTestCase {
    //ex2
    @Test
    @Features(value = {@Feature(value = "search")})
    @DisplayName("article in search results")
    @Description("Verifying that article is displayed in searc results list")
    @Step("start test testComparisonTextOfElement()")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testComparisonTextOfElement() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
//        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    //ex3
    @Test
    @Features(value = {@Feature(value = "search")})
    @DisplayName("canceled search")
    @Description("There is no results after clearing search field")
    @Step("start test testCanceledSearch()")
    @Severity(value = SeverityLevel.MINOR)
    public void testCanceledSearch() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchLine = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.searchResultIsNotEmpty();
        SearchPageObject.clearSearchField();
        SearchPageObject.searchResultIsEmpty();
    }
    //ex4
    @Test
    @Features(value = {@Feature(value = "search")})
    @DisplayName("check search word in search results")
    @Description("In every article of search results must be '{searchLine}' word")
    @Step("start test testCheckWord()")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testCheckWord() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        String searchLine = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
        int counter = SearchPageObject.countNumberOfLines();

        for (int i = 0; i < counter; i++ ) {
            Assert.assertTrue(
                    "there is no java in text of " + i + " element",
                    SearchPageObject.getTextOfLine(i).contains(searchLine));
        }
    }
}
