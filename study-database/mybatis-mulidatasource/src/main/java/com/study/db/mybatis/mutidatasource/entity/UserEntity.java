package com.study.db.mybatis.mutidatasource.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author sk-qianxiao
 */
@Data
@ToString
@Builder
public class UserEntity  {
    public UserEntity(Long id, String userName, String passWord, String nickName, UserSexEnum userSex) {
        this.id = id;
        this.userName = userName;
        this.passWord = passWord;
        this.nickName = nickName;
        this.userSex = userSex;
    }

    public UserEntity() {
    }

    private Long id;
    private String userName;
    private String passWord;
    private String nickName;
    private UserSexEnum userSex;
}