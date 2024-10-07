package app.GS.tests;

import app.GS.pageObjects.android.CartPage;
import app.GS.pageObjects.android.HomePage;
import app.GS.pageObjects.android.ProductsPage;
import app.GS.utils.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class General_Store_Test extends BaseTest {


    HomePage homePage;

    @BeforeMethod
    public void preSetup(){
        homePage = new HomePage((AndroidDriver) driver);
        homePage.openScreen("com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity");
    }

    @Test(description = "Verify purchase functionality by adding items to cart", groups = "Regression")
    public void selectForm() {

        homePage.selectCountry("Benin");
        homePage.enterYourName("AutoTest");
        homePage.chooseGender("Female");
        ProductsPage productsPage = homePage.clickOnShopButton();
        Assert.assertEquals(productsPage.verifyPageTitle(), "Products", "Invalid Page found!!");
        productsPage.clickOnAddToCart(0);
        productsPage.waitFewSeconds(3000);
        productsPage.clickOnAddToCart(0);
        CartPage cartPage = productsPage.clickOnCart();
        Assert.assertEquals(cartPage.verifyPageTitle(), "Cart");
        System.out.println(cartPage.getTotalAmount());
        cartPage.clickOnCheckBox();
        cartPage.clickOnPurchase();
        productsPage.waitFewSeconds(4000);
    }

    @Test(description = "Verify your name input field", groups = "Regression")
    public void verifyErrorMessage() {

        homePage.selectCountry("China");
        homePage.chooseGender("Male");
        homePage.clickOnShopButton();
        String actualErrorMsg = homePage.getErrorMsg();
        Assert.assertEquals(actualErrorMsg, "Please enter your name", "Invalid error message found!!");
    }

    @Test(description = "Verify Let's Shop functionality", groups = "Smoke")
    public void verifyLetsShopFunctionality() {

        homePage.selectCountry("Benin");
        homePage.enterYourName("AutoTest");
        homePage.chooseGender("Female");
        ProductsPage productsPage = homePage.clickOnShopButton();
        Assert.assertEquals(productsPage.verifyPageTitle(), "Products", "Invalid Page found!!");
        productsPage.waitFewSeconds(3000);
    }

}
