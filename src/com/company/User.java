package com.company;

import java.util.Arrays;

public class User implements java.io.Serializable {
    protected String name;
    protected String login;
    protected char[] password;
    public int access = 0;

    public User() {

    }

    public User(String name, String login, char[] password) {
        setName(name);
        setLogin(login);
        setPassword(password);
    }
    public User(String name, String login, char[] password, int access) {
        setName(name);
        setLogin(login);
        setPassword(password);
        this.access = access;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return name;
    }

    public char[] getPassword() {
        return password;
    }

    public boolean enter(String login, char[] password) {
        if (this.login == login && Arrays.equals(this.password, password)) {
            return true;
        } else {
            return false;
        }
    }
}
