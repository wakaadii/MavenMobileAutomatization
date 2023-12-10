package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.WelcomeScreenPageObject;
import org.openqa.selenium.remote.RemoteWebDriver;

public class IOSWelcomeScreenPageObject extends WelcomeScreenPageObject {

    static {
        SCIP_WELCOME_SCREEN_BUTTON = "xpath://XCUIElementTypeButton[@name='Skip']";
        NEXT_IOS_BUTTON_XPATH = "xpath://*[@type = 'XCUIElementTypeButton' and @name = 'Next']";
        LEARN_MORE_ABOUT_WIKI_XPATH = "xpath://*[@name = 'Learn more about Wikipedia']";
        NEW_WAYS_TO_EXPLORE_ID = "id://New ways to explore";
        ADD_OR_EDIT_PREFERED_LANGUAGES_XPATH = "xpath://*[@name = 'Add or edit preferred languages']";
        LEARN_NORE_ABOUT_DATA_COLLECTED_XPATH = "xpath://*[@type = 'XCUIElementTypeStaticText' and @name = 'Learn more about data collected']";
        GET_STARTED_BUTTON_XPATH = "xpath://*[@type = 'XCUIElementTypeButton' and @name = 'Get started']";
    }

    public IOSWelcomeScreenPageObject(RemoteWebDriver driver) {
        super(driver);
    }
}
