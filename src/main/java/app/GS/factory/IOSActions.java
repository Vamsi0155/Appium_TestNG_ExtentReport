package app.GS.factory;


import com.google.common.collect.ImmutableMap;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

public class IOSActions extends AppiumActions {

    private final IOSDriver driver;

    public IOSActions(IOSDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void rotateDevice(WebElement element) {

        driver.executeScript("mobile: rotateElement", ImmutableMap.of(
                // rotate clockwise, 90 degrees
                "rotation", -Math.PI / 2,
                // in approximately two seconds
                "velocity", Math.PI / 4,
                "elementId", ((RemoteWebElement)element).getId()
        ));
    }
}
