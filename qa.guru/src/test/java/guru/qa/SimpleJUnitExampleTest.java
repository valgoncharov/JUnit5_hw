package guru.qa;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class SimpleJUnitExampleTest {

    @Disabled("Jira project")
    @Test
    void simpleTest00(){

        open("https://www.coursera.org/");

    }
}
