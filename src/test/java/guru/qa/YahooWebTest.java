package guru.qa;
import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class YahooWebTest {

    @BeforeEach
    void setUp(){
        Configuration.pageLoadStrategy = "eager";
        open("https://ru.search.yahoo.com/");

    }

    @ValueSource (strings = {
          "xiaomi", "iPhon"
    })
    @ParameterizedTest(name ="Результат поискового запроса курсов по теме {0} должен быть непустым")
    @Tag("BLOCKER")
    void searchResultsShouldNotBeEmpty(String searchQuery) {
        $("#yschsp").setValue(searchQuery).pressEnter();
        $$(".dd.algo.algo-sr").shouldBe(CollectionCondition.sizeGreaterThan(0));

    }

    @CsvSource(value = {
            "xiaomi, www.mi.com",
            "iPhon, www.apple.com"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        $("#yschsp").setValue(searchQuery).pressEnter();
        $(".compTitle.options-toggle").shouldHave(text(expectedLink));

    }

    @CsvFileSource(resources = "/test_data/searchResultsShouldContainExpectedUrlOther.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultsShouldContainExpectedUrlOther(String searchQuery, String expectedLink) {
        $("#yschsp").setValue(searchQuery).pressEnter();
        $(".compTitle.options-toggle").shouldHave(text(expectedLink));

    }

    @Tag("BLOCKER")
    @DisplayName("Для поискового запроса 'iPhone' должен отдаваться не пустой список карточек")
    void successfulSearchIphoneTest() {

        $("#yschsp").setValue("iPhon").pressEnter();
        $$(".dd.algo.algo-sr").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

}

