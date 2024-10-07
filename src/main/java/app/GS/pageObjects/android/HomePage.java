package app.GS.pageObjects.android;

import app.GS.factory.AndroidActions;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends AndroidActions {

     private final AndroidDriver driver;

    public HomePage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    WebElement countryDropDown;

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    WebElement nameField;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text=\"Female\"]")
    WebElement femaleButton;

    @AndroidFindBy(xpath="//android.widget.RadioButton[@text=\"Male\"]")
    WebElement maleButton;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    WebElement shopButton;

    @AndroidFindBy(xpath="(//android.widget.Toast)[1]")
    WebElement errorMsg;


    public void selectCountry(String country){
        waitForElement(By.id("com.androidsample.generalstore:id/spinnerCountry"));
        countryDropDown.click();
        scrollUntilView(country).click();
    }

    public void enterYourName(String name) {
        nameField.sendKeys(name);
        driver.hideKeyboard();
    }

    public void chooseGender(String gender){
        if(gender.equals("Male")){
            maleButton.click();
        }
        else femaleButton.click();
    }

    public ProductsPage clickOnShopButton() {
        driver.hideKeyboard();
        shopButton.click();
        return new ProductsPage(driver);
    }

    public String getErrorMsg(){
        //waitForElement(errorMsg);
        return errorMsg.getAttribute("name");
    }
}
