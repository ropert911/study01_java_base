package com.study.spring.utils.conditional.linux;


import com.study.spring.utils.conditional.ListService;

/**
 * Created by sang on 16-12-14.
 */
public class LinuxListService implements ListService {
    @Override
    public String showListCmd() {
        return "ls";
    }
}
