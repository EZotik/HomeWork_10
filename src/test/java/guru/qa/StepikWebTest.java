package guru.qa;

import com.codeborne.selenide.Configuration;
import guru.qa.data.Language;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;


import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StepikWebTest {
    @BeforeEach
    void setUp(){
        Configuration.pageLoadStrategy = "eager";
        open("https://stepik.org/catalog");
    }

    @EnumSource(Language.class)
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectText(Language language) {
        $("button span.svg-icon").click();
        $(".menu_right").find(byText(language.getName())).click();
        $("h1.catalog-block__title").shouldHave(text(language.getDescription()));
    }


}
