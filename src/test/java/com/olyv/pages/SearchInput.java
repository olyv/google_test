package com.olyv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

class SearchInput extends AbstractPageObject {

    @FindBy(css = "input[name='q'][type='text']")
    private WebElement searchField;

    @FindBy(css = ".suggestions-inner-container")
    private List<WebElement> suggestedOptions;

    SearchInput(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    void enterText(String text) {
        searchField.sendKeys(text);
    }

    List<WebElement> getListOfSuggestedOptions() {
        return suggestedOptions;
    }
}
