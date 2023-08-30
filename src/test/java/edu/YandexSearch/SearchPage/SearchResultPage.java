package edu.YandexSearch.SearchPage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.*;

public class SearchResultPage {
    private final List<SearchResult> searchResults = new ArrayList<>();

    SearchResultPage() {
        updateSearchResults();
    }

    private void updateSearchResults() {
        searchResults.clear();

        var results = Selenide.$$("#search-result > li");

        for(var result : results) {
            try {
                SelenideElement titleElement = result.$("#search-result > li > div > div.VanillaReact.OrganicTitle.OrganicTitle_multiline.Typo.Typo_text_l.Typo_line_m.organic__title-wrapper > a > h2 > span");
                String title = titleElement.getText();
                String url = result.$("#search-result > li > div > div.VanillaReact.OrganicTitle.OrganicTitle_multiline.Typo.Typo_text_l.Typo_line_m.organic__title-wrapper > a").getAttribute("href");
                String description = result.$("#search-result > li > div > div.Organic-ContentWrapper.organic__content-wrapper > div.TextContainer.OrganicText.organic__text.text-container.Typo.Typo_text_m.Typo_line_m > span > label > span.ExtendedText-Short").getText();

                searchResults.add(new SearchResult(title, description, url, titleElement));
            }
            catch (com.codeborne.selenide.ex.ElementNotFound e) {}
        }
    }

    public List<SearchResult> getResults() {
        return new ArrayList<>(searchResults);
    }

    public void clickOnSearchResult(int index) {
        searchResults.get(index).titleElement().click();
    }

    public SearchResultPage newSearch(String search) {
        Selenide.$("button[class='HeaderDesktopForm-Clear mini-suggest__input-clear']").click();
        Selenide.$("input[class='HeaderDesktopForm-Input mini-suggest__input']").setValue(search).pressEnter();

        updateSearchResults();

        return this;
    }
}
