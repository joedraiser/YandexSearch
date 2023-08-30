package edu.YandexSearch;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import edu.YandexSearch.SearchPage.SearchResult;
import edu.YandexSearch.SearchPage.YaPage;
import edu.YandexSearch.SearchPage.SearchResultPage;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
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
    static void initialSetUp() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(true));
        resultPage = new YaPage()
                .setCookies("is_gdpr=0; is_gdpr_b=CKWUcRDLbigC; _ym_uid=1650557319827735891; yuidss=9554715101657277214; yandexuid=5227944091650382118; my=YwA=; font_loaded=YSv1; gdpr=0; L=AH5TRWlKcgVOcgVSSGJZA3oHZgNZfGJBBCExMV00CT8Yews7DgEGKg==.1663145939.15100.329124.6e7cf5de29a2d7c22534db73ac216bc6; i=a8K13uYUlI5JRegKlLXk8vftAYE9kg0oUFtZY6EhIsxP3wG2CJFU+/pkAWpLsX6a3H2zL0Fspbu00PAg7PtcVDD4Yck=; yandex_gid=21611; yandex_login=; bh=EkEiQ2hyb21pdW0iO3Y9IjExNiIsICJOb3QpQTtCcmFuZCI7dj0iMjQiLCAiR29vZ2xlIENocm9tZSI7dj0iMTE2IhoFIng4NiIiECIxMTYuMC41ODQ1LjExMSIqAj8wMgIiIjoJIldpbmRvd3MiQggiMTAuMC4wIkoEIjY0IlJdIkNocm9taXVtIjt2PSIxMTYuMC41ODQ1LjExMSIsICJOb3QpQTtCcmFuZCI7dj0iMjQuMC4wLjAiLCAiR29vZ2xlIENocm9tZSI7dj0iMTE2LjAuNTg0NS4xMTEiWgI/MA==; yandex_csyr=1693303592; mda2_beacon=1693303592872; _ym_isad=2; cycada=LmL5WUR3LhcRVSwmeAwSeB7tRpSPGkC1ur8OUmA08RI=; yp=1709105869.szm.1:1920x1080:1920x963#4294967295.skin.s#2008676399.pcs.0#1695994328.hdrc.0; _yasc=ZK5pNX2wAgPD87y5cpIjCeQFCihjrifTTsVPi9ISUIpkTQNVnY8IelhUzU698GBX0bDEG2vVFkk=")
                .search("ВК Звонки");
        searchResults = resultPage.getResults();
    }

    @Test()
    @Description("Проверка что ВК Звонки выпадают первыми")
    void isFirst() {
        String firstSearchResultTitle = searchResults.get(0).title();
        assertEquals("VK Звонки: приложение для групповых видеоконференций", firstSearchResultTitle);
    }

    @Test
    @Description("Проверка что URL равен референсному значению")
    void isUrlValid() {
        String urlOfFirstSearchResult = searchResults.get(0).url();
        assertEquals("https://calls.vk.com/", urlOfFirstSearchResult);
    }

    @Test
    @Description("Проверка, что при открытии первого результата открывается верный сайт")
    void isLinkOperable() {
        resultPage.clickOnSearchResult(0);
        switchTo().window(1);
        String urlOpened = WebDriverRunner.currentFrameUrl();
        Selenide.closeWindow();
        switchTo().window(0);
        assertEquals("https://calls.vk.com/",urlOpened);
    }

    @Test
    @Description("Тест который всегда падает")
    void testThatAlwaysFail() {
        resultPage.newSearch("WhatsApp");
        Selenide.$("#search-result > li:nth-child(3)").shouldHave(Condition.exactText("VK Звонки: приложение для групповых видеоконференций"));
    }
}
