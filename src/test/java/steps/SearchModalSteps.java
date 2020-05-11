package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.FeedPage;
import pages.SearchModalPage;

public class SearchModalSteps {
    private SearchModalPage searchModalPage;
    private FeedPage feedPage;

    public SearchModalSteps(WebDriver driver) {
        searchModalPage = new SearchModalPage(driver);
        feedPage = new FeedPage(driver);
    }

    @Step("Opening page and checking that page has been opened")
    public SearchModalSteps openPage() {
        feedPage
                .openPage();
        searchModalPage
                .isPageOpened();
        return this;
    }

    @Step("Write text {textForSearch} and click search button")
    public SearchModalSteps clickAndSearch(String textForSearch) {
        searchModalPage
                .clickInput()
                .writeText(textForSearch)
                .clickSearch();
        return this;
    }

    @Step("Create list of entries and check it contains necessary text {textForSearch}")
    public SearchModalSteps createListOfEntriesAndCheckText(String textForSearch) {
        searchModalPage
                .checkListOfEntriesContainsSearchText(textForSearch);
        return this;
    }
}