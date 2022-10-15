package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import guru.qa.data.Menu;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;

public class KinopoiskTest {
    @BeforeAll
    static void configure(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        Configuration.browser = "chrome";
        //Configuration.baseUrl = "https://www.kinopoisk.ru/";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @Disabled
    @DisplayName("Check free month for use and next month for 299 rub")
    @ValueSource(strings = {"джентельмены", "триггер"})
    @ParameterizedTest(name = "Проверка подписки за 299 Р в месяц {0}")
    void checkKinopoiskPayTest(String testData){
        open("https://www.kinopoisk.ru");
        $(byName("kp_query")).setValue(testData).pressEnter();
        $(".flap_img").click();
        $(".styles_subscriptionSubtext__zQuOI")
                .shouldHave(text("30 дней бесплатно, затем 299 ₽ в месяц"));
    }

    @DisplayName("Check pay for mouth")
    @CsvSource(value = {"джентельмены | 30 дней бесплатно, затем 299 ₽ в месяц",
                        "триггер | 30 дней бесплатно, затем 299 ₽ в месяц"},
                delimiter = '|'
    )
    @ParameterizedTest(name = "Проверка подписки за 299 Р в месяц {0}")
    void checkKinopoiskTest(String searchQuery, String expectedText){
        open("https://www.kinopoisk.ru");
        $(byName("kp_query")).setValue(searchQuery).pressEnter();
        $(".flap_img").click();
        $(".styles_subscriptionSubtext__zQuOI")
                .shouldHave(text(expectedText));
    }

    static Stream<Arguments> kinopoiskSiteTestList(){
        return Stream.of(
                Arguments.of( Menu.Фильмы, List.of("250 лучших фильмов", "Лучшие фильмы 2021 года: выбор редакции",
                        "500 лучших фильмов", "Популярные фильмы",
                        "Цифровые релизы", "Фильмы про вампиров")),
                Arguments.of(Menu.Годы, List.of("2022 год", "2021 год", "2020 год"))
//
//                        "250 лучших сериалов", "Популярные сериалы",
//                        "Лучшие сериалы 2021 года: выбор редакции", "Все сериалы онлайн",
//                        "Цифровые релизы", "Российские сериалы"))
        );
    }

    @DisplayName("Check list film")
    @MethodSource("kinopoiskSiteTestList")
    @ParameterizedTest(name = "Проверка списка фильмов {1}")
    void kinopoiskTestList(Menu menu, List<String> listMenu){
        open("https://www.kinopoisk.ru/lists/categories/movies/");
        $$(".styles_container__TJkuX.styles_categories__ly3pq").findBy(text(menu.name())).click();
        $(".styles_content__2mO6X").shouldHave(text(String.valueOf(listMenu)));
    }

    @DisplayName("Check list film and serial")
    @EnumSource(Menu.class)
    @ParameterizedTest(name = "Проверка отображения кнопок на странице {1}")
    void kinopoiskButtonList(Menu menu){
         open("https://www.kinopoisk.ru/lists/categories/movies/");
         $$(".styles_container__TJkuX.styles_categories__ly3pq")
                 .find(text(menu.name())).shouldBe(visible);
    }
}
