package com.study.db.mybatis5.maper;


import com.study.db.mybatis5.pojo.Group;

/**
 * @author sk-qianxiao
 */
public interface GroupMaper {
    void insertGroup(Group group);

    Group getGroup(int groupId);
}
