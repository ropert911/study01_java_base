package com.study.spring.bean.sang.db;

import com.study.spring.bean.sang.bean.Alias;

/**
 * Created by sang on 17-1-17.
 */
public interface AliasMapper {
    Alias findAliasByPid(Long id);
}
