package com.study.spring.bean.sang.db;

import com.study.spring.bean.sang.bean.Noodle;

import java.util.List;

/**
 * Created by sang on 17-1-17.
 */
public interface NoodleMapper {
    List<Noodle> findNoodleByArea();
}
