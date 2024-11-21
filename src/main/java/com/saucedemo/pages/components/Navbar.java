package com.saucedemo.pages.components;

import com.saucedemo.pages.BasePage;
import com.saucedemo.pages.CartPage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Navbar extends BasePage {
    @FindBy(id = "react-burger-menu-btn")
    private WebElement burgerMenu;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logoutLnk;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartLnk;

    public Navbar(WebDriver driver) {
        super(driver);
    }


    @Step("Click logout link")
    public void logout(){
        openSideBarMenu();
        logoutLnk.click();
    }

    @Step("Open side bar menu")
    public void openSideBarMenu(){
        burgerMenu.click();
    }

    @Step("Click cart link and redirect to Cart Page")
    public CartPage clickCartLnk(){
        cartLnk.click();
        return new CartPage(driver);
    }
}
