package com.saucedemo.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    @FindBy(id = "user-name")
    private WebElement usernameFld;

    @FindBy(id = "password")
    private WebElement passwordFld;

    @FindBy(id = "login-button")
    private WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter username")
    public void enterUsername(String username){
        usernameFld.sendKeys(username);
    }

    @Step("Enter password")
    public void enterPassword(String password){
        passwordFld.sendKeys(password);
    }

    @Step("Click login button")
    public void clickLoginBtn(){
        loginBtn.click();
    }

    @Step("Login and redirect to Products Page")
    public ProductsPage loginSuccessful(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();

        return new ProductsPage(driver);
    }

}
