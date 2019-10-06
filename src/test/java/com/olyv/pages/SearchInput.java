package com.olyv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

class SearchInput extends PageObject {

    @FindBy(css = "input[name='q'][type='text']")
    private WebElement searchInput;

    @FindBy(css = ".suggestions-inner-container")
    private List<WebElement> suggestedOptions;

    SearchInput(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    void enterText(String text) {
        searchInput.sendKeys(text);
    }

    List<WebElement> getListOfSuggestedOptions() {
//        var wait = new WebDriverWait(driver, 30);
//        return wait.until(refreshed(visibilityOfAllElements(suggestedOptions)));
        return suggestedOptions;
    }
}
