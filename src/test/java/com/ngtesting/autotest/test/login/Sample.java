package com.ngtesting.autotest.test.login;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

@Test(testName = "登录测试")
public class Sample {
    private static final Logger log = Logger.getLogger(Sample.class);

    @Test(priority=1, testName = "登录成功", dataProvider="userAccount")
    public void loginSuccess(String testName, String username, String password, String expectedTitle) {
        // 此处完成登录操作 ...

        // 获取页面标题
        String actualTitle = "首页";

        // 页面标题验证
        Assert.assertEquals(actualTitle , expectedTitle);
    }

    @DataProvider(name="userAccount")
    public Object[][] getUserAccountData() {
        return new Object[][] {
                {"成功登陆", "admin", "pass", "首页" },
                {"密码错误", "admin", "pass1", "登录" },
                {"账号过期", "tester", "pass", "登录"},
        };
    }
}
