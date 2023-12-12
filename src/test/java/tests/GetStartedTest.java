package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomeScreenPageObject;
import lib.ui.factory.WelcomeScreenPageObjectFactory;
import org.junit.Test;

@Epic("welcome screen for ios")
public class GetStartedTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "welcome screen")})
    @DisplayName("pass through welcome screen for ios")
    @Description("open welcome screen and move by it's pages")
    @Step("start test testPassThroughWelcome()")
    public void testPassThroughWelcome() {
        if (Platform.getInstance().isAndroid() || (Platform.getInstance().isMW())) {
            return;
        }
        WelcomeScreenPageObject WelcomeScreenPageObject = WelcomeScreenPageObjectFactory.get(driver);

        WelcomeScreenPageObject.learnMoreWikiLinkIOS();
        WelcomeScreenPageObject.nextWelcomeScreenIOS();

        WelcomeScreenPageObject.waitForNewWaysTextIOS();
        WelcomeScreenPageObject.nextWelcomeScreenIOS();

        WelcomeScreenPageObject.preferredLanguagesLinkIOS();
        WelcomeScreenPageObject.nextWelcomeScreenIOS();

        WelcomeScreenPageObject.learnMoreDataLinkIOS();
        WelcomeScreenPageObject.clickGetStartedButtonIOS();
    }
}
