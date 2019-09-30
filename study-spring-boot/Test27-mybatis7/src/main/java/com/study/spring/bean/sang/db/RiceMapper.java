package com.study.spring.bean.sang.db;

import com.study.spring.bean.sang.bean.Rice;

import java.util.List;

/**
 * Created by sang on 17-1-17.
 */
public interface RiceMapper {
    List<Rice> findRiceByArea();
}
