package com.newbei.developerInfoInOutJson.model;

public class Account {
    private long id;
    private String login;
    private String password;
    private AccountStatus accountStatus;

    public Account() {};
    public Account(long id) {this.id = id;}

    public long getId() { return id; }

    public void setId(long id) {this.id = id; }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public String toString() {
        return "Account with " + "id : " + id +" has accountStatus=" + accountStatus;
    }
}
