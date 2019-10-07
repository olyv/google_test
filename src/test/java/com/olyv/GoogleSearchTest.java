package com.olyv;

import com.olyv.driver.ContainerFactory;
import com.olyv.pages.HomePage;
import com.olyv.pages.SearchResultsPage;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.BrowserWebDriverContainer;

import static org.assertj.core.api.Assertions.assertThat;

public class GoogleSearchTest {

    private static final String SOME_WORD = "wortschatz";
    private static final String SOME_SITE_TO_SEARCH = "https://play.google.com";
    private static final Logger LOG = LoggerFactory.getLogger(GoogleSearchTest.class);
    private RemoteWebDriver driver;
    private HomePage homePage;
    private SearchResultsPage searchResultsPage;

    @Rule
    public BrowserWebDriverContainer container = new ContainerFactory().getContainer();

    @Before
    public void setUp() {
        LOG.info("Container is running at {}", container.getVncAddress());
        driver = container.getWebDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void whenEnterCriterionThenListOfOptionsIsSuggested() {
        givenOnHomePage();
        whenEnterSearchCriterion(SOME_WORD);
        thenEachSuggestionStartsWithCriterion(SOME_WORD);
    }

    @Test
    public void whenSearchForWordThenPageTitleStartsWithSearchedWord() {
        givenOnHomePage();
        whenSearchForWord(SOME_WORD);
        thenPageTitleStartsWithWord(SOME_WORD);
    }

    @Test
    public void whenSearchForWordOnSpecificSiteThenFirstResultIsLinkToSite() {
        var someCriterionWithSite = String.format("site:%s %s", SOME_SITE_TO_SEARCH, SOME_WORD);
        givenOnHomePage();
        whenSearchForWord(someCriterionWithSite);
        thenSearchResultsLinksPointToSite(SOME_SITE_TO_SEARCH);
    }

    private void givenOnHomePage() {
        homePage = new HomePage(driver);
        homePage.openHomePage();
    }

    private void whenSearchForWord(String someCriterion) {
        homePage.searchForWord(someCriterion);
    }

    private void whenEnterSearchCriterion(String criterion) {
        homePage.enterSearchCriterion(criterion);
    }

    private void thenEachSuggestionStartsWithCriterion(String criterion) {
        var options = homePage.getListOfSuggestedOptions();
        assertThat(options).allMatch(it -> it.startsWith(criterion));
    }

    private void thenSearchResultsLinksPointToSite(String site) {
        searchResultsPage = new SearchResultsPage(driver);
        var searchResultsLinks = searchResultsPage.getSearchResultsLinks();
        assertThat(searchResultsLinks).allMatch(link -> link.startsWith(site));
    }

    private void thenPageTitleStartsWithWord(String word) {
        searchResultsPage = new SearchResultsPage(driver);
        assertThat(searchResultsPage.isWordInPageTitle(word));
    }
}
