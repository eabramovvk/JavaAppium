package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class WelcomePageObject extends MainPageObject {

    private static final String
            WELCOME_TEXT_XPATH = "xpath://XCUIElementTypeStaticText[@name=\"Wikipedia is written collaboratively by volunteers and consists of more than 40 million articles in nearly 300 languages.\"]\n",
            SKIP_BUTTON_XPATH = "xpath://XCUIElementTypeButton[@name=\"Skip\"]\n";


    public WelcomePageObject (RemoteWebDriver driver)
    {
        super(driver);
    }



    public void waitForLearnMoreLink()
    {
        this.WaitForElementPresent(WELCOME_TEXT_XPATH, "Cannot find '" + SKIP_BUTTON_XPATH + "' text", 5);
    }

    public void clickSkip()
    {
        this.WaitForElementAndClick(SKIP_BUTTON_XPATH, "Cannot click skip button", 5);
    }
}