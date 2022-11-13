package tests;

import com.codeborne.selenide.ElementsCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddIssueIntoBagTest extends TestBase{

    ElementsCollection cards = $$(".ui-card");
    ElementsCollection colors = $$("div.image.radio-item.regular[data-test-id='item__sku']");



    @BeforeEach
    public void setUp() {
        step("Открываем сайт", () -> open(baseUrl));
    }

    @Test
    public void formOfProductTest() {
        step("Кликаем на карточку товара и переходим в форму товара", () -> cards.get(2).click());
        step("Проверяем наличие главного фото товара", ()  -> $("#item__main-photo").is(visible));
        step("Проверяем наличие фото в вертикальной карусели", () ->  assertTrue($$("#list__vertical-photo-carousel").size()>0));
        step("Проверяем наличие названия товара", () -> $("h1.title").shouldBe(visible));
        step("Параметр продавец", () -> $(byText("Продавец:")).shouldBe(visible));
        step("Параметр доставка", () -> $(byText("Доставка:")).shouldBe(visible));
        step("Продавец товара", () -> $("a.link__product-seller").is(visible));
        step("Кликаем на тултип", () -> $("#icon-help").click());
        step("Проверяем, что открывается окно с подсказкой, сверяем текст", () -> $("[toltip-text='']").shouldHave(text("Мы доставляем товары на следующий день после заказа до собственных пунктов выдачи. Выберите на этапе оформления заказа наиболее удобный для вас адрес.")));
        step("Закрываем тултип", () -> $("#icon-help").click());
        step("Параметр цвет", () -> $(byText("Цвет:")).shouldBe(visible));
        step("Проверяем наличие хотя бы одной миниатюры с цветом", () -> assertTrue(colors.size() >0));
        step("Параметр количество", () -> $(byText("Количество:")).shouldBe(visible));
        step("Проверяем наличие поля для ввода количества", () -> $("[data-test-id='input__quantity']").shouldBe(visible));
        step("Параметр Цена", () -> $(byText("Цена:")).shouldBe(visible));
        step("Рубли", () ->$("span.currency").is(visible));
        step("Выбираем цвет", () ->colors.get(1).click());
        step("Нажать добавить в корзину", () ->$("button.add-to-cart.noselect.solid.tall.wide[data-test-id='button__add-cart'][ui-button='']").click());
    }

    @Test
    public void addProductIntoBagTest(){
        step("Кликаем на карточку товара и переходим в форму товара", () -> cards.get(2).click());
        step("Выбираем цвет", () ->colors.get(1).click());
        step("Нажать добавить в корзину", () ->$("button.add-to-cart.noselect.solid.tall.wide[data-test-id='button__add-cart'][ui-button='']").click());
        step("Toast", () ->$("div.product-added-to-cart-container").shouldBe(visible));
        step("Нажать в тосте кнопку Перейти в корзину", () ->$("a.medium")).shouldHave(text("Перейти в корзину")).click();
    }



}
