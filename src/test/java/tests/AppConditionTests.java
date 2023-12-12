package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("change app conditions tests")
public class AppConditionTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "search"), @Feature(value = "article"), @Feature(value = "rotate")})
    @DisplayName("rotation screen test for app")
    @Description("Open article, rotate to landscape mode, verify article title, rotate to portrait mode, verify article title")
    @Step("start test testRotationOfScreenSearchResults()")
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

        Assert.assertEquals(
                "Article subtitle have been changed after rotation",
                titleBeforeRotating,
                titleAfterRotating
        );

        this.rotateScreenPortrait();
        String titleAfterSecondRotating = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "Article subtitle have been changed after second rotation",
                titleBeforeRotating,
                titleAfterSecondRotating
        );
    }

    @Test
    @Features(value = {@Feature(value = "search"), @Feature(value = "article"), @Feature(value = "background")})
    @DisplayName("move app to background")
    @Description("open article, move app to background, return, verify title of article")
    @Step("start test testSearchResultsInBackground()")
    public void testSearchResultsInBackground() {

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
