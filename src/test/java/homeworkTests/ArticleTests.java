package homeworkTests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class ArticleTests extends CoreTestCase {
    //ex6
    //тест должен падать - не успевает прогрузиться страница перед ArticlePageObject.assertElementPresent()
    @Test
    public void testAssertElementPresent() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String textSavedPage = "Java (programming language)";
        String searchLine = "java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.waitForSearchResult(textSavedPage);
        SearchPageObject.clickByArticleWithSubstring(textSavedPage);

        ArticlePageObject.assertElementPresent();
    }
}
