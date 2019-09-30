package com.study.spring.bean.conditional.windows;


import com.study.spring.bean.conditional.ListService;

/**
 * @author sk-qianxiao
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
