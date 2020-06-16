package com.ngtesting.autotest.test.apache;

import org.apache.commons.lang3.StringUtils;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Test(testName = "Test StringUtils")
public class TestStringUtils implements ITest {
    private static final Logger log = Logger.getLogger(TestStringUtils.class);

    @Test(priority=1, testName = "StringUtils space related functions")
    public void test1() {
        String str = " abc 123 ";

        str = StringUtils.trim(str);
        Assert.assertEquals(str , "abc 123");

        Assert.assertTrue(StringUtils.isEmpty(null));
        Assert.assertTrue(StringUtils.isBlank("   "));
    }

    @Test(priority=1, testName = "StringUtils letter related functions")
    public void test2() {
        String str = "abc";

        str = StringUtils.capitalize(str);
        Assert.assertEquals(str , "Abc");

        str = StringUtils.upperCase(str);
        Assert.assertEquals(str , "ABC");
    }

    protected String myTestName = "";
    public String getTestName() {
        return this.myTestName;
    }

    @BeforeMethod(alwaysRun = true)
    public void customTestName(Method method, Object[] testData, ITestContext ctx) {
        if (testData.length > 0) {
            this.myTestName = testData[0].toString();
        } else {
            Annotation[] anno = method.getDeclaredAnnotationsByType(Test.class);
            if (anno.length > 0) {
                this.myTestName = ((Test)anno[0]).testName();
            } else {
                this.myTestName = method.getName();
            }
        }

        ctx.setAttribute("testName", this.myTestName);
    }
}
