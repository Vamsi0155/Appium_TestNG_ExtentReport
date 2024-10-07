package app.GS.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

@Listeners(app.GS.utils.Reports.class)
public class BaseTest {

    private AppiumDriverLocalService service;

    private UiAutomator2Options androidOptions;
    private XCUITestOptions iOSOptions;

    public AppiumDriver driver;

    private final Logger logger =LogManager.getLogger("BaseTest.class");

    private String platform;

    @BeforeSuite
    public void initiateServer(){

        logger.info("!!!! Automation suite has started !!!!");
        service = new AppiumServiceBuilder().withAppiumJS(new File(System.getProperty("user.home") + ReadConfig.config.getProperty("AppiumJSPath")))
                .withIPAddress(ReadConfig.config.getProperty("IPAddress")).usingPort(Integer.parseInt(ReadConfig.config.getProperty("Port"))).build();
        service.start();

        System.out.println("Appium server has started!!!!");
        logger.info("Appium server has started!!!!");
    }

    @Parameters("Platform")
    @BeforeTest
    public void initiateAutomator(@Optional() String platformParam) {
        /* Note: 'UiAutomator2Options' has default set of PlatformName & AutomationName as well as XCUITestOptions. */

        platform = platformParam !=null ? platformParam : ReadConfig.config.getProperty("Platform");

        if(platform.equals("iOS")) {

            iOSOptions = new XCUITestOptions();
            iOSOptions.setDeviceName(ReadConfig.config.getProperty("DeviceName"));
            iOSOptions.setPlatformVersion(ReadConfig.config.getProperty("PlatformVersion"));
            iOSOptions.setApp(System.getProperty("user.dir") + ReadConfig.config.getProperty("AppPath"));
            iOSOptions.setNewCommandTimeout(Duration.ofSeconds(20));

            System.out.println("IOS details are set!!");
            logger.info("IOS details are set!!");
        } else {

            androidOptions = new UiAutomator2Options();
            androidOptions.setDeviceName(ReadConfig.config.getProperty("DeviceName"));
            androidOptions.setPlatformVersion(ReadConfig.config.getProperty("PlatformVersion"));
            androidOptions.setApp(System.getProperty("user.dir") + ReadConfig.config.getProperty("AppPath"));
            androidOptions.setNewCommandTimeout(Duration.ofSeconds(20));
            androidOptions.setAutoGrantPermissions(true);
            if(ReadConfig.config.getProperty("MobileBrowser").equals("Enabled")){
                androidOptions.setCapability("browserName", ReadConfig.config.getProperty("Browser"));
                androidOptions.setChromedriverExecutable(ReadConfig.config.getProperty("BrowserPath"));
            }

            System.out.println("Android details are set!!");
            logger.info("Android details are set!!");
        }
    }

    @BeforeClass
    public void initiateDriver() throws MalformedURLException {

        if(platform.equals("iOS")) {

            driver = new IOSDriver(new URL(ReadConfig.config.getProperty("RemoteAddress")), iOSOptions);

            System.out.println("IOS driver initialized!!");
            logger.info("IOS driver initialized!!");
        } else {

            driver = new AndroidDriver(new URL(ReadConfig.config.getProperty("RemoteAddress")), androidOptions);

            System.out.println("Android driver initialized!!");
            logger.info("Android driver initialized!!");
        }
    }
    @AfterClass
    public void closeDriver() {

        if(driver !=null) {
            driver.quit();

            System.out.println("Appium driver closed!!");
            logger.info("Appium driver closed!!");
        }
    }

    @AfterTest
    public void closeAutomator() {

    }
    @AfterSuite
    public void closeServer() {

        if(service != null) {
            service.stop();

            System.out.println("Appium server has closed!!!!");
            logger.info("Appium server has closed!!!!");
        }
        logger.info("!!!! Automation suite has completed !!!!");
    }

}
