package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import guru.qa.data.Locale;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class AeroflotTest {

    @BeforeAll
    static void configure() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;

    }

    static Stream<Arguments> aeroflotSiteButtonTestNewLocale(){
        return Stream.of(
                Arguments.of( Locale.EN, List.of("Buy a ticket", "Services",
                        "Special Offers", "Aeroflot Bonus", "Details", "For Business")),
                Arguments.of(Locale.RU, List.of("Купить билет", "Сервисы и услуги",
                        "Спецпредложения", "Аэрофлот Бонус", "Информация", "Для Бизнеса"))
        );
    }

    @DisplayName("Check change language button")
    @MethodSource("aeroflotSiteButtonTestNewLocale")
    @ParameterizedTest(name = "Проверка отображения названия кнопок для локали: {1}")
    void aeroflotSiteButtonTest(Locale locale, List<String> buttonsText){
        open("https://www.aeroflot.ru/ru-ru");
        $(".notification__content").$("button[type='button']").click();
        $(".main-module__header__selected-btn").click();
        $$(".main-module__wrapper").find(text(locale.name())).click();
        $(".main-module__button main-module__h-ml--auto main-module__js-dropdown-close").click();
        $$(".main-module__header__menu-wrapper").filter(visible)
                .shouldHave(CollectionCondition.texts(buttonsText));


    }
}
