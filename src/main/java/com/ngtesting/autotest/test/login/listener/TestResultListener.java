package com.ngtesting.autotest.test.login.listener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.log4testng.Logger;


/**
 *
 */
public class TestResultListener extends TestListenerAdapter {


    private static Logger logger = Logger.getLogger(TestResultListener.class);
    protected ITestContext testContext = null; // 这里也是新加的


    @Override
    public void onStart(ITestContext testContext) { // 这里也是新加的，用于对context进行统一
        this.testContext = testContext;
        super.onStart(testContext);
    }


    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        logger.warn(tr.getName() + " 测试用例执行失败！");
    }


    @Override
    public void onTestSkipped(ITestResult tr) {
        super.onTestSkipped(tr);
        logger.warn(tr.getName() + " 测试用例由于某些原因被跳过！");


    }


    @Override
    public void onTestSuccess(ITestResult tr) {
        super.onTestSuccess(tr);
        logger.info(tr.getName() + " 测试用例执行成功！");
    }


    @Override
    public void onTestStart(ITestResult tr) {
        super.onTestStart(tr);
        logger.info(tr.getName() + " 测试用例开始执行！");
    }


    @Override
    public void onFinish(ITestContext testContext) {
        super.onFinish(testContext);


// List of test results which we will delete later
        ArrayList<ITestResult> testsToBeRemoved = new ArrayList<ITestResult>();
// collect all id's from passed test
        Set<Integer> passedTestIds = new HashSet<Integer>();
        for (ITestResult passedTest : testContext.getPassedTests().getAllResults()) {
            logger.info("执行成功的用例 = " + passedTest.getName());
        }


// Eliminate the repeat methods
        Set<Integer> skipTestIds = new HashSet<Integer>();
        for (ITestResult skipTest : testContext.getSkippedTests().getAllResults()) {
            logger.info("被跳过的用例 = " + skipTest.getName());
// id = class + method + dataprovider


        }


// Eliminate the repeat failed methods
        Set<Integer> failedTestIds = new HashSet<Integer>();
        for (ITestResult failedTest : testContext.getFailedTests().getAllResults()) {
            logger.info("执行失败的用例 = " + failedTest.getName());

        }


// finally delete all tests that are marked
        for (Iterator<ITestResult> iterator = testContext.getFailedTests().getAllResults().iterator(); iterator.hasNext(); ) {
            ITestResult testResult = iterator.next();
            if (testsToBeRemoved.contains(testResult)) {
                logger.info("移除重复失败的用例 = " + testResult.getName());
                iterator.remove();
            }
        }


    }
}

