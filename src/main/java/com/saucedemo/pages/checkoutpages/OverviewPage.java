package com.saucedemo.pages.checkoutpages;

import com.saucedemo.pages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewPage extends BasePage {

    @FindBy(id = "finish")
    private WebElement finishBtn;

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click finish button and redirect to checkout final page")
    public CompletePage clickFinishBtn(){
        finishBtn.click();
        return new CompletePage(driver);
    }

}
