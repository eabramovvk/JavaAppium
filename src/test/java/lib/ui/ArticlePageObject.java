package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.concurrent.TimeUnit;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
            TITLE_ID,
            OPTIONS_BUTTON,
            OPTIONS_ADD_TO_MY_LIST_BUTTON_ID,
            OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT,
            OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
            ADD_TO_MY_LIST_OVERLAY_ID,
            MY_LIST_NAME_INPUT,
            MY_LIST_OK_BUTTON_TEXT,
            CLOSE_ARTICLE_BUTTON,
            FOLDER_NAME_TPL,
            FOOTER_ELEMENT;


    public ArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    /* TEMPLATE METHODS */
    private static String addFolderName(String substring)
    {
        return FOLDER_NAME_TPL.replace("{SUBSTRING}", substring);
    }
    /* TEMPLATE METHODS */

    public WebElement waitForTitleElement()
    {
        return this.WaitForElementPresent(TITLE_ID, "No element with " + TITLE_ID + " id found", 15);
    }

    public String getArticleTitle(){
        WebElement title_element = waitForTitleElement();
        if(Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");
        } else if(Platform.getInstance().isIOS()){
            return title_element.getAttribute("name");
        } else {
            return title_element.getText();
        }
    }

    public void swipeToFooter(){
        if(Platform.getInstance().isAndroid()){
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else if (Platform.getInstance().isIOS()){
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.scrollWebPageTillElementNotVisible(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToMyListFirstTime(String name_of_folder)
    {
        this.WaitForElementAndClick(
                OPTIONS_BUTTON,
                "Can not find 'More options' button",
                5
        );
        //Wait for button Add to reading list
        this.WaitForElementBeingActive(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_ID,
                "Could not wait for element to be active"
        );

        this.WaitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT,
                "Can not find 'Add to reading list' button",
                5
        );

        this.WaitForElementAndClick(
                ADD_TO_MY_LIST_OVERLAY_ID,
                "Can not find 'GOT IT' button",
                5
        );

        this.WaitForElementAndClear(
                MY_LIST_NAME_INPUT,
                "Cannot find article folder input text field",
                5
        );

        this.WaitForElementAndSendKeys(
                MY_LIST_NAME_INPUT,
                "Cannot put text into article folder input text field",
                5,
                name_of_folder
        );

        this.WaitForElementAndClick(
                MY_LIST_OK_BUTTON_TEXT,
                "Cannot press OK button",
                5
        );
    }

    public void closeArticle()
    {
        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()){
            this.WaitForElementAndClick(CLOSE_ARTICLE_BUTTON,
                    "Cannot close article, cannot find X link", 5);
        } else {
            System.out.println("Method closeArticle() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void addArticleToExistingList(String name_of_folder)
    {

        this.WaitForElementAndClick(
                OPTIONS_BUTTON,
                "Cannot find 'More options' button",
                5
        );

        this.WaitForElementBeingActive(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_ID,
                "Could not wait for element to be active"
        );

        this.WaitForElementAndClick(
                OPTIONS_ADD_TO_MY_LIST_BUTTON_TEXT,
                "Can not find 'Add to reading list' button",
                5
        );
        String folder_name_xpath = addFolderName(name_of_folder);
        ;
        this.WaitForElementAndClick(
                folder_name_xpath,
                "Can not find '"+ name_of_folder + "' button",
                5
        );
    }

    public void checkTitlePresentWithAssert()
    {
        this.assertElementPresent(
                TITLE_ID,
                "No article title found"
        );
    }

    public void addArticlesToSaved()
    {
        this.WaitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON_ID, "Cannot find save button", 5);
    }

    public void addArticleToMySaved(){
        if(Platform.getInstance().isMW()){
            this.removeArticleFromSavedIfItAdded();
        }
        this.WaitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON_ID, "Cannot find option to add article to reading list", 5);
    }

    public void removeArticleFromSavedIfItAdded(){
        if (this.isElementPresent(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON)){
            this.WaitForElementAndClick(OPTIONS_REMOVE_FROM_MY_LIST_BUTTON,
                    "Cannot click btn to remove an article from saved", 2);
            this.WaitForElementPresent(OPTIONS_ADD_TO_MY_LIST_BUTTON_ID,
                    "Cannot find btn to add an article to saved list after removing it from this list before");
        }
    }
}