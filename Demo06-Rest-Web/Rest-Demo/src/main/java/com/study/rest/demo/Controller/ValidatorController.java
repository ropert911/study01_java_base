package com.study.rest.demo.Controller;

import com.study.rest.demo.dto.PersonScope;
import com.study.rest.demo.dto.PersonalValidtor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by xq on 2017/10/24.
 */
@Api(value = "validatore验证", description = "validatore验证相关接口")
@RestController
@RequestMapping("/v")
public class ValidatorController extends BaseController {
    //绑定PersonalValidator
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(new PersonalValidtor());
    }

    //使用validator验证
    @PostMapping(value = "/person")
//    @ResponseBody
    public String testPersonalValidtor(@ApiParam(value = "", required = true) @Valid @RequestBody final PersonScope personScope,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuffer sb = new StringBuffer();
            for (ObjectError objectError : bindingResult.getAllErrors()) {
                sb.append(((FieldError) objectError).getField() + " : ").append(objectError.getDefaultMessage());
            }
            return sb.toString();
        } else {
            return personScope.toString();
        }
    }
}
