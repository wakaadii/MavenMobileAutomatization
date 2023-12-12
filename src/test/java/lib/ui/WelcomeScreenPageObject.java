package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class WelcomeScreenPageObject extends MainPageObject{

    protected static String
        SCIP_WELCOME_SCREEN_BUTTON,
        NEXT_IOS_BUTTON_XPATH,
        LEARN_MORE_ABOUT_WIKI_XPATH,
        NEW_WAYS_TO_EXPLORE_ID,
        ADD_OR_EDIT_PREFERED_LANGUAGES_XPATH,
        LEARN_NORE_ABOUT_DATA_COLLECTED_XPATH,
        GET_STARTED_BUTTON_XPATH;

    public WelcomeScreenPageObject(RemoteWebDriver driver) {
        super(driver);
    }
    public void clickSkip(){
        this.waitForElementAndClick(SCIP_WELCOME_SCREEN_BUTTON, "can't skip welcome screen");
    }

    @Step("move to next selcome screen")
    public void nextWelcomeScreenIOS () {
        this.waitForElementAndClick(NEXT_IOS_BUTTON_XPATH, "can't find 'next' button");
    }

    @Step("verify that 'learn more' is presented")
    public void learnMoreWikiLinkIOS () {
        this.waitForElementPresents(LEARN_MORE_ABOUT_WIKI_XPATH, "there is no element 'Learn more'");
    }

    @Step("verify that 'next ways' is presented")
    public void waitForNewWaysTextIOS () {
        this.waitForElementPresents(NEW_WAYS_TO_EXPLORE_ID, "there is no element 'New ways to explore'");
    }

    @Step("verify that 'preferred languages' is presented")
    public void preferredLanguagesLinkIOS () {
        this.waitForElementPresents(ADD_OR_EDIT_PREFERED_LANGUAGES_XPATH, "there is no element 'Add or edit preferred languages'");
    }

    @Step("verify that 'learn more details' is presented")
    public void learnMoreDataLinkIOS() {
        waitForElementPresents(LEARN_NORE_ABOUT_DATA_COLLECTED_XPATH,"there is no element 'Learn more about data collected'");
    }

    @Step("click 'get started' button")
    public void clickGetStartedButtonIOS() {
        this.waitForElementAndClick(GET_STARTED_BUTTON_XPATH, "can't find 'Get started' button");
    }
}
