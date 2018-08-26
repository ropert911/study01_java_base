import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoLogApplication {
    private static Logger logger = LoggerFactory.getLogger(DemoLogApplication.class);

    public static void main(String[] args) {
        logger.debug("This is debug message.");
        logger.info("This is info message.");
        logger.warn("This is warn message.");
        logger.error("This is error message.");
    }
}