package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.WelcomeScreenPageObject;
import lib.ui.factory.WelcomeScreenPageObjectFactory;
import org.junit.Test;

public class GetStartedTest extends CoreTestCase {

    @Test
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
