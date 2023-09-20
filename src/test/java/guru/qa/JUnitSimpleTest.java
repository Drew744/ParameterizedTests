package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import guru.qa.data.Locale;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class JUnitSimpleTest {

    @DisplayName("Демонстрационный тест") //Такое имя может быть передано в Allure
    @Test
    void simpleTest() {
        Assertions.assertTrue(3 > 2);
    }

    @BeforeEach
    void setUp() { //Аналог предусловий. Шаг, который будет выполняться перед каждым тестом
        open("https://google.com/");
    }

    @Disabled //Отключение выполнения теста
    @DisplayName("Адрес https://ru.selenide.org должен быть в выдаче гугла по запросу 'selenide'")
    @Test
    @Tags({@Tag("Blocker"), @Tag("UI_Test")}) // Теги для удобного поиска по тестам
    void successfulSelenideSearchTest() {

        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920*1080";
        Configuration.browser = "chrome";

        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://ru.selenide.org"));
    }

    @CsvFileSource(resources = "/testData.csv") //Путь к файлу со значениями
    @ParameterizedTest(name=" URL {1} should be present in google by query {0}")
    @Tags({@Tag("Blocker"), @Tag("UI_Test")})
    void successfulSearchByTextQueryTest(
            String productName,
            String productUrl
    ) {
        $("[name=q]").setValue(productName).pressEnter();
        $("[id=search]").shouldHave(text(productUrl));
    }

    @ValueSource(
            strings = {"allure testops", "selenide"}
    )

    @ParameterizedTest(name = "Check the number of search results for {0} query")
    @Tags({@Tag("Blocker"), @Tag("UI_Test")})
    void searchResultsCountTest(String productName) {
        $("[name=q]").setValue(productName).pressEnter();
        $$("div[class='g']").shouldHave(CollectionCondition.sizeGreaterThan(5));
    }

}