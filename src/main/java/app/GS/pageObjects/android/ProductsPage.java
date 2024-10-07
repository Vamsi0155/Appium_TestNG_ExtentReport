package app.GS.pageObjects.android;

import app.GS.factory.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductsPage extends AndroidActions {

    private final AndroidDriver driver;

    public ProductsPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    WebElement pageTitle;

    @AndroidFindBy(xpath="//android.widget.TextView[@text=\"ADD TO CART\"]")
    List<WebElement> addToCartButton;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    WebElement cartButton;

    public String verifyPageTitle() {
        waitForElement(By.id("com.androidsample.generalstore:id/toolbar_title"));
        return pageTitle.getAttribute("text");
    }

    public void clickOnAddToCart(int item) {
        addToCartButton.get(item).click();
    }

    public CartPage clickOnCart() {
        cartButton.click();
        return new CartPage(driver);
    }

}
