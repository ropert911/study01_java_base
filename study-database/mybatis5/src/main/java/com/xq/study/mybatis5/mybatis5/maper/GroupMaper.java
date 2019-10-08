package com.xq.study.mybatis5.mybatis5.maper;


import com.xq.study.mybatis5.mybatis5.pojo.Group;

/**
 * @author sk-qianxiao
 */
public interface GroupMaper {
    void insertGroup(Group group);

    Group getGroup(int groupId);
}
