package com.study.rest.consumer.bean.conditional.linux;


import com.study.rest.consumer.bean.conditional.ListService;

/**
 * @author sk-qianxiao
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
