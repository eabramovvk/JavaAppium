package lib.ui.mobile_web;

import lib.ui.ArticlePageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWArticlePageObject extends ArticlePageObject {
    static {
        TITLE_ID = "css:#content h1";
        OPTIONS_ADD_TO_MY_LIST_BUTTON_ID = "css:#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-star-base20";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        FOLDER_NAME_TPL = "xpath://*[@text='{SUBSTRING}']";
        FOOTER_ELEMENT = "css:footer";
        OPTIONS_REMOVE_FROM_MY_LIST_BUTTON = "css:#page-actions-watch #ca-watch.mw-ui-icon-wikimedia-unStar-progressive";
    }

    public MWArticlePageObject(RemoteWebDriver driver)
    {
        super(driver);
    }
}