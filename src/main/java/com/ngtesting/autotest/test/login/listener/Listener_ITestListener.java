package com.ngtesting.autotest.test.login.listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listener_ITestListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        // 针对@Test方法开始前的处理
        System.out.println("正在进行--->"+result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // 针对@Test方法执行成功后的处理
        System.out.println("执行成功后进行--->"+result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // 针对@Test方法执行失败后的处理
        System.out.println("执行失败后进行--->"+result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // 针对@Test方法跳过执行后的处理
        System.out.println("跳过执行后进行--->"+result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // 针对@Test方法断言的处理（这个很少用）

    }

    @Override
    public void onStart(ITestContext context) {
        // 针对TestNG。xml文件Test标签开始前的处理
        System.out.println("执行Test tag前进行--->"+context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        // 针对TestNG。xml文件Test标签开始后的处理
        System.out.println("执行Test tag后进行--->"+context.getName());
    }
}
