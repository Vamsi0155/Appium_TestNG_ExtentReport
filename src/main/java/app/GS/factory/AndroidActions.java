package app.GS.factory;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.Set;

public class AndroidActions extends AppiumActions {

    private final AndroidDriver driver;

    public AndroidActions(AndroidDriver driver){
        super(driver);
        this.driver = driver;
    }

    public void switchToView(String contextName){

        setNativeContext(driver.getContext());
        if(contextName.equalsIgnoreCase("NativeView")) {
            driver.context(getNativeContext());
        }
        else {
            Set<String> contexts = driver.getContextHandles();
            for (String context : contexts) {
                if (!context.contains(getNativeContext())) {
                    driver.context(context);
                }
            }
        }
    }

    public void openScreen(String packageWithActivity) {
        ((JavascriptExecutor) driver).executeScript("mobile: startActivity",
                ImmutableMap.of("intent", packageWithActivity)
        );
    }

    public WebElement scrollUntilView(String text) {
        return driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\""+text+"\"));"));
    }

    public void longPressOn(WebElement element) {
        ((JavascriptExecutor)driver).executeScript("mobile: longClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement)element).getId()));
    }

    public void doubleClickOn(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: doubleClickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
    }

    public void clickOn(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: clickGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId()));
    }

    public void dragOn() {

    }

    public void swipeTo(String direction) {
        /*
            The id of the element to be swiped. If the element id is missing then swipe bounding area must be provided.
            If both the element id and the swipe bounding area are provided then the area is effectively ignored.
         */
        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
                ImmutableMap.of("left",100, "top",100, "width",200, "height",200,
                        "direction",direction,
                        "percent", 0.75
                )
        );
    }

    public boolean scrollTo(String direction) {
        /*
            The id of the element to be scrolled. If the element id is missing then scroll bounding area must be provided.
            If both the element id and the scroll bounding area are provided then this area is effectively ignored.
         */
        return (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
                ImmutableMap.of(
                        "left", 100, "top", 100, "width", 200, "height", 200,
                        "direction", direction,
                        "percent", 1.0
                ));
    }

    public void zoomIn(WebElement element) {

        ((JavascriptExecutor) driver).executeScript("mobile: pinchOpenGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                "percent", 0.75
        ));
    }

    public void zoomOut(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("mobile: pinchCloseGesture",
                ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(),
                        "percent", 0.75
        ));
    }


    public void rotateDevice(int x, int y, int z) {
        DeviceRotation rotation = new DeviceRotation(x,y,z);
        driver.rotate(rotation);
    }

    public void doCopyPasteText(String text) {
        driver.setClipboardText(text);
        driver.getClipboardText();
    }
    
    public void pressKey(String key){
        if(key.equals("HOME")){
            driver.pressKey(new KeyEvent(AndroidKey.HOME));
        } else if (key.equals("BACK")) {
            driver.pressKey(new KeyEvent(AndroidKey.BACK));
        }
    }
}
