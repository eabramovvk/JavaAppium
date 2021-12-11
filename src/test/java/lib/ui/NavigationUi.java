package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class NavigationUi extends MainPageObject{

    protected static String
            MY_LISTS_LINK,
            OPEN_NAVIGATION;

    public NavigationUi(RemoteWebDriver driver){
        super(driver);
    }

    public void openNavigation(){
        if (Platform.getInstance().isMW()){
            this.WaitForElementAndClick(OPEN_NAVIGATION, "Cannot find and click open navigation btn", 5);
        } else {
            System.out.println("Method openNavigation() do nothing for platform" + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickMyLists()
    {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementsWithFewAttempts(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        } else {
            this.WaitForElementAndClick(
                    MY_LISTS_LINK,
                    "Cannot find navigation button to My list",
                    5
            );
        }
    }
}