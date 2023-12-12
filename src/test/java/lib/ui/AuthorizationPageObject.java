package lib.ui;

import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

public class AuthorizationPageObject extends MainPageObject{
    private static final String
        LOGIN_BUTTON = "xpath://body/div/div/a[contains(@class, 'cdx-button')]",
        LOGIN_INPUT = "css:input#wpName1",
        PASSWORD_INPUT = "css:input#wpPassword1",
        SUBMIT_BUTTON = "css:button#wpLoginAttempt";

    public AuthorizationPageObject(RemoteWebDriver driver) {
        super(driver);
    }

    @Step("go to auth pop-up")
    public void clickAuthButton() {
        this.tryClickElementWithFewAttempts(LOGIN_BUTTON,"Can't find login button", 5);
    }

    @Step("fill fields 'login' and 'password' by '{login}' and '{password}'")
    public void enterLoginData(String login, String password){
        this.waitForElementAndSend(LOGIN_INPUT, login, "Can,t find login field");
        this.waitForElementAndSend(PASSWORD_INPUT, password, "Can't find password field");
    }

    @Step("click on auth button")
    public void submitForm() {
        this.waitForElementAndClick(SUBMIT_BUTTON, "Can't find submit auth button");
    }
}
