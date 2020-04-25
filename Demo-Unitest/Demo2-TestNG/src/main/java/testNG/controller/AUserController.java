package testNG.controller;

import testNG.config.LocaleMessageSourceService;
import testNG.podo.AUser;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sang on 2017/9/9.
 */
@RestController
@RequestMapping("/book")
@Api("book相关")
public class AUserController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LocaleMessageSourceService localeMessageSourceService;

    @RequestMapping(value = "/hello1", method = RequestMethod.GET)
    public String book5() {
        logger.info("得到信息{}", localeMessageSourceService.getMessage("welcome"));
        return "hello";
    }

    //添加
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public AUser book1(@RequestBody AUser book) {
        System.out.println(book.getName());
        book.setPrice(33);
        book.setAuthor("曹雪芹");
        book.setPublisher("人民文学出版社");
        return book;
    }

    //删除
    @RequestMapping(value = "/book/{name}", method = RequestMethod.DELETE)
    public String book2(@PathVariable String name) {
        return "delete book：" + name;
    }

    //改
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    public AUser book3(@RequestBody AUser book, @PathVariable int id) {
        return book;
    }

    //查
    @RequestMapping(value = "/book/{name}", method = RequestMethod.GET)
    public AUser book4(@PathVariable String name) {
        return new AUser(name, 90, "罗贯中", "花城出版社");
    }
}
