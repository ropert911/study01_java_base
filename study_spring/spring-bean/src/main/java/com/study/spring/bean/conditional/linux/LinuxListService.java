package com.study.spring.bean.conditional.linux;


import com.study.spring.bean.conditional.ListService;

/**
 * @author sk-qianxiao
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
