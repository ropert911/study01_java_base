package com.study.rest.consumer.bean.conditional.windows;


import com.study.rest.consumer.bean.conditional.ListService;

/**
 * @author sk-qianxiao
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
