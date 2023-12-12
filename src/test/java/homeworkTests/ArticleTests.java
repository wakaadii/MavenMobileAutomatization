package homeworkTests;

import io.qameta.allure.Epic;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Epic("article page tests")
public class ArticleTests extends CoreTestCase {
    //ex6
    @Test
    public void testAssertElementPresent() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String textSavedPage = "Object-oriented programming language";
        String searchLine = "java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        //задержка, чтобы тест не падал
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        SearchPageObject.waitForSearchResult(textSavedPage);
        SearchPageObject.clickByArticleWithSubstring(textSavedPage);

        ArticlePageObject.assertElementPresent();
    }
}
