package com.saucedemo.pages;

import com.saucedemo.pages.checkoutpages.UserDetailsPage;
import com.saucedemo.pages.components.Navbar;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {
    @FindBy(className = "cart_item")
    private List<WebElement> products;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingBtn;
    @FindBy(id = "checkout")
    private WebElement checkoutBtn;

    private final Navbar navbar;

    public CartPage(WebDriver driver) {
        super(driver);
        navbar = new Navbar(driver);
    }

    public Navbar getNavbar() {
        return navbar;
    }

    public List<WebElement> getProducts() {
        return products;
    }

    @Step("Click continue shopping button and redirect to Products Page")
    public ProductsPage clickContinueShoppingBtn() {
        continueShoppingBtn.click();
        return new ProductsPage(driver);
    }

    @Step("Click checkout button and redirect to User Details Page")
    public UserDetailsPage clickCheckoutBtn() {
        checkoutBtn.click();
        return new UserDetailsPage(driver);
    }


}
