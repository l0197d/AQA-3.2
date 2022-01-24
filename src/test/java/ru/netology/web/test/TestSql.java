package ru.netology.web.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.Dashboard;
import ru.netology.web.page.Login;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.web.data.DBQuery.*;
import static ru.netology.web.data.DataHelper.*;

public class TestSql {
    private Dashboard dashboardPage;
    private Login loginPage;


    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldLogin() {
        var user = getAuthInfo();
        var login = new Login().validLogin(user);
        dashboardPage = login.validVerify(getVerificationCode(user.getLogin()));
    }

    @Test
    void shouldBlockWrongPassword() {
        var wrongPass = new AuthInfo(getAuthInfo().getId(), getAuthInfo().getLogin(), "123qwerty");
        loginPage = new Login();
        loginPage.login(wrongPass);
        loginPage.error();
        loginPage.login(wrongPass);
        loginPage.error();
        loginPage.login(wrongPass);
        loginPage.error();
        String actualStatus = getUserStatus(getAuthInfo().getLogin());
        assertEquals("blocked", actualStatus);
    }

    @AfterAll
    static void cleanDB() {
        deleteFromDB();
    }

}
