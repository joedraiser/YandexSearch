package edu.YandexSearch.SearchPage;

import com.codeborne.selenide.SelenideElement;

public record SearchResult(String title, String description, String url, SelenideElement titleElement) {
}
