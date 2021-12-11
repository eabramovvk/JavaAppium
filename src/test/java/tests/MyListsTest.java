package tests;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;

public class MyListsTest extends CoreTestCase {
    private static final String
            login = "eabrtestappium",
            password = "QAZwsx123!";

    @Test //Ex5: Тест: сохранение двух статей
    public void testDeleteArticleFromReadingList() throws InterruptedException {

        String textToSearch = "Java";
        String listName = "My list";
        String androidArticleTitle = "object-oriented programming language";
        String articleTitle = "bject-oriented programming language";
        String articleToDeleteTitle = "Island of Indonesia";
        String androidArticleToDeleteTitle = "island of Indonesia";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        //Adding article to the list
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(textToSearch);
        SearchPageObject.clickByArticleWithSubstring(articleTitle);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToMyListFirstTime(listName);
        } else
        {
            ArticlePageObject.addArticlesToSaved();
        }
        if(Platform.getInstance().isMW()){
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            Auth.clickAuthBtn();
            Auth.enterLoginData(login, password);
            Auth.submitForm();
            ArticlePageObject.waitForTitleElement();

            assertEquals("We are not on the same page after login", article_title, ArticlePageObject.getArticleTitle());

            ArticlePageObject.addArticleToMySaved();
        }
        //Close article
        ArticlePageObject.closeArticle();
        //Adding second article to the list
        if (Platform.getInstance().isAndroid())
        {
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(textToSearch);
        }
        SearchPageObject.clickByArticleWithSubstring(articleToDeleteTitle);
        ArticlePageObject.waitForTitleElement();
        if (Platform.getInstance().isAndroid())
        {
            ArticlePageObject.addArticleToExistingList(listName);
        } else {
            ArticlePageObject.addArticlesToSaved();
        }

        //Close article
        ArticlePageObject.closeArticle();
        if (Platform.getInstance().isIOS()){
            SearchPageObject.clickCancelSearch();
        }


        NavigationUi NavigationUi = NavigationUIFactory.get(driver);
        NavigationUi.openNavigation();
        NavigationUi.clickMyLists();
        //Open lists
        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid())
        {
            MyListsPageObject.openFolderByName(listName);
            MyListsPageObject.swipeByArticleToDelete(androidArticleToDeleteTitle);
            MyListsPageObject.openArticle(androidArticleTitle);
        } else {
            MyListsPageObject.closeSyncPopup();
            MyListsPageObject.swipeByArticleToDelete(articleToDeleteTitle);
            MyListsPageObject.openArticle(articleTitle);
        }
        String actual_title = ArticlePageObject.getArticleTitle();
        Assert.assertEquals(
                "Unexpected article title",
                "Java (programming language)",
                actual_title);
    }
}