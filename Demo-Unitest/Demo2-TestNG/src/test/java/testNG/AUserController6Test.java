package testNG;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AUserController6Test extends AbstractTestNGSpringContextTests {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testBook1() {
        String result = testRestTemplate.getForObject("/book/hello1", String.class);
        logger.info(result);
        Assert.assertNotNull(result);
        logger.info("5555555555555555555555");
    }
}