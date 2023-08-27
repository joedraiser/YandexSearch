package edu.YandexSearch.SearchPage;

import com.codeborne.selenide.SelenideElement;

public record SearchResult(String title, String description, String url, SelenideElement titleElement) {
    @Override
    public String toString() {
        return "SearchResult{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
