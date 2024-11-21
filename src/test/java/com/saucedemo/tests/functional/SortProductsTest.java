package com.saucedemo.tests.functional;

import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import com.saucedemo.tests.BaseTest;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static com.saucedemo.testsdata.ConstantData.*;
import static org.testng.AssertJUnit.assertEquals;

@Feature("Sorting products by given criteria")
public class SortProductsTest extends BaseTest {

    @Test(groups = {"functional"})
    public void testSortProductsByPriceAsc() {
        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = loginPage.loginSuccessful(USERNAME, PASSWORD);

        List<Double> rawProductPrices = productsPage.getProductPrices();

        productsPage.sortProductsBy(SORT_BY_PRICE_ASC);
        List<Double> sortedProductPrices = productsPage.getProductPrices();

        Collections.sort(rawProductPrices);

        assertEquals(rawProductPrices, sortedProductPrices);
    }
}
