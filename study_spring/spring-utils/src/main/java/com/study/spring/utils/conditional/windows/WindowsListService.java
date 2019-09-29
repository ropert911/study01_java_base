package com.study.spring.utils.conditional.windows;


import com.study.spring.utils.conditional.ListService;

/**
 * Created by sang on 16-12-14.
 */
public class WindowsListService implements ListService {
    @Override
    public String showListCmd() {
        return "dir";
    }
}
