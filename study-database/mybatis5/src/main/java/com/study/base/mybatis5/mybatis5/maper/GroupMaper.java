package com.study.base.mybatis5.mybatis5.maper;


import com.study.base.mybatis5.mybatis5.pojo.Group;

/**
 * @author sk-qianxiao
 */
public interface GroupMaper {
    void insertGroup(Group group);

    Group getGroup(int groupId);
}
