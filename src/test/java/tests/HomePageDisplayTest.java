package tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class HomePageDisplayTest extends TestBase {

    ElementsCollection headerButtons = $$("i.hidden-mbs");
    ElementsCollection cards = $$(".ui-card");
    ElementsCollection sections = $$(".title-text");

    @BeforeEach
    public void setUp() {
        step("Открываем сайт", () -> open(baseUrl));
    }

    @Test
    public void pageElementsTest() {
        step("Проверяем наличие лого", () -> $("#middle-header-main-logo middle-mbs").isDisplayed());
        step("Проверяем наличие кнопки Каталог", () -> $("#button__catalog").isDisplayed());
        step("Проверяем наличие раздела Электроника", () -> $("#category-0").shouldHave(text("Электроника")));
        step("Проверяем наличие раздела Красота", () -> $("#category-5").shouldHave(text("Красота")));
        step("Проверяем наличие раздела Ещё", () -> $(".more").shouldHave(text("Ещё")));
        step("Проверяем наличие баннера", () -> $(".banner-block").isDisplayed());

        step("Проверяем, что разделов на странице больше 0", () -> assertTrue(sections.size() > 0));
        step("Проверяем наличие раздела Популярное", () -> sections.get(0).shouldHave(text("Популярное")));

        step("Проверяем наличие раздела Популярное", () -> sections.get(0).shouldHave(text("Популярное")));
        step("Проверяем, что на странице есть карточки товаров", () -> assertTrue(cards.size() > 0));

        step("Проверяем наличие кнопки Войти", () -> headerButtons.get(0).shouldHave(text("Войти")));
        step("Проверяем наличие кнопки Избранное", () -> headerButtons.get(1).shouldHave(text("Избранное")));
        step("Проверяем наличие кнопки Корзина", () -> headerButtons.get(2).shouldHave(text("Корзина")));
    }

    @Test
    public void likeCardWithIssueTest() {
        step("Жмем лайк карточке товара", () -> $x("(//*[name()='path'])[99]").parent().click());
        step("Переходим в Избранное", () -> headerButtons.get(1).click());
        step("Проверяем, что в Избранном есть карточка лайкнутого товара", () -> assertTrue(cards.size() > 0));
    }

    @Test
    public void simpleAddIssueToBagTest() {
        step("Переводим ко второму ряду карточек", () -> cards.get(5).isDisplayed());
        step("Кладем товар в корзину", () -> $x("(//*[name()='path'])[96]").parent().shouldBe(appear).click());
        step("Переходим в Корзину", () -> headerButtons.get(2).click());
        step("Проверяем, что в Корзине есть карточка товара", () -> assertTrue($$(".cart-item-wrapper").size() > 0));
    }

}
