package app.GS.factory;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AppiumActions {

    private final AppiumDriver driver;
    private String nativeContext;

    public AppiumActions(AppiumDriver driver) {
        this.driver =driver;
    }

    public void waitForElement(By locator) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void setNativeContext(String context){
        this.nativeContext = context;
    }
    public String getNativeContext() {
        return nativeContext;
    }

    public void waitFewSeconds(int sec){
        try{
            Thread.sleep(sec);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public double convertToInteger(String value) {
        value = value.replace("$","").trim();
        return Double.parseDouble(value);
    }
}
