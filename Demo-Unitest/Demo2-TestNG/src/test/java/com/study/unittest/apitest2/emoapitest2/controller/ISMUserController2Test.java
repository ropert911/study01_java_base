package com.study.unittest.apitest2.emoapitest2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.*;

//执行顺序  BeforeSuite  BeforeTest BeforeClass BeforeMethod
public class ISMUserController2Test {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass    //在调用当前类的第一个测试方法之前运行，注释方法仅运行一次
    public void BeforeClass() {
        logger.info("xxxx222 -- BeforeClass");
    }

    @AfterClass     //在调用当前类的第一个测试方法之后运行，注释方法仅运行一次
    public void AfterClass() {
        logger.info("xxxx222 -- AfterClass");
    }

    @BeforeSuite    //在该套件的所有测试都运行在注释的方法之前，仅运行一次
    public void BeforeSuite() {
        logger.info("xxxx222 -- BeforeSuite");
    }

    @AfterSuite    //在该套件的所有测试都运行在注释方法之后，仅运行一次
    public void AfterSuite() {
        logger.info("xxxx222 -- AfterSuite");
    }

    @BeforeTest     //注释的方法将在属于<test>标签内的类的所有测试方法运行之前运行
    public void BeforeTest() {
        logger.info("xxxx222 -- BeforeTest");
    }

    @AfterTest      //注释的方法将在属于<test>标签内的类的所有测试方法运行之后运行
    public void AfterTest() {
        logger.info("xxxx222 -- AfterTest");
    }

    @BeforeGroups("group2-1")   //配置方法将在之前运行组列表。 此方法保证在调用属于这些组中的任何一个的第一个测试方法之前不久运行。
    public void BeforeGroups() {
        logger.info("xxxx222 -- BeforeGroups");
    }

    @AfterGroups("group2-1")    //此配置方法将在之后运行组列表。该方法保证在调用属于任何这些组的最后一个测试方法之后不久运行。
    public void AfterGroups() {
        logger.info("xxxx222 -- AfterGroups");
    }

    @BeforeMethod   //@Test 执行前执行
    public void BeforeMethod() {
        logger.info("xxxx222 -- BeforeMethod");
    }

    @AfterMethod     //@Test 执行之后执行
    public void AfterMethod() {
        logger.info("xxxx222 -- AfterMethod");
    }

    @Test
    public void testBook1() {
        logger.info("222---- testBook1");
    }

    @Test(groups="group2-1")
    public void testBook2() {
        logger.info("222---- testBook2");
    }

    @Test(groups="group2-3")
    public void testBook3() {
        logger.info("222---- testBook3");
    }

    @Test(groups="group2-1")
    public void testBook4() {
        logger.info("222---- testBook4");
    }

    @Test(expectedExceptions = { RuntimeException.class, Exception.class })
    public void throwExceptions() throws RuntimeException{
        throw new RuntimeException();
    }
    @Test(enabled = false)
    public void ignorTest() {
        Assert.assertEquals(true, true);
    }

    @Test(timeOut = 2000) // time in milliseconds
    public void testTimeout() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    public void method1() {
        logger.info("222---- This is method 1");
    }

    //依赖
    @Test(dependsOnMethods = { "method1" })
    public void method2() {
        logger.info("222---- This is method 2");
    }

    //多次测试
    @Test(invocationCount = 10)
    public void repeatThis() {
        logger.info("222---- repeatThis");
    }
}