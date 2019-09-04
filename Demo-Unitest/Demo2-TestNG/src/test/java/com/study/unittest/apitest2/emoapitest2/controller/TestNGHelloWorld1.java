package com.study.unittest.apitest2.emoapitest2.controller;

import org.testng.Assert;
import org.testng.annotations.*;

@Test(groups = "Group")
//@Listeners(TestListenerAdapterImp.class)
public class TestNGHelloWorld1 {
    @BeforeTest
    public void bfTest() {
        System.out.println("TestNGHelloWorld1 beforTest!");
    }

    @Test(expectedExceptions = ArithmeticException.class, expectedExceptionsMessageRegExp = ".*zero")
    public void helloWorldTest1() {
        System.out.println("TestNGHelloWorld1 Test1!");
        int c = 1 / 0;
        Assert.assertEquals("1", "1");
    }

    @Test(groups = "Group1")
    @Parameters(value = "para")
    public void helloWorldTest2(@Optional("Tom")String str) {
        Assert.assertEquals("1", "2");
        System.out.println("TestNGHelloWorld1 Test2! "+ str);
    }

    @Test(groups = "Group2")
    public void helloWorldTest3(){
        System.out.println("TestNGHelloWorld1 Test3!");
    }

    @AfterTest
    public void AfTest() {
        System.out.println("TestNGHelloWorld1 AfterTest!");
    }
}