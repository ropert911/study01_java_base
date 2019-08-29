import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//SLF4j 简单日志门面（Simple Logging Facade for Java）
//SLF4J是一个用于日志系统的简单Facade，允许最终用户在部署其应用时使用其所希望的日志系统
//spring_boot中内置支持
public class Slf4jOrgApplication {
    private static final Logger logger = LoggerFactory.getLogger(Slf4jOrgApplication.class);

    public static void main(String[] args) {
        logger.error("Slf4jOrgApplication  error msg");
        logger.warn("Slf4jOrgApplication   warn msg");
        logger.info("Slf4jOrgApplication   info msg");
        logger.debug("Slf4jOrgApplication  debug msg");
    }
}
