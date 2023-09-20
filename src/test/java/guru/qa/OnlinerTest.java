package guru.qa;

import guru.qa.data.Onliner;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class OnlinerTest {

    static Stream<Arguments> OnlinerDataProvider() {
        return Stream.of(
                Arguments.of(Onliner.Телевизоры, List.of("Телевизор Xiaomi TV Q2 55\" (международная версия)", "Телевизор Xiaomi TV Q2 50\" (международная версия)",
                        "Телевизор Xiaomi Mi TV A2 55\" (международная версия)", "Телевизор Xiaomi Mi TV A2 43\" (международная версия)", "Телевизор Xiaomi Mi TV A2 50\" (международная версия)", "Телевизор Xiaomi Mi TV A2 32\" (международная версия)", "OLED телевизор LG B3 OLED65B3RLA")),
                Arguments.of(Onliner.Смартфоны, List.of("Смартфон HONOR 90 8GB/256GB международная версия (арктический лед)", "Смартфон HONOR 90 12GB/512GB международная версия (полночный черный)", "Смартфон HONOR 90 8GB/256GB международная версия (изумрудный зеленый)", "Смартфон HONOR 90 8GB/256GB международная версия (полночный черный)",
                        "Смартфон HONOR 90 Lite 8GB/256GB международная версия (небесный голубой)", "Смартфон HONOR 90 Lite 8GB/256GB международная версия (полночный черный)", "Смартфон HONOR 90 Lite 8GB/256GB международная версия (титановый серебристый)"))
        );
    }
    @MethodSource("OnlinerDataProvider")
    @ParameterizedTest(name = "Для категории {0} отображаются товары {1}")
    void onlinerSiteShouldContainCertainGoods(
            Onliner onliner,
            List<String> goods
    ) {
        open("https://www.onliner.by/");
        $$(".project-navigation__item.project-navigation__item_secondary").find(text(onliner.name())).click();
        $$(".schema-product__title a").first(7).shouldHave(texts(goods));
    }
}
