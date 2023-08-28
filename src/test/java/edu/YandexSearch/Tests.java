package edu.YandexSearch;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import edu.YandexSearch.SearchPage.SearchResult;
import edu.YandexSearch.SearchPage.YaPage;
import edu.YandexSearch.SearchPage.SearchResultPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.codeborne.selenide.Selenide.switchTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    private static List<SearchResult> searchResults;
    private static SearchResultPage resultPage;

    @BeforeAll
    static void setUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        resultPage = new YaPage()
                .setCookies("yuidss=5277916001677014113; is_gdpr=0; is_gdpr_b=CIG7UxCAqAEoAg==; yandex_login=; yandexuid=1550811281676922472; _ym_uid=1677014114294366484; i=HUqWeMR70g4KaxY0gnEu30MO8IW4LWpp8blzJM660NvvpPPauTtP0BQF/X+dWL9iuV8zxU0ePZBRHlywjVC9maqIwsA=; my=YwA=; yandex_csyr=1692736857; mda2_beacon=1692736858055; bh=EkEiQ2hyb21pdW0iO3Y9IjExNiIsICJOb3QpQTtCcmFuZCI7dj0iMjQiLCAiR29vZ2xlIENocm9tZSI7dj0iMTE2IhoFIng4NiIiECIxMTYuMC41ODQ1LjExMSIqAj8wMgIiIjoJIldpbmRvd3MiQggiMTAuMC4wIkoEIjY0IlJdIkNocm9taXVtIjt2PSIxMTYuMC41ODQ1LjExMSIsICJOb3QpQTtCcmFuZCI7dj0iMjQuMC4wLjAiLCAiR29vZ2xlIENocm9tZSI7dj0iMTE2LjAuNTg0NS4xMTEiWgI/MA==; gdpr=0; _ym_isad=2; yandex_gid=21611; ys=wprid.1693141107789101-2233912026319909430-balancer-l7leveler-kubr-yp-sas-144-BAL-4137; cycada=EEpsjAyFuNANA3Vg6vNCAB7tRpSPGkC1ur8OUmA08RI=; _yasc=zVE2XpE1vrWFwKjMa9wka8Q0s7r/S8ZoWa13FKUCiLV5PC5CZutke1kmDhMdfJuP3XnQzmI8; yp=1708909822.szm.1:1920x1080:1920x963#1695725063.ygu.1#1695819801.csc.1#4294967295.skin.s#1695415283.hdrc.0#2008501108.pcs.0")
                .search("ВК Звонки");
        searchResults = resultPage.getResults();
    }

    @Test
    void isFirst() {
        String firstSearchResultTitle = searchResults.get(0).title();
        assertEquals("VK Звонки: приложение для групповых видеоконференций", firstSearchResultTitle);
    }

    @Test
    void isUrlValid() {
        String urlOfFirstSearchResult = searchResults.get(0).url();
        assertEquals("https://calls.vk.com/", urlOfFirstSearchResult);
    }

    @Test
    void isLinkOperable() {
        resultPage.clickOnSearchResult(0);
        switchTo().window(1);
        String urlOpened = WebDriverRunner.currentFrameUrl();
        Selenide.closeWindow();
        assertEquals("https://calls.vk.com/",urlOpened);
    }
}
