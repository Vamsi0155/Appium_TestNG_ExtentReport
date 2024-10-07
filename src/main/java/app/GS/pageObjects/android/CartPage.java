package app.GS.pageObjects.android;

import app.GS.factory.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends AndroidActions {

    private final AndroidDriver driver;

    public CartPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    WebElement pageTitle;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/totalAmountLbl")
    WebElement totalAmount;

    @AndroidFindBy(className = "android.widget.CheckBox")
    WebElement checkBox;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    WebElement purchaseButton;

    public String verifyPageTitle() {
        waitForElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
        return pageTitle.getAttribute("text");
    }

    public double getTotalAmount() {
        return convertToInteger(totalAmount.getAttribute("name"));
    }

    public void clickOnCheckBox() {
        checkBox.click();
    }

    public void clickOnPurchase() {
        purchaseButton.click();
    }

}
