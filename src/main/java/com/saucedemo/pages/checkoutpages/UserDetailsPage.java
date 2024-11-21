package com.saucedemo.pages.checkoutpages;

import com.saucedemo.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserDetailsPage extends BasePage {
    @FindBy(id = "first-name")
    private WebElement firstNameFld;

    @FindBy(id = "last-name")
    private WebElement lastNameFld;

    @FindBy(id = "postal-code")
    private WebElement postalCodeFld;

    @FindBy(id = "continue")
    private WebElement continueBtn;

    public UserDetailsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter first name")
    public void enterFirstName(String firstName){
        firstNameFld.sendKeys(firstName);
    }

    @Step("Enter last name")
    public void enterLastName(String lastName){
        lastNameFld.sendKeys(lastName);
    }

    @Step("Enter post code {postalCode}")
    public void enterPostalCode(String postalCode){
        postalCodeFld.sendKeys(postalCode);
    }

    @Step("Click continue button and redirect to Overview Page")
    public OverviewPage clickContinueBtn(){
        continueBtn.click();
        return new OverviewPage(driver);
    }

    @Step("Fill user details")
    public void fillUserDetails(String firstName, String lastName, String postalCode){
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPostalCode(postalCode);
    }


}
