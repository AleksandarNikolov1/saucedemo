package com.saucedemo.pages.checkoutpages;

import com.saucedemo.pages.BasePage;
import com.saucedemo.pages.components.Navbar;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CompletePage extends BasePage {

    @FindBy(className = "complete-header")
    private WebElement confirmationMessage;

    private final Navbar navbar;

    public CompletePage(WebDriver driver) {
        super(driver);
        navbar = new Navbar(driver);
    }

    @Step("Verify confirmation message for order complete")
    public String getConfirmationMessage() {
        return confirmationMessage.getText();
    }

    @Step("Click shopping cart link and redirect to Cart Page")
    public void clickShoppingCartLnk() {
        navbar.clickCartLnk();
    }
}
