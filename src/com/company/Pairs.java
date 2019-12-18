package com.company;

import java.util.ArrayList;


public class Pairs implements java.io.Serializable{
    private final String[] days = {"ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ"};
    private ArrayList<Integer> room;
    private ArrayList<Integer> group;
    private ArrayList<String> prof;
    private ArrayList<String> pair;

    Pairs() {
        room = new ArrayList<>();
        group = new ArrayList<>();
        prof = new ArrayList<>();
        pair = new ArrayList<>();
    }

    public void occRoom(int room) {
        this.room.add(room);
    }

    public void occGroup(int group) {
        this.group.add(group);
    }

    public void occProf(String prof) {
        this.prof.add(prof);
    }

    public boolean checkRoom(int room) {
        return this.room.contains(room);
    }

    public boolean checkGroup(int group) {
        return this.group.contains(group);
    }

    public boolean checkProf(String prof) {
        return this.prof.contains(prof);
    }

    public int getRooms() {
        return this.room.size();
    }

    public void setPair(String prof, String disc, int room, int group, int hour, int day) {
        this.pair.add("Профессор: " + prof + ", дисциплина: " + disc + ", аудитория № " + room + ", группа № " + group + ", пара №" + (hour + 1) + ", день недели: " + days[day]);
    }

    public String printPair(String prof) {
        for (String i : pair) {
            if (i.contains(prof)) {
                return i;
            }
        }
        return "";
    }
}