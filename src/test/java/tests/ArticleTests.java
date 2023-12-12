package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factory.ArticlePageObjectFactory;
import lib.ui.factory.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

@Epic("article page tests")
public class ArticleTests extends CoreTestCase {
    @Test
    @Features(value = {@Feature(value = "search"), @Feature(value = "article")})
    @DisplayName("Compare article title with expected one")
    @Description("Open search 'Java' -> link with 'Object-oriented programming language' subtitle and expect 'Java (programming language)' title")
    @Step("start test testCompareArticleTitle()")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCompareArticleTitle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        String articleTitle;
        articleTitle = ArticlePageObject.getArticleTitle();

        Assert.assertEquals(
                "this is not expected title",
                "Java (programming language)",
                articleTitle
        );

    }
    @Test
    @Features(value = {@Feature(value = "search"), @Feature(value = "article"), @Feature(value = "scroll")})
    @DisplayName("Swipe article to the footer")
    @Description("Open search 'appium' -> subtitle 'Automation for Apps' and scroll down to footer")
    @Step("start test testSwipeArticle() ")
    @Severity(value = SeverityLevel.MINOR)
    public void testSwipeArticle() {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("appium");
        SearchPageObject.clickByArticleWithSubstring("Automation for Apps");
        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.swipeToFooter();

    }
}
