package com.study.rest.demo.dto;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by xq on 2017/9/22.
 */
public class User {
    @NotBlank(message="用户名不能为空")
	private String UserName;
    @NotBlank(message="密码不能为空")
    private String PassWord;


	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

    @Override
    public String toString() {
        return "User{" +
                "UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                '}';
    }
}
