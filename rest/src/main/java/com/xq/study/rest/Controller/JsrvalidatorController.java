package com.xq.study.rest.Controller;

import com.xq.study.rest.dto.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by xq on 2017/10/24.
 */
@Api(value = "jsr303验证", description = "jsr303验证相关接口")
@RestController
@RequestMapping("/v2")
public class JsrvalidatorController extends BaseController {
    @PostMapping("login")
    public String login(@ApiParam(value = "", required = true) @Valid @RequestBody final User user,
                        BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                sb.append(((FieldError) objectError).getField() + " : ").append(objectError.getDefaultMessage());
            }

            System.out.println("=========" + sb);
            return sb.toString();
        } else {
            return user.toString();
        }
    }

}
