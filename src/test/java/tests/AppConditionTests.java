package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Test;

public class AppConditionTests extends CoreTestCase {
    @Test
    public void testRotationOfScreenSearchResults() {
        if (Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String searchLine = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String titleBeforeRotating = ArticlePageObject.getArticleTitle();
        this.rotateScreenLandscape();
        String titleAfterRotating = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article subtitle have been changed after rotation",
                titleBeforeRotating,
                titleAfterRotating
        );

        this.rotateScreenPortrait();
        String titleAfterSecondRotating = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Article subtitle have been changed after second rotation",
                titleBeforeRotating,
                titleAfterSecondRotating
        );
    }

    @Test
    public void testSearchResultsInBackground () {

        if (Platform.getInstance().isMW()){
            return;
        }

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        String searchLine = "Java";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchLine);
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitleElement();

        this.moveToBackground(5);

        ArticlePageObject.waitForTitleElement();
    }
}
