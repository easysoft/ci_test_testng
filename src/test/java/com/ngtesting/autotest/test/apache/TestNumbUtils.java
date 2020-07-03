package com.ngtesting.autotest.test.apache;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

@Test(testName = "Test NumberUtils")
public class TestNumbUtils implements ITest {
    private static final Logger log = Logger.getLogger(TestNumbUtils.class);

    @Test(priority=1, testName = "Convert")
    public void test1() {
        String str = "123";

        Assert.assertTrue(NumberUtils.isDigits("123"));
        Assert.assertTrue(NumberUtils.toInt("123") == 123);
    }

    @Test(priority=1, testName = "Compare")
    public void test2() {
        Assert.assertEquals( NumberUtils.max(1, 2, 123), 123);
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
