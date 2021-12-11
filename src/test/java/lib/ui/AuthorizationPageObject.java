package lib.ui;

import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject {
    private static final String
            LOGIN_BTN = "xpath://body/div/div/a[text()='Log in']",
            LOGIN_INPUT = "css:input[name='wpName']",
            PASSWORD_INPUT = "css:input[name='wpPassword']",
            SUBMIT_BTN = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver){
        super(driver);
    }

    public void clickAuthBtn() throws InterruptedException{
        this.WaitForElementPresent(LOGIN_BTN, "Cannot find auth btn", 5);
        Thread.sleep(1000);
        this.WaitForElementAndClick(LOGIN_BTN, "Cannot find and click auth btn", 5);
    }
    public void enterLoginData(String login, String password){
        this.WaitForElementAndSendKeys(LOGIN_INPUT, login, 5, "Cannot find and put a login to the login input");
        this.WaitForElementAndSendKeys(PASSWORD_INPUT, password,5, "Cannot find and put a password to the password input");
    }
    public void submitForm(){
        this.WaitForElementAndClick(SUBMIT_BTN, "Cannot find and click submit auth btn", 5);
    }
}