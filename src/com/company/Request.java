package com.company;

public class Request  implements java.io.Serializable {
    private Professor requester;
    private String discipline;
    private int group, pairs;

    Request(Professor requester, String disc, int group, int pairs) {
        this.requester = requester;
        this.discipline = disc;
        this.group = group;
        this.pairs = pairs;
    }

    String getRequester() {
        return this.requester.getName();
    }

    String getDiscipline() {
        return this.discipline;
    }

    int getGroup() {
        return this.group;
    }

    int getPairs() {
        return this.pairs;
    }
    @Override
    public String toString(){
        return getRequester() + ", дисциплина: " + getDiscipline() + ", группа: " + getGroup() + ", кол-во часов: " + getPairs();
    }
}