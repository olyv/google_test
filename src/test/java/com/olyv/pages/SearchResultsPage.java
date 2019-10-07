package com.olyv.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class SearchResultsPage extends AbstractPageObject {

    @FindBy(css = ".r > a:first-child")
    private List<WebElement> searchResultsLinks;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public Boolean isWordInPageTitle(String word) {
        var waitForPageTitleSeconds = 15;
        var wait = new WebDriverWait(driver, waitForPageTitleSeconds);
        return wait.until(ExpectedConditions.titleContains(word));
    }

    public List<String> getSearchResultsLinks() {
        return searchResultsLinks.stream()
                .map(element -> element.getAttribute("href"))
                .collect(toList());
    }
}
