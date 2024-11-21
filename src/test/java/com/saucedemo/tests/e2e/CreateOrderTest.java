package com.saucedemo.tests.e2e;

import com.saucedemo.pages.*;
import com.saucedemo.pages.checkoutpages.CompletePage;
import com.saucedemo.pages.checkoutpages.OverviewPage;
import com.saucedemo.pages.checkoutpages.UserDetailsPage;
import com.saucedemo.tests.BaseTest;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.saucedemo.testsdata.ConstantData.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class CreateOrderTest extends BaseTest {

    @Test(groups = {"e2e"})
    public void testCreateOrder() {

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginSuccessful(USERNAME, PASSWORD);

        assertTrue(productsPage.getProductsCount() > 1);

        int firstProductIndex = 0;
        int lastProductIndex = productsPage.getProductsCount() - 1;
        int penultimateProductIndex = productsPage.getProductsCount() - 2;

        productsPage.clickAddProductBtn(firstProductIndex);
        productsPage.clickAddProductBtn(lastProductIndex);

        List<String> selectedProductDetails = getProductDetails(productsPage.getSelectedProducts());

        CartPage cartPage = productsPage.clickCartLnk();

        List<String> cartProductDetails = getProductDetails(cartPage.getProducts());

        assertTrue(isCartContentMatching(selectedProductDetails, cartProductDetails));

        productsPage = cartPage.clickContinueShoppingBtn();

        productsPage.clickRemoveProductBtn(firstProductIndex);
        productsPage.clickAddProductBtn(penultimateProductIndex);

        selectedProductDetails = getProductDetails(productsPage.getSelectedProducts());

        productsPage.clickCartLnk();

        cartProductDetails = getProductDetails(cartPage.getProducts());

        Collections.reverse(cartProductDetails);

        assertTrue(isCartContentMatching(selectedProductDetails, cartProductDetails));

        UserDetailsPage userDetailsPage = cartPage.clickCheckoutBtn();

        userDetailsPage.fillUserDetails(FIRST_NAME, LAST_NAME, POST_CODE);

        OverviewPage overviewPage = userDetailsPage.clickContinueBtn();

        CompletePage completePage = overviewPage.clickFinishBtn();

        assertEquals(completePage.getConfirmationMessage(), ORDER_CONFIRMATION_MESSAGE);

        completePage.clickShoppingCartLnk();

        assertEquals(0, cartPage.getProducts().size());

        cartPage.getNavbar().logout();
    }

    @Step("Verify that the shopping cart page contains all added products")
    private boolean isCartContentMatching(List<String> expectedProducts,
                                          List<String> actualProducts) {

        if (expectedProducts.size() != actualProducts.size()) {
            return false;
        }

        return expectedProducts.equals(actualProducts);
    }

    private List<String> getProductDetails(List<WebElement> products){
        return products.stream()
                .map(this::getProductInfo)
                .collect(Collectors.toList());
    }

    private String getProductInfo(WebElement product){
        String title = product.findElement(By.className("inventory_item_name")).getText();
        String desc = product.findElement(By.className("inventory_item_desc")).getText();
        String price = product.findElement(By.className("inventory_item_price")).getText();

        return title + " | " + price + " | " + desc;
    }
}
