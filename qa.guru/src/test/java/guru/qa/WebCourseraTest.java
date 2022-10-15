package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class WebCourseraTest {
    @BeforeAll
    static void configure(){
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
        Configuration.browser = "chrome";
        //Configuration.baseUrl = "";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
    }

    @DisplayName("Web site test")
    @Test
    void courseraFirstPage(){
        open("https://www.kinopoisk.ru/");
        $(byName("kp_query")).setValue("дже").pressEnter();
        //$(".js-serp-metrika").click();
        $(".flap_img").click();
        //Check result
        //$$("").shouldHave(CollectionCondition.size(12));
        $(".styles_subscriptionSubtext__zQuOI")
                .shouldHave(text("30 дней бесплатно, затем 299 ₽ в месяц"));
    }


}
