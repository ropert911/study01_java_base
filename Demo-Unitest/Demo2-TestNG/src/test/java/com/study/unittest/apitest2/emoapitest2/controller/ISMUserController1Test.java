package com.study.unittest.apitest2.emoapitest2.controller;

import org.testng.Assert;

public class ISMUserController1Test {

    @org.testng.annotations.BeforeMethod
    public void setUp() {
    }

    @org.testng.annotations.AfterMethod
    public void tearDown() {
    }

    @org.testng.annotations.Test
    public void testBook1() {
        String email = "feedback@yiibai.com";
        Assert.assertNull(email);
    }

    @org.testng.annotations.Test
    public void testBook2() {
        String email = "feedback@yiibai.com";
        Assert.assertNotNull(email);
        Assert.assertEquals(email, "feedback@yiibai.com");
    }

    @org.testng.annotations.Test
    public void testBook3() {
    }

    @org.testng.annotations.Test
    public void testBook4() {
    }
}