package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

@Epic("Tests for articles")
public class ArticleTests extends CoreTestCase {
    @Test //Ex6: Тест: assert title
    @Features(value = {@Feature(value = "Search"), @Feature(value = "Article")})
    @DisplayName("Open and assert title and article together")
    @Description("We open and assert title and article together in the search result list")
    @Step("Start testCheckArticleTitlePresent")
    @Severity(value = SeverityLevel.NORMAL)
    public void testCheckArticleTitlePresent() {
        String textToSearch = "Java";
        String articleToOpen = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textToSearch);
        SearchPageObject.clickByArticleWithSubstring(articleToOpen);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.takeScreenshoot("article_page");
        ArticlePageObject.checkTitlePresentWithAssert();
    }
}