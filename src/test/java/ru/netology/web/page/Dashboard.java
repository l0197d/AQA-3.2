package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class Dashboard {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public Dashboard() {
        heading.shouldBe(visible);
    }

}
