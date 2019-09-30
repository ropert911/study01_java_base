package com.xq.study.mongo.entity;

import com.xq.study.mongo.constans.Sex;

import java.io.Serializable;

/**
 * Created by summer on 2017/5/5.
 */
public class User implements Serializable {
        private static final long serialVersionUID = -3258839839160856613L;
        private Long id;
        private String userName;
        private String passWord;
        private Sex sex;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getPassWord() {
                return passWord;
        }

        public void setPassWord(String passWord) {
                this.passWord = passWord;
        }
        public Sex getSex() {
                return sex;
        }

        public void setSex(Sex sex) {
                this.sex = sex;
        }
        @Override
        public String toString() {
                return "User{" +
                        "id=" + id +
                        ", userName='" + userName + '\'' +
                        ", passWord='" + passWord + '\'' +
                        ", sex='" + sex +'\'' +
                        '}';
        }
}
