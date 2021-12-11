
package lib.ui.mobile_web;

import lib.ui.SearchPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class MWSearchPageObject extends SearchPageObject {
    static{
        SEARCH_INIT_ELEMENT ="css:button#searchIcon";
        SEARCH_INPUT ="css:form>input[type='search']";
        SEARCH_CANCEL_BUTTON ="css:button.cancel";
        SEARCH_RESULT_BY_SUBSTRING_TPL ="xpath://div[contains(@class,'wikidata-description')][contains(text(),'{SUBSTRING}')]";
        SEARCH_RESULT_ELEMENTS ="css:ul.page-list>li.page-summary";
        SEARCH_RESULT_ID = "id:org.wikipedia:id/page_list_item_title";
        SEARCH_RESULTS_TITLE_AND_DESCRIPTION_COMPARISION_TPL = "xpath://XCUIElementTypeStaticText[@name='{SUBSTRING_T}']/../XCUIElementTypeStaticText[@name='{SUBSTRING_D}']";
    }
    public MWSearchPageObject(RemoteWebDriver driver){
        super(driver);
    }
}