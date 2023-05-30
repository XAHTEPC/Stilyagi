package com.example.demo.Model;

public class User {
    String employee_id;
    String login;
    String password;
    String level_id;

    public User(String employee_id, String login, String password, String level_id) {
        this.employee_id = employee_id;
        this.login = login;
        this.password = password;
        this.level_id = level_id;
    }
}
