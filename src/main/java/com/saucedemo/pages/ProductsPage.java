package com.saucedemo.pages;

import com.saucedemo.pages.components.Navbar;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductsPage extends BasePage {
    @FindBy(className = "inventory_item")
    private List<WebElement> products;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLnk;

    @FindBy(className = "shopping_cart_badge")
    private WebElement addedProductsCount;

    @FindBy(className = "product_sort_container")
    private WebElement sortingDropdown;

    @FindBy(className = "inventory_item_price")
    private List<WebElement> prices;

    @FindBy(xpath = "//button[contains(text(), 'Remove')]/ancestor::div[@class='inventory_item']")
    private List<WebElement> selectedProducts;

    private Navbar navbar;

    public ProductsPage(WebDriver driver) {
        super(driver);
        navbar = new Navbar(driver);
    }

    public List<WebElement> getSelectedProducts() {
        return selectedProducts;
    }

    public int getProductsCount() {
        return products.size();
    }

    @Step("Open shopping cart page")
    public CartPage clickCartLnk() {
        return navbar.clickCartLnk();
    }

    @Step("Open sorting options menu")
    public void clickSortingDropdown() {
        sortingDropdown.click();
    }

    @Step("Sort products by given criteria")
    public void sortProductsBy(String text) {
        clickSortingDropdown();
        Select select = new Select(sortingDropdown);
        select.selectByVisibleText(text);
    }

    @Step("Get the prices of all products")
    public List<Double> getProductPrices() {

        List<Double> pricesList = new ArrayList<>();

        for (WebElement priceEl : prices) {
            String substringPrice = priceEl.getText().replace("$", "").trim();
            Double productPrice = Double.parseDouble(substringPrice);
            pricesList.add(productPrice);
        }

        return pricesList;
    }

    @Step("Remove product from shopping cart")
    public void clickRemoveProductBtn(int index) {
        products.get(index)
                .findElement(By.xpath(".//button[contains(@id, 'remove')]"))
                .click();
    }

    @Step("Add product to shopping cart")
    public void clickAddProductBtn(int index) {
        products.get(index)
                .findElement(By.xpath(".//button[contains(@id, 'add-to-cart')]"))
                .click();
    }
}
