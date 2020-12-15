package com.ngtesting.autotest.test.login;

import org.testng.*;
import org.testng.annotations.*;
import org.testng.log4testng.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Test(testName = "登录测试")
public class TestLogin implements ITest {
    private static final Logger log = Logger.getLogger(TestLogin.class);

    protected String myTestName = "";

    @Test(priority=1, testName = "登录成功")
    public void loginSuccess() {
        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
        }

        String title = "homepage";
        Assert.assertEquals(title , "homepage");
    }

    @Test(priority=2, dataProvider="userAccount")
    public void loginFail(String testName, String username, String password, String expectedTitle) {
        String actualTitle = "login";
        Assert.assertEquals(actualTitle , expectedTitle);
    }

    @DataProvider(name="userAccount")
    public Object[][] getUserAccountData() {
        return new Object[][] {
                {"密码错误", "admin", "pass1", "login" },
                {"账号过期", "tester", "pass", "login"},
        };
    }

    @Override
    public String getTestName() {
        String name = testName.get();
        return name;
    }

    @BeforeMethod(alwaysRun = true)
    public void customTestName(Method method, Object[] testData, ITestContext ctx) {
        if (testData.length > 0) {
            this.myTestName = testData[0].toString();
        } else {
            Annotation[] anno = method.getDeclaredAnnotationsByType(org.testng.annotations.Test.class);
            if (anno.length > 0) {
                this.myTestName = ((org.testng.annotations.Test)anno[0]).testName();
            } else {
                this.myTestName = method.getName();
            }
        }

        testName.set(this.myTestName);
    }

    private ThreadLocal<String> testName = new ThreadLocal<>();
}
