package tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomePageDisplayTest extends TestBase{

    @Test
    public void pageElementsTest(){
        step("Открываем сайт", () -> open(baseUrl));
        step("Проверяем наличие лого", () -> $("#middle-header-main-logo middle-mbs").isDisplayed());
        step("Проверяем наличие кнопки Каталог", () -> $("#button__catalog").isDisplayed());
        step("Проверяем наличие раздела Электроника", () -> $("#category-0").shouldHave(text("Электроника")));
        step("Проверяем наличие раздела Красота", () -> $("#category-5").shouldHave(text("Красота")));
        step("Проверяем наличие раздела Ещё", () -> $(".more").shouldHave(text("Ещё")));
        step("Проверяем наличие баннера", () -> $(".banner-block").isDisplayed());

        ElementsCollection razdel = $$(".title-text");
        step("Проверяем, что разделов на странице больше 0", () -> assertTrue(razdel.size() > 0));
        step("Проверяем наличие раздела Популярное", () -> razdel.get(0).shouldHave(text("Популярное")));

        ElementsCollection cards = $$(".ui-card");
        step("Проверяем, что на странице есть карточки товаров", () -> assertTrue(cards.size() > 0));

        ElementsCollection buttons = $$("i.hidden-mbs");
        step("Проверяем наличие кнопки Войти", () -> buttons.get(0).shouldHave(text("Войти")));
        step("Проверяем наличие кнопки Избранное", () -> buttons.get(1).shouldHave(text("Избранное")));
        step("Проверяем наличие кнопки Корзина", () -> buttons.get(2).shouldHave(text("Корзина")));
    }
}
