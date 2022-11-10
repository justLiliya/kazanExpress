package tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class AddIssueIntoBagTest extends TestBase{

    ElementsCollection headerButtons = $$("i.hidden-mbs");
    ElementsCollection cards = $$(".ui-card");
    ElementsCollection sections = $$(".title-text");

    @BeforeEach
    public void setUp() {
        step("Открываем сайт", () -> open(baseUrl));
    }

    @Test
    public void addTest() {
        step("Проверяем наличие раздела Популярное", () -> sections.get(0).shouldHave(text("Популярное")));
        step("Переводим ко второму ряду карточек", () -> cards.get(4).isDisplayed());

        step("Кладем товар в корзину", () -> $x("(//*[name()='path'])[98]").parent().shouldBe(appear).click());
        step("Проверяем наличие модалки", () -> $(".content.slideUp").is(visible));
        $(byText("Добавить в корзину")).click();

    }

}
