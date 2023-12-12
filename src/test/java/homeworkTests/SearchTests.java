package homeworkTests;

import io.qameta.allure.Epic;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("search tests")
public class SearchTests extends CoreTestCase {
    //ex2
    @Test
    public void testComparisonTextOfElement() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
//        SearchPageObject.waitForSearchResult("Java (programming language)");
    }

    //ex3
    @Test
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
