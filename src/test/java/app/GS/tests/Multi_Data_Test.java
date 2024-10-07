package app.GS.tests;

import app.GS.utils.ReadConfig;
import app.GS.pageObjects.android.CartPage;
import app.GS.pageObjects.android.HomePage;
import app.GS.pageObjects.android.ProductsPage;
import app.GS.utils.BaseTest;
import io.appium.java_client.android.AndroidDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Multi_Data_Test extends BaseTest {

    HomePage homePage;

    @BeforeMethod
    public void preSetUp() {
        homePage = new HomePage((AndroidDriver) driver);
        homePage.openScreen("com.androidsample.generalstore/com.androidsample.generalstore.SplashActivity");
    }

    @Test(description = "Verify total amount with multi-data set's", dataProvider = "getData", groups = "Regression")
    public void verifyTotalAmount_multipleDataTest(HashMap<String, String> input) {

        homePage.selectCountry(input.get("country"));
        homePage.enterYourName(input.get("name"));
        homePage.chooseGender(input.get("gender"));
        ProductsPage productsPage = homePage.clickOnShopButton();
        Assert.assertEquals(productsPage.verifyPageTitle(), "Products", "Invalid Page found!!");
        productsPage.clickOnAddToCart(0);
        productsPage.waitFewSeconds(3000);
        productsPage.clickOnAddToCart(0);
        CartPage cartPage = productsPage.clickOnCart();
        Assert.assertEquals(cartPage.verifyPageTitle(), "Cart");
        System.out.println(cartPage.getTotalAmount());
        productsPage.waitFewSeconds(2000);
    }

    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = ReadConfig.getJsonData("Multi_Data_Test");
        Object[][] dataArray = new Object[data.size()][1];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i][0] = data.get(i);
        }
        return dataArray;
    }
}
