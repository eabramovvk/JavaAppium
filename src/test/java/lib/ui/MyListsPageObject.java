package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.awt.*;


abstract public class MyListsPageObject extends MainPageObject {

    protected static String
            FOLDER_BY_NAME_TPL,
            ARTICLE_BY_TITLE_TPL,
            POPUP_CLOSE_BUTTON,
            POPUP_TITLE,
            SWIPE_TRASH_BUTTON,
            PAGE_WITH_LISTS,
            REMOVE_FROM_SAVED_BUTTON;

    public MyListsPageObject (RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String addFolderName(String folder_name)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", folder_name);
    }

    private static String getSavedArticleByXpath(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    /* TEMPLATE METHODS */

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = addFolderName(name_of_folder);
        this.WaitForElementBeingActive(
                folder_name_xpath,
                "Cannot find folder by name"
        );

        this.WaitForElementAndClick(
                folder_name_xpath,
                "Can not click folder by name",
                5
        );
    }
    private static String getFolderXpathByName(String name_of_folder){
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title){
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title){
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    private static String getResultSearchElement(String substring){
        return PAGE_WITH_LISTS.replace("{SUBSTRING}", substring);
    }

    public void waitForArticleToApearByTitle(String article_title){
        String article_xpath = getFolderXpathByName(article_title);
        this.WaitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleByDissapearByTitle(String article_title){
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.WaitForElementNotPresent(article_xpath, "Saved article still present with title " + article_title, 15);
    }

    public void swipeByArticleToDelete(String articleTitle)
    {
        this.waitForArticleToApearByTitle(articleTitle);
        String article_xpath = getSavedArticleXpathByTitle(articleTitle);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.SwipeElementToTheLeft(article_xpath,
                    "Cannot find saved article");} else {
            String remove_locator = getRemoveButtonByTitle(articleTitle);
            this.WaitForElementAndClick(remove_locator, "Cannot click btn to remove article from saved", 10);
        }

        if(Platform.getInstance().isIOS()){
            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
        }
        if(Platform.getInstance().isMW()){
            driver.navigate().refresh();
        }
        this.waitForArticleByDissapearByTitle(articleTitle);
    }

    public void openArticle(String articleTitle)
    {
        String article_xpath = getSavedArticleByXpath(articleTitle);
        if (Platform.getInstance().isAndroid())
        {
            this.WaitForElementBeingActive(
                    article_xpath,
                    "Could not wait for article to be active"
            );
        }
        this.WaitForElementAndClick(
                article_xpath,
                "No element with " + article_xpath + " found or unable to click",
                5
        );
    }

    public void closeSyncPopup(){
        this.WaitForElementAndClick(POPUP_CLOSE_BUTTON, "Cannot press sync popup close button", 5);
    }
}
