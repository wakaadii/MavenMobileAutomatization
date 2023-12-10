package lib;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

//        capabilities.setCapability("app", "C:/programming/JavaAppiumAutomation/JavaAppiumAutomation/apks/org.wikipedia.apk");

public class Platform {
    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            PLATFORM_MOBILE_WEB = "mobile_web",
            APPIUM_URL = "http://127.0.0.1:4723/";

    private static Platform instance;

    private Platform() {}

    public static Platform getInstance() {
        if (instance == null) {
            instance = new Platform();
        }
        return instance;
    }

    public RemoteWebDriver getDriver() throws Exception {
        URL URL = new URL(APPIUM_URL);
        if (this.isAndroid()) {
            return new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        } else if (this.isIOS()) {
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        } else if (this.isMW()) {
            return new ChromeDriver(this.getMWChromeOptions());
        } else {
            throw new Exception("can't get run platform from env variable. Platform value = " + this.getPlatformVar());
        }
    }

    public boolean isAndroid() {
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS () {
        return isPlatform(PLATFORM_IOS);
    }

    public boolean isMW () {
        return isPlatform(PLATFORM_MOBILE_WEB);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities () throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("appium:platformName", "Android");
        capabilities.setCapability("appium:deviceName", "amt");
        capabilities.setCapability("appium:platformVersion", "8.1");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appium:appPackage", "org.wikipedia");
        capabilities.setCapability("appium:appActivity", ".main.MainActivity");
        capabilities.setCapability("appium:app", "/Users/shanti/IdeaProjects/MobileAutomatization/apks/org.wikipedia.apk");

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities () throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "amt");
        capabilities.setCapability("platformVersion", "14.1");
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("app", "/Users/shanti/IdeaProjects/MobileAutomatization/apks/org.wikipedia.ios.app");

        return capabilities;
    }

    private ChromeOptions getMWChromeOptions() {
        Map<String, Object> deviceMetrics = new HashMap<String, Object>();
        deviceMetrics.put("width", 360);
        deviceMetrics.put("height", 640);
        deviceMetrics.put("pixelRatio", 3.0);

        Map<String, Object> mobileEmulation = new HashMap<String, Object>();
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent", "Mozilla/5.0 (Linux; Android 9.0; en-us; Nexus 5 Build/JOP40D) AppleWebKit/535.19 (KHTML, like Gecko) Chrome/120.0.6099.62 Mobile Safari/535.19");

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("window-size=360,640");
        return chromeOptions;
    }

    private boolean isPlatform (String my_platform) {
        String platform = this.getPlatformVar();
        return platform.equals(my_platform);
    }

    public String getPlatformVar() {
        return System.getenv("PLATFORM");
    }

}
