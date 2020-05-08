package com.study.base.java_io;

import java.io.Console;

public class ConsoleTest {
    public static void main(String[] args) {
        //获得 Console 实例对象,需要有可用的console. 如在交互式命令中启动，并且没有重定向     
        Console console = System.console();
        if (console != null) {
            String user = new String(console.readLine("Enter username:"));
            String pwd = new String(console.readPassword("Enter passowrd:"));
            console.printf("Username is: " + user + "\n");
            console.printf("Password is: " + pwd + "\n");
        } else {
            System.out.println("Console is unavailable.");
        }
    }
}
