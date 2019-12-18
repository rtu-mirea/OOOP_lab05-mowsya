package com.company;

public class Professor extends User implements java.io.Serializable {

    public Professor(String name, String login, char[] password) {
        super(name, login, password);
        access = 0;
    }
    public Professor(String name, String login, char[] password, int access) {
        super(name, login, password, access);
    }

    @Override
    public String toString() {
        String pass = "";
        for (char i: getPassword()){
            pass = pass + i;
        }
        return getName() + " " + getLogin() + " " + pass;
    }
}
