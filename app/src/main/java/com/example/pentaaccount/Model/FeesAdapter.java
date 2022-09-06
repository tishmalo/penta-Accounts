package com.example.pentaaccount.Model;

public class FeesAdapter {
    String username,balance;

    public FeesAdapter() {
    }

    public FeesAdapter(String username, String balance) {
        this.username = username;
        this.balance = balance;
    }

    public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getbalance() {
        return balance;
    }

    public void setbalance(String balance) {
        this.balance = balance;
    }
}
