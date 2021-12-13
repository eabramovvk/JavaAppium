package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.WelcomePageObject;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Welcome")})
    @DisplayName("Pass welcome screen")
    @Description("Pass welcome screen, if he is present")
    @Step("Start testPassThroughWelcome")
    @Severity(value = SeverityLevel.BLOCKER)
    public void testPassThroughWelcome()
    {
        System.out.println("method started");
        if ((Platform.getInstance().isAndroid()) || (Platform.getInstance().isMW())){
            return;
        }
        WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
        WelcomePageObject.waitForLearnMoreLink();
        WelcomePageObject.clickSkip();
    }
}