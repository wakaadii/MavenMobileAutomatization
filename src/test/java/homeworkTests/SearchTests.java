package homeworkTests;

import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class SearchTests extends CoreTestCase {
    //ex2
    @Test
    public void testComparisonTextOfElement() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java (programming language)");
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
        System.out.println(SearchPageObject.getTextOfLine(0));

        for (int i = 0; i < counter; i++ ) {
            assertTrue(
                    "there is no java in text of " + i + " element",
                    SearchPageObject.getTextOfLine(i).contains(searchLine));
        }
    }
}
