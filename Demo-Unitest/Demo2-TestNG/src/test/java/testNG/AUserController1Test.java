package testNG;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

//执行顺序  BeforeSuite  BeforeTest BeforeClass BeforeMethod
public class AUserController1Test {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeClass    //在调用当前类的第一个测试方法之前运行，注释方法仅运行一次
    public void BeforeClass() {
        logger.info("xxxx111 -- BeforeClass");
    }

    @AfterClass     //在调用当前类的第一个测试方法之后运行，注释方法仅运行一次
    public void AfterClass() {
        logger.info("xxxx111 -- AfterClass");
    }

    @BeforeSuite    //在该套件的所有测试都运行在注释的方法之前，仅运行一次
    public void BeforeSuite() {
        logger.info("xxxx111 -- BeforeSuite");
    }

    @AfterSuite    //在该套件的所有测试都运行在注释方法之后，仅运行一次
    public void AfterSuite() {
        logger.info("xxxx111 -- AfterSuite");
    }

    @BeforeTest     //注释的方法将在属于<test>标签内的类的所有测试方法运行之前运行
    public void BeforeTest() {
        logger.info("xxxx111 -- BeforeTest");
    }

    @AfterTest      //注释的方法将在属于<test>标签内的类的所有测试方法运行之后运行
    public void AfterTest() {
        logger.info("xxxx111 -- AfterTest");
    }

    @BeforeGroups("group1-1")   //配置方法将在之前运行组列表。 此方法保证在调用属于这些组中的任何一个的第一个测试方法之前不久运行。
    public void BeforeGroups() {
        logger.info("xxxx111 -- BeforeGroups");
    }

    @AfterGroups("group1-1")    //此配置方法将在之后运行组列表。该方法保证在调用属于任何这些组的最后一个测试方法之后不久运行。
    public void AfterGroups() {
        logger.info("xxxx111 -- AfterGroups");
    }

    //    @DataProvider   标记一种方法来提供测试方法的数据。 注释方法必须返回一个Object [] []，其中每个Object []可以被分配给测试方法的参数列表。 要从该DataProvider接收数据的@Test方法需要使用与此注释名称相等的dataProvider名称。
//    @Factory    将一个方法标记为工厂，返回TestNG将被用作测试类的对象。 该方法必须返回Object []。
//    @Listeners  定义测试类上的侦听器
//    @Parameters 描述如何将参数传递给@Test方法

    @BeforeMethod   //@Test 执行前执行
    public void BeforeMethod() {
        logger.info("xxxx111 -- BeforeMethod");
    }

    @AfterMethod     //@Test 执行之后执行
    public void AfterMethod() {
        logger.info("xxxx111 -- AfterMethod");
    }


    @Test
    public void testBook1() {
        logger.info("111---- testBook1");
    }

    @Test(groups="group1-1")
    public void testBook2() {
        logger.info("222---- testBook2");
        Assert.assertNull(null);
    }

    @Test(groups="group3")
    public void testBook3() {
        logger.info("111---- testBook3");
    }

    @Test(groups="group1-1")
    public void testBook4() {
        logger.info("111---- testBook4");
    }
}