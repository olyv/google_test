package com.olyv.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.util.function.Predicate.not;
import static java.util.stream.Collectors.toList;

public class HomePage extends PageObject {

    private final static String HOME_PAGE_URL = "http://google.com";
    private SearchInput searchInput;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(driver, this);
        searchInput = new SearchInput(driver);
    }

    public void openHomePage() {
        driver.get(HOME_PAGE_URL);
    }

    public void enterSearchCriterion(String word) {
        searchInput.enterText(word);
    }

    public List<String> getListOfSuggestedOptions() {
        return searchInput.getListOfSuggestedOptions().stream()
                .map(WebElement::getText)
                .filter(not(String::isEmpty))
                .collect(toList());
    }

    public void searchForWord(String word) {
        searchInput.enterText(word + Keys.RETURN);
    }
}
