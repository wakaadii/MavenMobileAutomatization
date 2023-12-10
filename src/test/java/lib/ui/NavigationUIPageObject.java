package lib.ui;

import lib.Platform;
import org.openqa.selenium.remote.RemoteWebDriver;

public class NavigationUIPageObject extends MainPageObject{

    protected static String OPEN_NAVIGATION = "css:label#mw-mf-main-menu-button",
                            MY_LIST_LINKS = "xpath://a[@data-event-name='menu.watchlist']/..";


    public void openNavigation() {
        if (Platform.getInstance().isMW()) {
            this.waitForElementAndClick(OPEN_NAVIGATION, "Can't click on navigation button", 5);
        } else  {
            System.out.println("method openNavigation() does nothing for platform " + Platform.getInstance().getPlatformVar());
        }
    }

    public void clickToMyLists() {
        if (Platform.getInstance().isMW()) {
            this.tryClickElementWithFewAttempts(MY_LIST_LINKS, "Can't find navigation button to saved links", 10);
        } else {
            this.waitForElementAndClick(MY_LIST_LINKS, "Can't find navigation button to saved links");
        }
    }

    public NavigationUIPageObject (RemoteWebDriver driver) { super(driver); }
}
