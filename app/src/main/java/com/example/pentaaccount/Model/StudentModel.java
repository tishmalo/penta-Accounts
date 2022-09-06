package com.example.pentaaccount.Model;

public class StudentModel {
    private String code, Username;

    public StudentModel() {
    }

    public StudentModel(String code, String username) {
        this.code = code;
        Username = username;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }
}
