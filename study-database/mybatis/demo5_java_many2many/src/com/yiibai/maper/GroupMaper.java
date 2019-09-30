package com.yiibai.maper;

import yiibai.pojo.Group;

/**
 * Created by xq on 2018/7/30.
 */
public interface GroupMaper {
    void insertGroup(Group group);

    Group getGroup(int groupId);
}
