package com.xq.study.rest.dto;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


public class PersonalValidtor implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return PersonScope.class.equals(aClass);
    }

    /**
     * .      * 实现Validator中的validate接口
     * .      * @param obj
     * .      * @param errors
     * .
     */
    @Override
    public void validate(Object obj, Errors errors) {
        //把校验信息注册到Error的实现类里
        PersonScope personScope = (PersonScope) obj;
        if (StringUtils.isEmpty(personScope.getName())) {
            ValidationUtils.rejectIfEmpty(errors, "name", "111", "姓名不能为空!");
        }

        if (StringUtils.isEmpty(personScope.getAddress())) {
            errors.rejectValue("address", "222", "家庭地址不能为空!!!!");
        }
    }
}