package com.study.rest.demo.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.BasicErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 系统异常统一处理（包括spring framework内异常）
 *
 * @author sk-shifanwen on 2019/7/23.
 */
@RestController
public class CustomErrorController extends BasicErrorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomErrorController.class);


    public CustomErrorController() {
        super(new DefaultErrorAttributes(), new ErrorProperties());
    }

    private static final String PATH = "/error";

    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Override
    public ResponseEntity error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request, isIncludeStackTrace(request, MediaType.ALL));
        LOGGER.error("BaseError: " + body);
        HttpStatus status = getStatus(request);
        return new ResponseEntity("aaaaaaaa", status);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}