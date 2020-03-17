package com.ngtesting.autotest.test;

import org.testng.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;
import org.testng.log4testng.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Test(testName = "登录测试")
public class TestLogin implements ITest {
    private static final Logger log = Logger.getLogger(TestLogin.class);

    protected String myTestName = "";

    @Test(priority=1, testName = "登录成功")
    public void loginSuccess() {
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
                {"账号过期", "tester", "pass", "login2"},
        };
    }

    public String getTestName() {
        return this.myTestName;
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

        ctx.setAttribute("testName", this.myTestName);
    }

//    @AfterMethod(alwaysRun = true)
//    public void setResultTestName(ITestResult result) {
//        log.warn("=== " + this.myTestName);
//
//        try {
//            BaseTestMethod baseTestMethod = (BaseTestMethod) result.getMethod();
//            Field f = baseTestMethod.getClass().getSuperclass().getDeclaredField("m_methodName");
//            f.setAccessible(true);
//            f.set(baseTestMethod, this.myTestName);
//        } catch (Exception e) {
//            Reporter.log("Exception : " + e.getMessage());
//        }
//    }
}
