package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SimpleJUnitExampleTest {

    @Disabled("Jira project")//Дизейблит тест, не будет выполнятся
    @Test
    void simpleTest00(){

        open("https://www.coursera.org/");

    }

    @DisplayName("Можно указать что тестирует наш тест")
    @Test
    void simpleTest01(){

        open("https://www.coursera.org/");

    }

    @DisplayName("My test")
    @Test
    void simpleTest02(){

        open("https://www.coursera.org/");

    }

    @DisplayName("My test")
    @Test
    void simpleTest03(){

        open("https://www.coursera.org/");

    }
}
