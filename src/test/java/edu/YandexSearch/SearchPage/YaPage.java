package edu.YandexSearch.SearchPage;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.open;

public class YaPage {
    private final ArrayList<Cookie> cookies = new ArrayList<>();

    public YaPage() {
        open("https://ya.ru");
    }

    public YaPage setCookies (String cookiesString, String domain) {
        Map<String,String> cookiesMap = new HashMap<>();

        for(var cookieString : cookiesString.split(";")) {
            cookiesMap.put(cookieString.substring(0, cookieString.indexOf('=')).trim(), cookieString.substring(cookieString.indexOf('=')+1).trim());
        }

        for(var key : cookiesMap.keySet()) {
            cookies.add(new Cookie(key, cookiesMap.get(key), domain, "/", null));
        }

        for (var cookie : cookies) {
            WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        }

        open("https://ya.ru");

        return this;
    }

    public YaPage setCookies (String cookiesString) {
        return setCookies(cookiesString, "ya.ru");
    }

    public SearchResultPage search(String search) {
        Selenide.$("#text").setValue(search).pressEnter();

        return new SearchResultPage();
    }
}
